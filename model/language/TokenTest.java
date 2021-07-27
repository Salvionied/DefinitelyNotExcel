package model.language;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TokenTest {
    
    @Test
    public void testLength1() {
        Token t = new Token(TokenType.PLUS, "+", 0);
        assertEquals(TokenType.PLUS, t.getType());
        assertEquals("+", t.getText());
        assertEquals(0, t.getStartPosition());
        assertEquals(1, t.getEndPosition());
    }
    
    @Test
    public void testLength2() {
        Token t = new Token(TokenType.CELLIDENTIFIER, "3:1", 0);
        assertEquals(TokenType.CELLIDENTIFIER, t.getType());
        assertEquals("3:1", t.getText());
        assertEquals(0, t.getStartPosition());
        assertEquals(3, t.getEndPosition());
    }
    
    @Test
    public void testLength3() {
        Token t = new Token(TokenType.LITERAL, "456", 60);
        assertEquals(TokenType.LITERAL, t.getType());
        assertEquals("456", t.getText());
        assertEquals(60, t.getStartPosition());
        assertEquals(63, t.getEndPosition());
    }
    
}