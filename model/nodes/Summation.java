package model.nodes;

import model.program.Program;
import model.program.Sum;

/**
 * Class for The SUM function
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Summation extends FunctionNode
{
    
    /**
     * Constructor for FunctionNode of type Summation.
     * 
     * @param range the range of Cells to sum
     */
    public Summation(final Node range) {
        super(range);
    }
    
    /**
     * Method for Compiling this FunctionNode.
     * 
     * @param p the instance of Program
     */
    public void compile(final Program p) {
        compile(p,new Sum(elements));
    }
    
    @Override
    public String toString() {
        return toString("SUM");
    }
}
