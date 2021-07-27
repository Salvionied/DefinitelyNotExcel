package model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for State Saving Functionalities of the SheetModel
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class CaretakerTest
{
    @Test
    public void testUndo() {
        SheetModel sm = new SheetModel();
        sm.setParse(0,0,"Expected");
        sm.add();
        sm.setParse(0,0,"Wrong Value");
        sm.add();
        sm.undo();
        assertEquals("Expected", sm.get(0,0).toString());
    }
    
    @Test
    public void testRedo() {
        SheetModel sm = new SheetModel();
        sm.setParse(0,0,"Expected");
        sm.add();
        sm.setParse(0,0,"Wrong Value");
        sm.add();
        sm.undo();
        assertEquals("Expected", sm.get(0,0).toString());
        sm.redo();
        assertEquals("Wrong Value", sm.get(0,0).toString());
    }
    
    @Test
    public void testUndoUndoRedoRedo() {
        SheetModel sm = new SheetModel();
        sm.setParse(0,0,"Expected");
        sm.add();
        sm.setParse(0,0,"Wrong Value");
        sm.add();
        sm.setParse(0,0,"AnotherWrongValue");
        sm.add();
        sm.undo();
        assertEquals("Wrong Value", sm.get(0,0).toString());
        sm.undo();
        assertEquals("Expected", sm.get(0,0).toString());
        sm.redo();
        assertEquals("Wrong Value", sm.get(0,0).toString());
        sm.redo();
        assertEquals("AnotherWrongValue", sm.get(0,0).toString());
    }
    
    @Test
    public void testLimitMemory() {
        final SheetModel sm = new SheetModel();
        for (int i=0; i < 50; i++ ) {
            sm.setParse(0,0,Integer.toString(i));
            sm.add();
        }
        sm.undo();
        assertEquals("48.0", sm.get(0,0).toString());
    }
    
    @Test
    public void testLimitUndo() {
        SheetModel sm = new SheetModel();
        sm.setParse(0,0,"Expected");
        sm.add();
        sm.undo();
        sm.undo();
        assertNull(sm.get(0,0));
    }
    
    @Test
    public void testLimitRedo() {
        SheetModel sm = new SheetModel();
        sm.setParse(0,0,"Expected");
        sm.add();
        sm.undo();
        sm.undo();
        sm.redo();
        sm.redo();
        sm.redo();
        assertEquals("Expected",sm.get(0,0).toString());
    }
}
