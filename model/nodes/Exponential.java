package model.nodes;

import model.program.Exp;
import model.program.Program;

/**
 * Power Expression tree class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Exponential extends BinaryNode {

    /**
     * Constructor for objects of class Exponential.
     * 
     * @param leftChild parameter representing the Value at the left of the exponential sign
     * @param rightChild parameter representing the Value at the right of the exponential sign
     */
    public Exponential(final Node leftChild, final Node rightChild)
    {
        super(leftChild,rightChild);
    }
    
    @Override
    public void compile(final Program p)  {
        compile(p,new Exp());
    }
    
    @Override
    public String toString() {
        return toString("^");
    }
}
