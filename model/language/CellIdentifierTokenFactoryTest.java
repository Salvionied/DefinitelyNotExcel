package model.language;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public class CellIdentifierTokenFactoryTest {
       
    @Test
    public void testFoundLength1() {
        CellIdentifierTokenFactory f = new CellIdentifierTokenFactory();
        f.setText("A0");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(2, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("A0", f.getTokenText());
        assertEquals(TokenType.CELLIDENTIFIER, f.getToken().getType());
    }
    
    @Test
    public void testFoundLength3() {
        CellIdentifierTokenFactory f = new CellIdentifierTokenFactory();
        f.setText("AA12");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(4, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("AA12", f.getTokenText());
        assertEquals(TokenType.CELLIDENTIFIER, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        CellIdentifierTokenFactory f = new CellIdentifierTokenFactory();
        f.setText("123=456");
        boolean found = f.find(4);
        assertFalse(found);
    }

}

