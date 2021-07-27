package model.nodes;

import model.language.ParseHelper;
import model.program.FunctionInstruction;
import model.program.Program;

/**
 * FunctionNode Parent class
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class FunctionNode extends NumberNode
{
    private final Node child;
    public int elements;
    
    /**
     * Constructor for the Basic Parent Class of a Function Node.
     * 
     * @param child Node object contained within the function.
     */
    public FunctionNode(final Node child) {
        super();
        this.child = child;
        if (child.getType() == Type.RANGE) {
            elements = RangeHelper.countCells(
            ParseHelper.rangeParser(child.toString()));
        } else {
            elements = 1;
        }
    }
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    @Override
    public Type getType() {
        return Type.NUMBER;
    }
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    @Override
    public boolean isConstant() {
        return false;
    }
    
    /**
     * Method to Compile the Binary Operation.
     * 
     * @param p The program
     * @param fi the Binary Instruction to append.
     */
    public void compile(final Program p,final FunctionInstruction fi) {
        child.compile(p);
        p.append(fi);
    }
    
    /**
     * Method to Stringify the Binary Operation.
     * 
     * @param symbol the symbol of the Instruction to stringify
     * @return String representing the Binary Operation
     */
    public String toString(final String symbol) {
        return symbol + "(" + child.toString() + ")";
    }
    
}
