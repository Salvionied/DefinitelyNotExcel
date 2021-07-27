package model.language;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for Range Tokens.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class RangeTokenFactoryTest {
       
    @Test
    public void testFoundLength1() {
        RangeTokenFactory f = new RangeTokenFactory();
        f.setText("A0:B0");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(5, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("A0:B0", f.getTokenText());
        assertEquals(TokenType.RANGE, f.getToken().getType());
    }
    
    @Test
    public void testFoundLength3() {
        RangeTokenFactory f = new RangeTokenFactory();
        f.setText("AA1:AA12");
        boolean found = f.find(0);
        assertTrue(found);
        assertEquals(8, f.getTokenLength());
        assertEquals(0, f.getTokenStartPosition());
        assertEquals("AA1:AA12", f.getTokenText());
        assertEquals(TokenType.RANGE, f.getToken().getType());
    }
    
    @Test
    public void testNoMatchNotFound() {
        RangeTokenFactory f = new RangeTokenFactory();
        f.setText("123=456");
        boolean found = f.find(4);
        assertFalse(found);
    }

}