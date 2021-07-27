package model.nodes;

import model.program.Program;
import model.program.Sub;

/**
 * Subtraction Expression tree.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Subtraction extends BinaryNode {
    /**
     * Create a new Subtraction node.
     * @param leftChild the left operand
     * @param rightChild the right operand
    */
    public Subtraction(final Node leftChild, final Node rightChild)
    {
        super(leftChild,rightChild);
    }
    
    @Override
    public void compile(final Program p)  {
        compile(p,new Sub());
    }
    
    @Override
    public String toString() {
        return toString("-");
    }
    
}
