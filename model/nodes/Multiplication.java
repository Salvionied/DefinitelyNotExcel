package model.nodes;

import model.program.Mul;
import model.program.Program;

/**
 * Multiplication Expression tree class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Multiplication extends BinaryNode
{
    /**
     * Constructor for objects of class Multiplication.
     * 
     * @param leftChild parameter representing the Value at the left of the multiplication sign
     * @param rightChild parameter representing the Value at the right of the multiplication sign
     */
    public Multiplication(final Node leftChild, final Node rightChild)
    {
        super(leftChild,rightChild);
    }
    
    @Override
    public void compile(final Program p) {
        compile(p,new Mul());
    }
    
    @Override
    public String toString() {
        return toString("*");
    }
}
