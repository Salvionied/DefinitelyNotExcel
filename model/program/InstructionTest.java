package model.program;

import model.nodes.*;
import model.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests toString() and execute() of Instruction subclasses.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InstructionTest {
    
    @Test
    public void testToStringNPush() {
        Instruction i = new NPush(1.0);
        assertEquals("NPUSH 1.0", i.toString());
    }
    
    @Test
    public void testExecuteNPush() {
    
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        Instruction i = new NPush(1);
        i.execute(s);
        assertEquals(1, os.pop());
    
    }
    
    @Test
    public void testToStringNeg() {
        Instruction i = new Neg();
        assertEquals("NEG", i.toString());
    }
    
    @Test
    public void testExecuteNeg() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        os.push(1);
        Instruction i = new Neg();
        i.execute(s);
        assertEquals(-1.0, os.pop());
    
    }
    
    @Test
    public void testToStringAdd() {
        Instruction i = new Add();
        assertEquals("ADD", i.toString());
    }
    
    @Test
    public void testExecuteAdd() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        os.push(1);
        os.push(2);
        Instruction i = new Add();
        i.execute(s);
        assertEquals(3.0, os.pop());
    
    }
    
    @Test
    public void testToStringSub() {
        Instruction i = new Sub();
        assertEquals("SUB", i.toString());
    }
    
    @Test
    public void testExecuteSub() {

        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        os.push(3);
        os.push(2);
        Instruction i = new Sub();
        i.execute(s);
        assertEquals(1.0, os.pop());
        
    }
    
    @Test
    public void testNload() {
      
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new NumberLiteral(2.0));
        Storage s = new Storage(os, vt);
        int[] cell= {0,0};
        Instruction i = new Nload(cell);
        i.execute(s);
        assertEquals("NLOAD A0", i.toString());
        assertEquals(2.0,s.getOperandStack().pop());
        
    }
    
    @Test
    public void testMul() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        os.push(3);
        os.push(2);
        Instruction i = new Mul();
        i.execute(s);
        assertEquals(6.0, os.pop());
        assertEquals("MUL", i.toString());
        
    }

    @Test
    public void testDiv() {
    
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os, vt);
        os.push(2);
        os.push(4);
        Instruction i = new Div();
        i.execute(s);
        assertEquals(0.5, os.pop());
        assertEquals("DIV", i.toString());
        
    }
    
    @Test
    public void testInstructions() {
        Instruction i = new Instruction();
        assertEquals("?",i.toString());
    }
    
    @Test
    public void testInstr() {
       
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        Instruction i = new Instruction();
        i.execute(s);
    
    }
    
    @Test
    public void testExp() {
  
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(4);
        os.push(2);
        Instruction i = new Exp();
        i.execute(s);
        assertEquals(16.0, os.pop());
        assertEquals("EXP", i.toString());
        
    }
    
    @Test
    public void testMod() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(3);
        os.push(2);
        Instruction i = new Mod();
        i.execute(s);
        assertEquals(1.0, os.pop());
        assertEquals("MOD", i.toString());
    }

    @Test
    public void testFDiv() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(10);
        os.push(4);
        Instruction i = new FDiv();
        i.execute(s);
        assertEquals(2, os.pop());
        assertEquals("FDIV", i.toString());
    
    }
    
    @Test
    public void testSqr() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(4);
        Instruction i = new Sqr();
        i.execute(s);
        assertEquals(2.0, os.pop());
        assertEquals("SQR", i.toString());
    }
    
    @Test
    public void testSin() {
    
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(30);
        Instruction i = new Sin();
        i.execute(s);
        assertEquals(-0.988, os.pop().doubleValue(),0.001);
        assertEquals("SIN", i.toString());
   
    }
    
    @Test
    public void testCos() {
       
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(60);
        Instruction i = new Cos();
        i.execute(s);
        assertEquals(-0.952, os.pop().doubleValue(), 0.1);
        assertEquals("COS", i.toString());
    
    }
    
    @Test
    public void testTan() {
    
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        Storage s = new Storage(os,vt);
        os.push(45);
        Instruction i = new Tan();
        i.execute(s);
        assertEquals(1.6, os.pop().doubleValue(), 0.2);
        assertEquals("TAN", i.toString());

    }
    
    @Test
    public void testAvg() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new NumberLiteral(2.0));
        vt.set(1,0, new NumberLiteral(5.0));
        Storage s = new Storage(os, vt);
        int[] cells = {0,0,1,0};
        Instruction i = new RangeLoad(cells);
        Instruction avg = new Avg(2);
        i.execute(s);
        avg.execute(s);
        assertEquals("RLOAD A0:A1", i.toString());
        assertEquals("AVG", avg.toString());
        assertEquals(3.5,s.getOperandStack().pop().doubleValue(),0.1);
        
    }
    
    @Test
    public void testSum() {
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new NumberLiteral(2.0));
        vt.set(1,0, new NumberLiteral(5.0));
        Storage s = new Storage(os, vt);
        int[] cells = {0,0,1,0};
        Instruction i = new RangeLoad(cells);
        Instruction avg = new Sum(2);
        i.execute(s);
        avg.execute(s);
        assertEquals("RLOAD A0:A1", i.toString());
        assertEquals("SUM", avg.toString());
        assertEquals(7.0,s.getOperandStack().pop().doubleValue(),0.1);
    
    }
    
    @Test
    public void testMin() {
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new NumberLiteral(2.0));
        vt.set(1,0, new NumberLiteral(5.0));
        Storage s = new Storage(os, vt);
        int[] cells = {0,0,1,0};
        Instruction i = new RangeLoad(cells);
        Instruction avg = new Min(2);
        i.execute(s);
        avg.execute(s);
        assertEquals("RLOAD A0:A1", i.toString());
        assertEquals("MIN", avg.toString());
        assertEquals(2.0,s.getOperandStack().pop().doubleValue(),0.1);
    
    }
    
    @Test
    public void testMax() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new NumberLiteral(12.0));
        vt.set(1,0, new NumberLiteral(5.0));
        vt.set(2,0, new NumberLiteral(0.5));
        vt.set(3,0, new NumberLiteral(1.0));
        Storage s = new Storage(os, vt);
        int[] cells = {0,0,3,0};
        Instruction i = new RangeLoad(cells);
        Instruction avg = new Max(4);
        i.execute(s);
        avg.execute(s);
        assertEquals("RLOAD A0:A3", i.toString());
        assertEquals("MAX", avg.toString());
        assertEquals(12.0,s.getOperandStack().pop().doubleValue(),0.1);
        
    }
    
    @Test
    public void testStringCellInRange() {
        
        OperandStack os = new OperandStack();
        SheetModel vt = new SheetModel();
        vt.set(0,0 , new StringNode("TEST"));
        vt.set(1,0, new NumberLiteral(5.0));
        vt.set(2,0, new NumberLiteral(0.5));
        vt.set(3,0, new NumberLiteral(1.0));
        Storage s = new Storage(os, vt);
        int[] cells = {0,0,3,0};
        Instruction i = new RangeLoad(cells);
        Instruction avg = new Sum(4);
        i.execute(s);
        avg.execute(s);
        assertEquals("RLOAD A0:A3", i.toString());
        assertEquals("SUM", avg.toString());
        assertEquals(6.5,s.getOperandStack().pop().doubleValue(),0.1);
        
    }
}
