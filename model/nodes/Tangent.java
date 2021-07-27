package model.nodes;

import model.program.Program;
import model.program.Tan;

/**
 * Operation Class for the Tangent Trygonometric function.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Tangent extends OperationNode {

    /**
     * Create a new Tangent node.
     * @param child the operand
     */
    public Tangent(final Node child) {
        super(child);
    }

    @Override
    public void compile(final Program p) {
        compile(p, new Tan());
    }
    
    @Override
    public String toString() {
        return toString("TAN");
    }
    
}

