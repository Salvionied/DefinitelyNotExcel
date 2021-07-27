package model.nodes;

import model.program.FDiv;
import model.program.Program;

/**
 * Division expression tree class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class FloorDivision extends BinaryNode
{
    /**
     * Constructor for objects of class Division.
     * 
     * @param leftChild value representing the element on the left of the division sign
     * @param rightChild value representing the element on the right of the division sign
     * 
     */
    public FloorDivision(final Node leftChild, final Node rightChild)
    {
        super(leftChild,rightChild);
    }
    
    @Override
    public void compile(final Program p) {
        compile(p,new FDiv());
    }
    
    @Override
    public String toString() {
        return toString("_/");
    }
}
