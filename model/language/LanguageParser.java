package model.language;

import model.InterpreterCommandException;
import model.InterpreterException;
import model.InterpreterOperatorException;
import model.ModelException;
import model.nodes.Addition;
import model.nodes.Average;
import model.nodes.Cosine;
import model.nodes.Division;
import model.nodes.ErrorNode;
import model.nodes.Exponential;
import model.nodes.FloorDivision;
import model.nodes.Maximum;
import model.nodes.Minimum;
import model.nodes.Modulo;
import model.nodes.Multiplication;
import model.nodes.Negation;
import model.nodes.Node;
import model.nodes.NumberLiteral;
import model.nodes.NumberVariable;
import model.nodes.RangeNode;
import model.nodes.Sine;
import model.nodes.SquareRoot;
import model.nodes.StringNode;
import model.nodes.Subtraction;
import model.nodes.Summation;
import model.nodes.Tangent;

/**
 * An Parser to generate a valid AST tree from a String input.
 * (a simple language of arithmetic expressions).
 * 
 * <code>
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" | "%" | "^" | "_/" | ) FACTOR }
 * FACTOR       ::= (Literal | DOUBLE)|
 *                  Identifier |
 *                  CELLIDENTIFIER |
 *                  RANGE|
 *                  "(" EXPRESSION ")"
 * </code>
 * If code doesnt start with an Equal sign, there can only be two outcomes, either
 * a String or a Number. See ParsePrimitive Input.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public final class LanguageParser implements Parser {
    
    private LexicalAnalyzer lexer;
    
    /**
     * Parse a program in the Arith language.
     * @param sourceCode The source code of the program in the Arith language
     * @return an AST of the program
     * @throws InterpreterException when The SourceCode Cannot be succesfully interpreted.
     */
    public Node parse(final String sourceCode) {
        String source = sourceCode;
        if (source.startsWith("=")) {
            source = sourceCode.replaceAll(" ","");
        } else {
            return parsePrimitiveInput(sourceCode);
        }
        this.lexer = new LexicalAnalyzer(source);
        // fetch first token
        lexer.fetchNextToken();
        //Skip the = sign
        lexer.fetchNextToken();
        // now parse the EXPRESSION
        try {
            return parseExpression();
        } catch (ModelException moe) {
            return new ErrorNode(sourceCode ,moe.getMessage());
        }
    }
    
    /**
     * Parse the Input to a primitive type.
     * @returns Node either NumberNode or StringNode if number or string.
     */
    private Node parsePrimitiveInput(final String sourceCode) {
        this.lexer = new LexicalAnalyzer(sourceCode);
        lexer.fetchNextToken();
        final Token cur = lexer.getCurrentToken();
        try {
            if (cur.getType() == TokenType.LITERAL || cur.getType() == TokenType.DOUBLE) {
                return new NumberLiteral(Double.parseDouble(sourceCode.replaceAll(" ","")));
            } else { return new StringNode(sourceCode); }
        } catch (NumberFormatException nfe) {
            return new ErrorNode(sourceCode,"\"" + sourceCode + "\"is not a valid Number");
        }
    }
    
    /**
     * Parse an expression.
     * This assumes the lexer already points to the first token of this expression.
     * 
     * <p>EBNF:
     * <code>
     * EXPRESSION ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
     * </code>
     * 
     * @return a Node representing the expression
     */
    private Node parseExpression() throws InterpreterException {
        //Check ["+"|"-"] for the first TERM
        Node left = parseExpressionHelper();
        Token cur = lexer.getCurrentToken();
        Node avg = null;
        while (cur.getType() == TokenType.PLUS || cur.getType() == TokenType.MINUS) {
            avg = operationHelper(cur,left);
            cur = lexer.getCurrentToken();
            left = avg;
        }
        if (cur.getType() == TokenType.CLOSED_PAREN) {
            cur = lexer.getNextToken();
            if (cur.getType() != TokenType.END_OF_FILE) {
                return operationHelper(cur,left);
            }
        }
        return parseFinalizer(left,avg);
    }
    
    /**
     * If avg , the General and complete AST tree is complete and existent, otherwise
     * return just the left branch.
     * @return Node the left branch of the astTree
     */
    private Node parseFinalizer(final Node left,final Node avg) {
        if (avg != null) {
            return avg;
        } else { return left; }
    }
    
    /**
     * Helper Method that checks wheter the first element is positive or not.
     * @returns Node either the TERM or the NEGATION of the TERM.
     */
    private Node parseExpressionHelper() throws InterpreterException {
        final Token cur = lexer.getCurrentToken();
        if (cur.getType() == TokenType.MINUS) {
            lexer.fetchNextToken();
            final Node n = parseTerm();
            return new Negation(n);
        } else if (cur.getType() == TokenType.PLUS) {
            lexer.fetchNextToken();
            return parseTerm();
        } else {
            return parseTerm();
        }
    }
    
    /**
     * Parse a term.
     * This assumes the lexer already points to the first token of this term.
     * 
     * <p>EBNF:
     * <code>
     * TERM ::= FACTOR { ( "*" | "/" | "%" | "^" | "_/" | ) FACTOR }
     * </code>
     * 
     * @return a Node representing the term
     */
    private Node parseTerm() throws InterpreterException {
        Token cur = this.lexer.getCurrentToken();
        Node left = parseFactor();
        Node avg = null;
        cur = lexer.getNextToken();
        while (cur.getType() == TokenType.SLASH 
            || cur.getType() == TokenType.STAR
            || cur.getType() == TokenType.PERCENT
            || cur.getType() == TokenType.EXP
            || cur.getType() == TokenType.FLOOR) {
            avg = operationHelper(cur,left);
            cur = lexer.getNextToken();
            left = avg;
        }
        return parseFinalizer(left, avg);
    }
    
    /**
     * Helper to compute the various nodes in the various cases.
     * Depending on which method calls this one the outcome will be different,
     * if called within parseExpression, this will evaluate also the nextTERM,
     * if called within parseTerm , this will evaluate also the next FACTOR.
     */
    private Node operationHelper(final Token cur, final Node left) throws InterpreterException {
        lexer.fetchNextToken();    
        switch (cur.getType()) {
            case PLUS:
                return new Addition(left,parseTerm());
            case MINUS:
                return new Subtraction(left,parseTerm());
            case SLASH:
                return new Division(left,parseFactor());
            case STAR:
                return new Multiplication(left,parseFactor());
            case PERCENT:
                return new Modulo(left,parseFactor());
            case EXP:
                return new Exponential(left,parseFactor());
            default:
                return limitCasesHelper(cur,left);
        }
    }
    
    /**
     * This Method handles all the limitCases there can be. 
     * if there is an END_OF_FILE it will return.
     * if there is a closed parenthesis it will evaluate the next Element.
     */
    private Node limitCasesHelper(final Token cur, final Node left) throws InterpreterException {
        switch (cur.getType()) {
            case CLOSED_PAREN:
                final Token curr = lexer.getCurrentToken();
                return operationHelper(curr,left);
            case END_OF_FILE:
                return left;
            case FLOOR:
                return new FloorDivision(left,parseFactor());
            default:
                throw new InterpreterOperatorException();
        }
    }
    
    /**
     * Parse a factor.
     * This assumes the lexer already points to the first token of this factor.
     * 
     * <p>EBNF:
     * <code>
     * FACTOR ::=  
     *          (Literal | DOUBLE)|
     *          Identifier |
     *          CELLIDENTIFIER |
     *          RANGE|
     *          "(" EXPRESSION ")"
     * </code>
     * 
     * @return a Node representing the factor
     */
    private Node parseFactor() throws InterpreterException {
        final Token cur = this.lexer.getCurrentToken();
        switch (cur.getType()) {
            case OPEN_PAREN:
                lexer.fetchNextToken();
                return parseExpression();
            case LITERAL:
                return new NumberLiteral(Double.parseDouble(cur.getText()));
            case DOUBLE:
                return new NumberLiteral(Double.parseDouble(cur.getText()));
            case CELLIDENTIFIER:
                return new NumberVariable(ParseHelper.cellParser(cur.getText()));
            case RANGE:
                return new RangeNode(ParseHelper.rangeParser(cur.getText()));
            case IDENTIFIER:
                return parseFunction();
            default:
                throw new InterpreterOperatorException(
                    "Error: Operator requires two arguments.");                
        }
    }

    /**
     * parse a Function.
     * This assumes the lexer already points to IDENTIFIER Token of the function.
     * and calls the parseFactor() since the element within the parenthesis will either be a range
     * or a cellIdentifier.
     * 
     * 
     * @return a Node representing the Function.
     */
    private Node parseFunction() throws InterpreterException  {
        final String cur = this.lexer.getCurrentToken().getText();
        lexer.fetchNextToken();
        lexer.fetchNextToken();
        Node left;
        switch (cur) {
            case "SQRT":
                left = new SquareRoot(parseFactor());
                break;
            case "MIN":
                left = new Minimum(parseFactor());
                break;
            default:
                left = parseFunctionHelper(cur);
                break;
        } 
        lexer.fetchNextToken();
        final Token next = lexer.getCurrentToken();
        return operationHelper(next, left);
    }
    
    /**
     * Helper for parsFunction() method.
     * @param cur the current token's string
     * @return a Node representing the current Token's text.
     */
    private Node parseFunctionHelper(final String cur) throws InterpreterException {
        switch (cur) {
            case "SUM":
                return new Summation(parseFactor());
            case "AVG":
                return  new Average(parseFactor());
            case "MAX":
                return  new Maximum(parseFactor()); 
            default:
                return parseTrygoFunction(cur);
        }
    }
    
    /**
     * Helper for parseFunction() method, this one handles all the trygonometric functions.
     */
    private Node parseTrygoFunction(final String cur) throws InterpreterException {
        switch (cur) {
            case "SIN":
                return new Sine(parseFactor());
            case "COS":
                return new Cosine(parseFactor());
            case "TAN":
                return new Tangent(parseFactor());
            default:
                throw new InterpreterCommandException(cur);
        }
    }
}
    
