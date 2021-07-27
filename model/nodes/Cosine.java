package model.nodes;

import model.program.Cos;
import model.program.Program;

/**
 * Operator class for the COSINE Function.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Cosine extends OperationNode {

    /**
     * Create a new Cosine node.
     * @param child the operand
     */
    public Cosine(final Node child) {
        super(child);
    }

    @Override
    public void compile(final Program p)  {
        compile(p, new Cos());
    }
    
    @Override
    public String toString() {
        return toString("COS");
    }
    
}
