package model.nodes;

import model.program.Program;
import model.program.Sqr;


/**
 * A double Square Root.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SquareRoot extends OperationNode {
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public SquareRoot(final Node child) {
        super(child);
    }

 
    @Override
    public void compile(final Program p)  {
        compile(p, new Sqr());
    }
    
    @Override
    public String toString() {
        return toString("SQRT");
    }
    
}
