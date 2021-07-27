package model;

import model.program.Program;
import model.nodes.NumberLiteral;
import model.nodes.NumberNode;
import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SheetModelTest.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SheetModelTest
{
    @Test
    public void testSheetModel() {
        SheetModel sm = new SheetModel();
        sm.set(2,2, new NumberLiteral(2.0));
        assertEquals(sm.get(2,2).toString(), new NumberLiteral(2.0).toString());
    }
    
    @Test
    public void testResetSheetModel() {
        SheetModel sm = new SheetModel();
        sm.set(2,2, new NumberLiteral(2.0));
        assertEquals(sm.get(2,2).toString(), new NumberLiteral(2.0).toString());
        sm.resetSheetModel(50,100);
        assertNull(sm.get(2,2));
    }
    
    @Test
    public void testSetParse() {
        SheetModel sm = new SheetModel();
        sm.setParse(2,2, "=2");
        assertEquals(sm.get(2,2).toString(), new NumberLiteral(2.0).toString());
    }
    
    @Test
    public void testWidthHeight() {
        SheetModel sm = new SheetModel(10,10);
        assertEquals(11, sm.getHeight());
        assertEquals(11, sm.getWidth(1));
    }
    
    @Test
    public void testGet() {
        SheetModel sm = new SheetModel();
        assertNull(sm.get(0,1));
        assertNull(sm.get(2,2));
        assertNull(sm.get(0,-1));
        assertNull(sm.get(-1,0));
    }
    
    @Test
    public void testSet() {
        SheetModel sm = new SheetModel();
        sm.set(-1,0,new NumberLiteral(2.0));
        sm.set(0,-1, new NumberLiteral(2.0));
        sm.set(0,2, new NumberLiteral(2.0));
        assertEquals(sm.get(0,2).toString(),"2.0");
        sm.set(2,0, new NumberLiteral(2.0));
        assertEquals(sm.get(2,0).toString(),"2.0");
        sm.set(0,5, new NumberLiteral(2.0));
        assertEquals(sm.get(0,5).toString(), "2.0");
    }
    
    @Test
    public void testAddListenerSetCell() {
        final SheetModel sheetm = new SheetModel();
        class TestSheetModelListener implements SheetModelListener {
            public boolean gotNotified = false;
            public boolean gotNotifiedForSheetModel = false;
            public void sheetModelChanged(SheetModel sm) {
                gotNotified = true;
                gotNotifiedForSheetModel = sm == sheetm;
            }
        }
        TestSheetModelListener sml = new TestSheetModelListener();
        sheetm.addSheetModelListener(sml);
        sheetm.setParse(1,1,"2.0");
        assertTrue(sml.gotNotified);
        assertTrue(sml.gotNotifiedForSheetModel);
    }
    
    @Test
    public void testRemoveListenerSetCell() {
        final SheetModel sheetm = new SheetModel();
        class TestSheetModelListener implements SheetModelListener {
            public boolean gotNotified = false;
            public boolean gotNotifiedForSheetModel = false;
            public void sheetModelChanged(SheetModel sm) {
                gotNotified = true;
                gotNotifiedForSheetModel = sm == sheetm;
            }
        }
        TestSheetModelListener sml = new TestSheetModelListener();
        sheetm.addSheetModelListener(sml);
        sheetm.removeSheetModelListener(sml);
        sheetm.setParse(1,1,"2.0");
        assertFalse(sml.gotNotified);
        assertFalse(sml.gotNotifiedForSheetModel);
    }
    
    @Test
    public void TestSave() {
        boolean exceptionThrown = false;
        try {
            SheetModel nt = new SheetModel(5,5);
            nt.setFileName("test1out");
            nt.saveSheet();
            NumberNode test = new NumberLiteral(12);
            nt.set(0,0,test);
            assertEquals(nt.get(0,0).toString(), "12.0");
            nt.setFileName("test1out");
            nt.saveSheet();
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }
    
    @Test
    public void TestSaveSwap() {
        boolean exceptionThrown = false;
        try {
            SheetModel nt = new SheetModel(5,5);
            nt.setFileName("test2");
            nt.saveSheet();
            NumberNode test1 = new NumberLiteral(12);
            nt.set(1,1,test1);
            NumberNode test2 = new NumberLiteral(23);
            nt.set(1,1,test2);
            assertEquals(nt.get(1,1).toString(), "23.0");
            nt.setFileName("test2out");
            nt.saveSheet();
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertFalse(exceptionThrown);
    }
    
    @Test
    public void TestSaveSwapLoad() {
        SheetModel nt = new SheetModel(5,5);
    
        nt.set(5, 5, new NumberLiteral(12));
        nt.set(1, 1, new NumberLiteral(23));
        boolean ExceptionThrown = false;
        try {
            nt.setFileName("test2");
            nt.saveSheet();
            SheetModel nt2 = new SheetModel();
            nt2.loadSheet(System.getProperty("user.dir") + "/" + "test2.csv",100,50);
            assertEquals(nt2.get(1,1).toString(), "23.0");
            nt2.setFileName("test2out");
            nt2.saveSheet();
        } catch (IOException e) {
            boolean ExceptionThrouwn = true;
        }
        assertFalse(ExceptionThrown);
    }
    
    @Test
    public void TestErrorDir() {
        SheetModel sm = new SheetModel();
        boolean exceptionThrown = false;
        try {
            sm.saveSheet();
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void TestErrorDir2() {
        SheetModel sm = new SheetModel();
        boolean exceptionThrown = false;
        try {
            sm.setPath(System.getProperty("user.dir")+"/");
            sm.saveSheet();
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    @Test
    public void TestExceptions1Load() {
        SheetModel sm = new SheetModel();
        boolean exceptionThrown = false;
        try {
            sm.loadSheet("",100,50);
            sm.loadSheet("/",100,50);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
  
    @Test
    public void TestExceptions2Load() {
        SheetModel sm = new SheetModel();
        boolean exceptionThrown = false;
        try {
            sm.loadSheet("hello.mp3",100,50);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    @Test
    public void TestExceptions3Load() {
        SheetModel sm = new SheetModel();
        boolean exceptionThrown = false;
        try {
            sm.loadSheet("/",100,50);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    @Test
    public void testLoadCell() {
        boolean thrown = false;
        try {
            final SheetModel sm = new SheetModel();
            sm.setParse(0,0, "2.0");
            sm.setParse(0,1, "=A0+5");
            assertEquals(sm.get(0,1).toString(),"(A0+5.0)");
            Program p = new Program();
            sm.get(0,1).compile(p);
            final double result = p.execute(sm).doubleValue();
            assertEquals(7.0,result,0.01);
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testSaveLoadTable() throws Exception {
        boolean thrown = false;
        try {
            final SheetModel sm = new SheetModel();
            sm.setParse(0,0, "2.0");
            sm.setParse(0,1, "Hello");
            sm.setParse(0,2, "=5+5");
            sm.setPath(System.getProperty("user.dir")+"/" +"TestSaveLoad.csv");
            sm.saveSheet();
            sm.resetSheetModel(50,50);
            assertNull(sm.get(0,0));
            
            sm.loadSheet("TestSaveLoad.csv",100,50);
            assertEquals("Hello",sm.get(0,1).toString());
            assertEquals("(5.0+5.0)",sm.get(0,2).toString());
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    
    @Test
    public void testSetParseProgramError() {
        final SheetModel sm = new SheetModel();
        sm.setParse(0,1, "=A0");
        assertEquals(sm.get(0,1).toString(),"A0");
        sm.setParse(0,2, "=HELLO");
        assertEquals(sm.get(0,2).getValue(), "Error: The function " + "\"" + "HELLO" + "\"" +  " is not defined.");
        
        sm.setParse(0,3, "=5+");
        assertEquals(sm.get(0,3).getValue(), "Error: Operator requires two arguments.");
        
        sm.setParse(0,4, "=(5+5)5");
        assertEquals(sm.get(0,4).getValue(),"Error: Operator expected.");
        
        sm.setParse(0,5, "=(5+5)E2");
        assertEquals(sm.get(0,5).getValue(),"Error: Operator expected.");
        sm.setParse(0,6, "=(5+5)E2:I3");
        assertEquals(sm.get(0,6).getValue(),"Error: Operator expected.");
    }
    
    @Test 
    public void testCellUndefinedTriesToGetLoaded() {
        final SheetModel sm = new SheetModel();
        final Program p = new Program();
        sm.setParse(0,1, "=A0/5");
        sm.get(0,1).compile(p);
        assertEquals(0.0,(p.execute(sm).doubleValue()),0.1);
    
    }
    
    @Test
    public void testGetCellResult() {
        final SheetModel sm = new SheetModel();
        sm.setParse(0,1,"=A0/5");
        assertEquals("0.0", sm.getCellResult(0,1).toString());
    }
    
    @Test
    public void testInvalidRange() {
        final SheetModel sm = new SheetModel();
        sm.setParse(0,1,"=SUM(13)");
        assertEquals("13.0", sm.getCellResult(0,1).toString());
    }
}
