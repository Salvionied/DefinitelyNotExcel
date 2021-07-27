package model.nodes;


import model.program.Instruction;
import model.program.Program;

/**
 * Class for single Argument operators.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class OperationNode extends NumberNode
{
    private final Node child;
    
    /**
     * Constructor for OperationNode.
     * @param child the Node onto which the operators has effect
     */
    public OperationNode(final Node child) {
        super();
        this.child = child;
    }
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    @Override
    public boolean isConstant() {
        // to be implemented in subclasses
        return child.isConstant();
    }
    
    /**
     * Method to Compile the Binary Operation.
     * 
     * @param p The program
     * @param i the Instruction to append.
     */
    public void compile(final Program p,final Instruction i) {
        child.compile(p);
        p.append(i);
    }
    
    /**
     * Method to Stringify the Single argument operation.
     * 
     * @param symbol the symbol of the Instruction to stringify
     * @return String representing the operator
     */
    public String toString(final String symbol) {
        return symbol + "(" + child.toString() + ")";
    }
}
