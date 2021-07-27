package model.nodes;

import model.program.Max;
import model.program.Program;

/**
 * Class for The MAX function
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Maximum extends FunctionNode
{
    /**
     * Constructor for NodeFunction of Type Maximum.
     * @param range the RangeNode.
     */
    public Maximum(final Node range) {
        super(range);

    }
    
    /**
     * Method for Compiling this Function Node.
     * 
     * @param p the program instance
     */
    public void compile(final Program p) {
        compile(p,new Max(elements));
    }
    
    @Override
    public String toString() {
        return toString("MAX");
    }
}