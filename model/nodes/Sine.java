package model.nodes;

import model.program.Program;
import model.program.Sin;

/**
 * Class for the Sine Operation.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Sine extends OperationNode {
    
    /**
     * Create a new Sine node.
     * @param child the operand
     */
    public Sine(final Node child) {
        super(child);
    }

    @Override
    public void compile(final Program p) {
        compile(p, new Sin());
    }
    
    @Override
    public String toString() {
        return toString("SIN");
    }
    
}
