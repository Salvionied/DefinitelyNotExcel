package model.nodes;


import model.program.Avg;
import model.program.Program;


/**
 * Class for The AVERAGE function
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Average extends FunctionNode
{
    
    /**
     * Constructor For Average FunctionNode.
     * 
     * @param range the Range of cells.
     */
    public Average(final Node range) {
        super(range);
    }
    
    /**
     * Compile the Average function.
     * 
     * @param  p the Program object that compiles the Node.
     */
    public void compile(final Program p) {
        compile(p,new Avg(elements));
    }
    
    @Override
    public String toString() {
        return toString("AVG");
    }
}