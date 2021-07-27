package model.language;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TokenFactoryTest {
    @Test
    public void tesTokenFactory() {
        TokenFactory tf = new TokenFactory();
        tf.setText("Test");
        assertFalse(tf.find(2));
        assertEquals(-1,tf.getTokenLength());
        assertNull(tf.getToken());
    }
}
