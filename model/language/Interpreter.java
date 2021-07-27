package model.language;

import model.SheetModel;
import model.nodes.Node;
import model.nodes.Type;
import model.program.Program;

/**
 * The Interpreter, returns the result of the ASTTree generated by the Parser.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Interpreter
{
    private final LanguageParser lp;
    private final SheetModel sm;

    /**
     * Constructor for the Interpreter.
     * @param sm the SheetModel that will be used as 
     *     The variableTable for cells and ranges.
     */
    public Interpreter(final SheetModel sm)
    {
        this.lp = new LanguageParser();
        this.sm = sm;
    }
    
    /**
     * Parse a String to an AstTree and execute it to get the result.
     * @param sourceCode the String of the expression.
     * @return Object the result of the execution of the expression or an error.
     */
    public Object parseAndExecute(final String sourceCode) {
        final Node n = lp.parse(sourceCode);
        return execute(n);
    }
    
    /**
     * Execute an AstTree and return the result or the error as string.
     * @param astTree the Pre- parsed AstTree to execute.
     * @return Object the result of the execution of the expression or an error.
     */
    public Object execute(final Node astTree) {
        final Program p = new Program();
        if (astTree != null) {
            if (astTree.getType() == Type.STRING) {
                return astTree.toString();
            } else {
                astTree.compile(p);
                return p.execute(this.sm).doubleValue();
            }
        } else { return ""; }
    }
    
    /**
     * Parse and return the AstTree of an Expression String.
     * @param sourceCode the Expression String we want to parse.
     * @return Node the AstTree generated from the parser.
     */
    public Node parseCode(final String sourceCode) {
        return lp.parse(sourceCode);        
    }
}