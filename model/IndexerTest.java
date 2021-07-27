package model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class IndexerTest.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class IndexerTest
{
    @Test
    public void testcorrectIndex() {
        Indexer i = new Indexer();
        assertEquals(0, i.getIndex("A"));
        assertEquals(25, i.getIndex("Z"));
        assertEquals(26, i.getIndex("AA"));
    }
    
    @Test
    public void testInvalidIndex() {
        Indexer i = new Indexer();
        assertEquals(-1, i.getIndex("AAA"));
        assertEquals(-1, i.getIndex("aa"));
        assertEquals(-1, i.getIndex("A12"));
    }
    
    @Test
    public void testName() {
        Indexer i = new Indexer();
        assertEquals("A", i.getAlpha(0));
        assertEquals("AA",i.getAlpha(26));
    }
}
