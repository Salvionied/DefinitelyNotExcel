package model.nodes;

import model.program.Min;
import model.program.Program;

/**
 * Class for The MIN function
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Minimum extends FunctionNode
{
    /**
     * Constructor for FunctionNode of type Min.
     * 
     * @param range the Range of Cells
     */
    public Minimum(final Node range) {
        super(range);
    }
    
    /**
     * Method for compiling this FunctionNode.
     * 
     * @param p the Program Instance
     */
    public void compile(final Program p) {
        compile(p,new Min(elements));
    }
    
    @Override
    public String toString() {
        return toString("MIN");
    }
}