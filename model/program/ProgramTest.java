package model.program;

import model.nodes.NumberNode;
import model.ModelException;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class For the Program class.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class ProgramTest {
    
    @Test
    public void testEmpty() {
        Program p = new Program();
        assertEquals(0, p.getLength());
        assertEquals("", p.toString());
    }

    @Test
    public void testAppend() {
    
        Program p = new Program();
        NumberNode nn = new NumberNode();
        nn.compile(p);
        Instruction i = new NPush(1);
        p.append(i);
        assertEquals(1, p.getLength());
        assertSame(i, p.get(0));
        assertEquals("  " + i.toString() + "\n", p.toString());
        
    }
    
    @Test
    public void testAppend2() {
        Program p = new Program();
        Instruction i1 = new NPush(1);
        Instruction i2 = new NPush(2);
        p.append(i1);
        p.append(i2);
        assertEquals(2, p.getLength());
        assertSame(i1, p.get(0));
        assertSame(i2, p.get(1));
        assertEquals(
            "  " + i1.toString() + "\n" +
            "  " + i2.toString() + "\n", 
            p.toString());
    }
    
    @Test
    public void testExecute() {
    
        Program p = new Program();
        p.append(new NPush(2));
        assertEquals(2, p.execute());
    
    }

}
