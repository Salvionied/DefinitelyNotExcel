package model.program;

import model.nodes.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test compile() of Node subclasses
 * (and toString() of Instruction subclasses).
 * This tests that the compile() methods generate the correct
 * sequence of Instructions.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class CompilerTest {
    
    @Test 
    public void testNode() {
    
        Compiler c = new Compiler();
        Node n = new Node();
        Program p = c.compile(n);
        assertEquals(0, p.getLength());
    
    }
    
    @Test
    public void testLiteral() {
   
        Compiler c = new Compiler();
        Node n = new NumberLiteral(5);
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(0).toString());
        
    }

    @Test
    public void testNegation() {
   
        Compiler c = new Compiler();
        Node n = new Negation(new NumberLiteral(5));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Neg);
            
    }
    
    @Test
    public void testAddition() {
   
        Compiler c = new Compiler();
        Node n = new Addition(new NumberLiteral(5), new NumberLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(6.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Add);
            
    }
    
    @Test
    public void testSubtraction() {
        Compiler c = new Compiler();
        Node n = new Subtraction(new NumberLiteral(5), new NumberLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(6.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Sub);
        
    }
    
    @Test
    public void testVariable() {
   
        Compiler c = new Compiler();
        int[] cell = {1,1};
        Node n = new NumberVariable(cell);
        Program p = c.compile(n);
        assertEquals(1, p.getLength());
        assertTrue(p.get(0) instanceof Nload);
        assertEquals(new Nload(cell).toString(), p.get(0).toString());
        
    }
    
    @Test
    public void testMultiplication() {
   
        Compiler c = new Compiler();
        Node n = new Multiplication(new NumberLiteral(5), new NumberLiteral(6));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(6.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Mul);
            
    }
    
    @Test
    public void testDivision() {
    
        Compiler c = new Compiler();
        Node n = new Division(new NumberLiteral(10), new NumberLiteral(2));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(10.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(2.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Div);
       
    }
    
    @Test
    public void testModulo() {
    
        Compiler c = new Compiler();
        Node n = new Modulo(new NumberLiteral(3), new NumberLiteral(5));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(3.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Mod);
            
    }
    
    @Test
    public void testExponential() {
   
        Compiler c = new Compiler();
        Node n = new Exponential(new NumberLiteral(3), new NumberLiteral(5));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(3.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(5.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof Exp);
        
    }
    
    @Test
    public void testSquareRoot() {
    
        Compiler c = new Compiler();
        Node n = new SquareRoot(new NumberLiteral(16));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(16.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Sqr);
           
    }
    
    @Test
    public void testSine() {
   
        Compiler c = new Compiler();
        int[] cell = {1,1};
        Node n = new Sine(new NumberVariable(cell));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof Nload);
        assertEquals(new Nload(cell).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Sin);
        
    }
    
    @Test
    public void testCosine() {
        
        Compiler c = new Compiler();
        int[] cell = {1,1};
        Node n = new Cosine(new NumberVariable(cell));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof Nload);
        assertEquals(new Nload(cell).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Cos);
            
    }
    
    @Test
    public void testTangent() {
   
        Compiler c = new Compiler();
        int[] cell = {1,1};
        Node n = new Tangent(new NumberVariable(cell));
        Program p = c.compile(n);
        assertEquals(2, p.getLength());
        assertTrue(p.get(0) instanceof Nload);
        assertEquals(new Nload(cell).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Tan);
            
    }
    
    @Test
    public void testFloorDivision() {
    
        Compiler c = new Compiler();
        Node n = new FloorDivision(new NumberLiteral(15), new NumberLiteral(2));
        Program p = c.compile(n);
        assertEquals(3, p.getLength());
        assertTrue(p.get(0) instanceof NPush);
        assertEquals(new NPush(15.0).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof NPush);
        assertEquals(new NPush(2.0).toString(), p.get(1).toString());
        assertTrue(p.get(2) instanceof FDiv);
           
    }
    
    @Test
    public void testSummation() {
        
        Compiler c = new Compiler();
        int[] cells = {0,0,1,0};
        Node n = new Summation(new RangeNode(cells));
        Program p = c.compile(n);
        assertTrue(p.get(0) instanceof RangeLoad);
        assertEquals(new RangeLoad(cells).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Sum);
    
    }
    
    @Test
    public void testMinimum() {
       
        Compiler c = new Compiler();
        int[] cells = {0,0,1,0};
        Node n = new Minimum(new RangeNode(cells));
        Program p = c.compile(n);
        assertTrue(p.get(0) instanceof RangeLoad);
        assertEquals(new RangeLoad(cells).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Min);
       
    }
    
    @Test
    public void testAverage() {
   
   
        Compiler c = new Compiler();
        int[] cells = {0,0,1,0};
        Node n = new Average(new RangeNode(cells));
        Program p = c.compile(n);
        assertTrue(p.get(0) instanceof RangeLoad);
        assertEquals(new RangeLoad(cells).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Avg);
    
    }
    
    @Test
    public void testMaximum() {
    
        Compiler c = new Compiler();
        int[] cells = {0,0,1,0};
        Node n = new Maximum(new RangeNode(cells));
        Program p = c.compile(n);
        assertTrue(p.get(0) instanceof RangeLoad);
        assertEquals(new RangeLoad(cells).toString(), p.get(0).toString());
        assertTrue(p.get(1) instanceof Max);
    
    }
    
}
