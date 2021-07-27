package model.language;

import model.nodes.*;
import model.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The test class for the Interpreter which is the Language pack main class
 * for relations with the external.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InterpreterTest
{   
    @Test
    public void testParseAndExecute() {
        
        final SheetModel sm = new SheetModel();
        //setup
        final Interpreter i = new Interpreter(sm);
        //test input
        final String sourceCode = "=(5+5)*12";
        // code under test
        final Object actualRoot = i.parseAndExecute(sourceCode);
        //expected tree
        final Node expectedRoot = new Multiplication(
                                                        new Addition(
                                                                        new NumberLiteral(5),
                                                                        new NumberLiteral(5))
                                                        , new NumberLiteral(12));
        
        //assertion
        assertEquals(actualRoot.toString(), i.execute(expectedRoot).toString());
    }
    
    @Test
    public void testNullAstTree() {
        final SheetModel sm = new SheetModel();
        final Interpreter i = new Interpreter(sm);
        assertEquals(i.execute(null).toString(),"");
    }
    
    @Test
    public void testStringAstTree() {
        final SheetModel sm = new SheetModel();
        final Interpreter i = new Interpreter(sm);
        final Object res = i.parseAndExecute("TEST");
        assertEquals(res.toString(),"TEST");
    }
    
    @Test
    public void testErrorInterpreting() {
        final SheetModel sm = new SheetModel();
        final Interpreter i = new Interpreter(sm);
        final Object res = i.parseAndExecute("=(5+5)5");
        assertEquals(res.toString(),"=(5+5)5");
    }
}
