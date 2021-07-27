package model.nodes;

import model.program.Add;
import model.program.Program;

/**
 * Addition of doubles.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Addition extends BinaryNode {
    /**
     * Create a new Addition node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Addition(final Node leftChild, final Node rightChild)
    {
        super(leftChild,rightChild);
    }
    
    @Override
    public void compile(final Program p) {
        compile(p,new Add());
    }
    
    @Override
    public String toString() {
        return toString("+");
    }
}