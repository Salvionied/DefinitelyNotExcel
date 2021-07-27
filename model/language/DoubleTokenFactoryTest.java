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
public class DoubleTokenFactoryTest {
    @Test
    public void testFoundLength3() {
        DoubleTokenFactory f = new DoubleTokenFactory();
        f.setText("a=9.1");
        boolean found = f.find(2);
        assertTrue(found);
        assertEquals(3, f.getTokenLength());
        assertEquals(2, f.getTokenStartPosition());
        assertEquals("9.1", f.getTokenText());
        assertEquals(TokenType.DOUBLE, f.getToken().getType());
    }
    
    @Test
    public void testFoundLength6() {
        DoubleTokenFactory f = new DoubleTokenFactory();
        f.setText("abc=156.99x");
        boolean found = f.find(4);
        assertTrue(found);
        assertEquals(6, f.getTokenLength());
        assertEquals(4, f.getTokenStartPosition());
        assertEquals("156.99", f.getTokenText());
        assertEquals(TokenType.DOUBLE, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        DoubleTokenFactory f = new DoubleTokenFactory();
        f.setText("abc=xyz");
        boolean found = f.find(4);
        assertFalse(found);
    }
    
}