package model.nodes;

import model.program.Program;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests toString(), isConstant(), and getType() of Node subclasses.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class NodeTest {
    @Test
    public void testNode() {
        Node e = new Node();
        assertEquals(Type.INVALID, e.getType());
        assertEquals(true, e.isConstant());
        assertEquals("", e.toString());
        assertEquals("",e.getValue());
    }
    
    @Test 
    public void testNumberNode() {
        Node e = new NumberNode();
        assertEquals(Type.NUMBER, e.getType());
    }
    
    @Test
    public void testNumberLiteral() {
        Node e = new NumberLiteral(5);
        assertTrue(e.isConstant());
        assertEquals("5.0", e.toString());
        assertEquals(Type.NUMBER, e.getType());
    }
    
    @Test
    public void testNumberVariable() {
        int[] cell = {0,0};
        Node e = new NumberVariable(cell);
        assertFalse(e.isConstant());
        assertEquals(Type.NUMBER, e.getType());
        assertEquals("A0", e.toString());
    }
    
    
    @Test
    public void testNumberNegation() {
        Node e = new Negation(new NumberLiteral(5));
        assertTrue(e.isConstant());
        assertEquals("(-5.0)", e.toString());
        assertEquals(Type.NUMBER, e.getType());
    }
   
    
    @Test
    public void testNumberAddition() {
        
        Node e = new Addition(new NumberLiteral(5), new NumberLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5.0+6.0)", e.toString());
        assertEquals(Type.NUMBER, e.getType());
        
        int[] cell = {0,0};
        int[] celltwo = {0,1};
        Node f = new Addition( new NumberLiteral(5), new NumberVariable(cell));
        assertFalse(f.isConstant());
        assertEquals("(5.0+A0)", f.toString());
        Node g = new Addition(new NumberVariable(celltwo), new NumberVariable (cell));
        assertFalse(g.isConstant());
        assertEquals("(B0+A0)",g.toString());
    }
    
    
    
    @Test
    public void testSubtraction() {
        Node e = new Subtraction(new NumberLiteral(5), new NumberLiteral(6));
        assertTrue(e.isConstant());
        assertEquals("(5.0-6.0)", e.toString());
        assertEquals(Type.NUMBER, e.getType());
        
        int[] cell = {0,0};
        int[] celltwo = {0,1};
        Node f = new Subtraction(new NumberLiteral(5), new NumberVariable(cell));
        assertFalse(f.isConstant());
        assertEquals("(5.0-A0)", f.toString());
        
        Node g = new Subtraction(new NumberVariable(cell), new NumberVariable (celltwo));
        assertFalse(g.isConstant());
        assertEquals("(A0-B0)",g.toString());
    }
    
    
    @Test
    public void testMultiplication() {
        Node e = new Multiplication(new NumberLiteral(5), new NumberLiteral(3));
        assertTrue(e.isConstant());
        assertEquals("(5.0*3.0)",e.toString());
        assertEquals(Type.NUMBER, e.getType());
        
        int[] cell = {0,0};
        int[] celltwo = {0,1};
        Node f = new Multiplication(new NumberLiteral(5), new NumberVariable(cell));
        assertFalse(f.isConstant());
        assertEquals("(5.0*A0)", f.toString());
        
        Node g = new Multiplication(new NumberVariable(cell), new NumberVariable (celltwo));
        assertFalse(g.isConstant());
        assertEquals("(A0*B0)",g.toString());
    }
    
    
    @Test
    public void testDivision() {
        Node e = new Division(new NumberLiteral(5), new NumberLiteral(3));
        assertTrue(e.isConstant());
        assertEquals("(5.0/3.0)",e.toString());
        assertEquals(Type.NUMBER, e.getType());
        
        int[] cell = {0,0};
        int[] celltwo = {0,1};
        Node f = new Division(new NumberLiteral(5), new NumberVariable(cell));
        assertFalse(f.isConstant());
        assertEquals("(5.0/A0)", f.toString());
        
        Node g = new Division(new NumberVariable(cell), new NumberVariable (celltwo));
        assertFalse(g.isConstant());
        assertEquals("(A0/B0)",g.toString());
    }
    
    @Test
    public void testStringNode() {
        StringNode e = new StringNode("Hello");
        assertEquals(Type.STRING, e.getType());
        assertEquals("Hello",e.toString());
        assertEquals("Hello",e.getValue());
    }
    
    @Test
    public void testModulo() {
        Node e = new Modulo(new NumberLiteral(5), new NumberLiteral(3));
        assertTrue(e.isConstant());
        assertEquals("(5.0%3.0)", e.toString());
    }
    
    @Test
    public void testExp() {
        Node e = new Exponential(new NumberLiteral(5), new NumberLiteral(3));
        assertTrue(e.isConstant());
        assertEquals("(5.0^3.0)",e.toString());
    
    }
    
    @Test
    public void testFDiv() {
        Node e = new FloorDivision(new NumberLiteral(5), new NumberLiteral(3));
        assertTrue(e.isConstant());
        assertEquals("(5.0_/3.0)",e.toString());
    
    }
    
    @Test
    public void testSine() {
        Node e = new Sine(new NumberLiteral(5));
        assertTrue(e.isConstant());
        assertEquals("SIN(5.0)",e.toString());
    
    }
    
    @Test
    public void testCosine() {
        Node e = new Cosine(new NumberLiteral(5));
        assertTrue(e.isConstant());
        assertEquals("COS(5.0)",e.toString());
    
    }
    
    @Test
    public void testTangent() {
        Node e = new Tangent(new NumberLiteral(5));
        assertTrue(e.isConstant());
        assertEquals("TAN(5.0)",e.toString());
    
    }
    
    @Test
    public void testSqr() {
        Node e = new SquareRoot(new NumberLiteral(4));
        assertTrue(e.isConstant());
        assertEquals("SQRT(4.0)",e.toString());
        
        int[] cell = {0,0};
        Node f = new SquareRoot(new NumberVariable(cell));
        assertFalse(f.isConstant());
        assertEquals("SQRT(A0)",f.toString());
    }
    
    @Test
    public void testFunctionNode() {
        int[] cells = {0,0,1,0};
        Node e = new FunctionNode(new RangeNode(cells));
        assertEquals(e.getType(), Type.NUMBER);
        assertFalse(e.isConstant());
    }
    
    @Test
    public void testErrorNode() {
        ErrorNode e = new ErrorNode("=(5*5)*","Operator requires 2 arguments.");
        assertEquals(e.toString(),"=(5*5)*");
        assertEquals(e.getValue(),"Operator requires 2 arguments.");
        assertEquals(e.getType(), Type.STRING);
    }
    
    @Test
    public void invalidRange() {
        Node e = new FunctionNode(new NumberLiteral(12));
        assertEquals(e.getType(), Type.NUMBER);
    }
}