package model.language;

import model.InterpreterException;
import model.nodes.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This test class will test some aspects of the rules
 * of the LanguageParser.
 * 
 * EXPRESSION   ::= [ "+" | "-" ] TERM { ( "+" | "-" ) TERM }
 * TERM         ::= FACTOR { ( "*" | "/" | "%" | "^" | "_/" | ) FACTOR }
 * FACTOR       ::= (Literal | DOUBLE)|
 *                  Identifier |
 *                  CELLIDENTIFIER |
 *                  RANGE|
 *                  "(" EXPRESSION ")"
 * </code>
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public class LanguageParserTest {

    @Test
    public void testLiteral() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=12";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new NumberLiteral(12);
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testNegation() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=-11";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new Negation(new NumberLiteral(11));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testSquareRoot() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=SQRT(16)";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new SquareRoot(new NumberLiteral(16));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    @Test
    public void testUnaryPlus() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=+11";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new NumberLiteral(11);
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testAddition() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=12+2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new Addition(new NumberLiteral(12), new NumberLiteral(2));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testSubtraction() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=12-2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new Subtraction(new NumberLiteral(12), new NumberLiteral(2));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testMultiplication() {
        boolean thrown = false; 
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=12*2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new Multiplication(new NumberLiteral(12), new NumberLiteral(2));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testDivision() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=12/2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new Division(new NumberLiteral(12), new NumberLiteral(2));
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void testParentheses() {
        boolean thrown = false;
        try {
            // setup
            final Parser parser = new LanguageParser();
            // test input
            final String sourceCode = "=(12)";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            // expected tree
            final Node expectedRoot = new NumberLiteral(12);
            // assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testDouble() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "1.2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new NumberLiteral(1.2);
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testDoubleNegation() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=-1.2";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new Negation(new NumberLiteral(1.2));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        }catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testExp() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=2.0^3.0";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new Exponential(new NumberLiteral(2),new NumberLiteral(3));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testMod() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=2.0%4.0";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new Modulo(new NumberLiteral(2.0), new NumberLiteral(4.0));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testFDiv() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=15.0_/2.0";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new FloorDivision(new NumberLiteral(15.0), new NumberLiteral(2.0));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testComplicatedOne() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=(5+5)*12";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new Multiplication(
                                                            new Addition(
                                                                            new NumberLiteral(5),
                                                                            new NumberLiteral(5))
                                                            , new NumberLiteral(12));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        }catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    
    }
    
    @Test
    public void testComplicatedTwo() {
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=(5+5)5";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new StringNode("Error: Operator expected.");
            //assertion
            assertEquals(expectedRoot.getValue(), actualRoot.getValue());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testComplicatedThree(){
        boolean thrown = false;
        try {
            //setup
            final Parser parser = new LanguageParser();
            //test input
            final String sourceCode = "=((((5+5)))))";
            // code under test
            final Node actualRoot = parser.parse(sourceCode);
            //expected tree
            final Node expectedRoot = new Addition(new NumberLiteral(5), new NumberLiteral(5));
            //assertion
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testMIN(){
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=MIN(A0:B0)";
            final int[] cells = {0,0,0,1};
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Minimum(new RangeNode(cells));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testMAX() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=MAX(A0:B0)";
            final int[] cells = {0,0,0,1};
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Maximum(new RangeNode(cells));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testSUM() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=SUM(A0:B0)";
            final int[] cells = {0,0,0,1};
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Summation(new RangeNode(cells));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testAVG() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=AVG(A0:B0)";
            final int[] cells = {0,0,0,1};
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Average(new RangeNode(cells));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testAdditionFunction() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=5+AVG(A0:B0)";
            final Node actualRoot = parser.parse(sourceCode);
            final int[] cells = {0,0,0,1};
            final Node expectedRoot = new Addition(new NumberLiteral(5),
                new Average(new RangeNode(cells)));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testFunctionMultiplication() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=SUM(A0:B0)*5";
            final Node actualRoot = parser.parse(sourceCode);
            final int[] cells = {0,0,0,1};
            final Node expectedRoot = new Multiplication( new Summation(new RangeNode(cells))
                ,new NumberLiteral(5));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testAdditionSqrt() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=5+SQRT(16)";
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Addition(new NumberLiteral(5),
                new SquareRoot(new NumberLiteral(16)));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testSine() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=SIN(16)";
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Sine(new NumberLiteral(16));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testCosine() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=COS(16)";
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Cosine(new NumberLiteral(16));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testTangent() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "=TAN(16)";
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new Tangent(new NumberLiteral(16));
            assertEquals(expectedRoot.toString(), actualRoot.toString());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testPrimitive() {
        boolean thrown = false;
        try {
            final Parser parser = new LanguageParser();
            final String sourceCode = "12";
            final Node actualRoot = parser.parse(sourceCode);
            final Node expectedRoot = new NumberLiteral(12);
            assertEquals(expectedRoot.toString(), actualRoot.toString());
            final String sourceCodetwo = "HELLO WORLD";
            final Node actualRoottwo = parser.parse(sourceCodetwo);
            final Node expectedRoottwo = new StringNode("HELLO WORLD");
            assertEquals(expectedRoottwo.toString(), actualRoottwo.toString());
            
            final String errorPrimitive = "12cs";
            final Node actualerror = parser.parse(errorPrimitive);
            final Node expectederror = new ErrorNode("12cs", "\"12cs\" is not a valid Number");
            assertEquals(actualerror.toString(), expectederror.toString());
            assertEquals(actualerror.getValue(), actualerror.getValue());
        } catch (InterpreterException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
}
