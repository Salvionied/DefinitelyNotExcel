package model.nodes;

import model.program.BinaryInstruction;
import model.program.Program;

/**
 * Parent class for BinaryOperations.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class BinaryNode extends NumberNode
{
    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor for objects of class BinaryNode.
     * 
     * @param leftChild value representing the element on the left of the division sign
     * @param rightChild value representing the element on the right of the division sign
     * 
     */
    public BinaryNode(final Node leftChild, final Node rightChild)
    {
        super();
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    @Override
    public Type getType() {
        // to be implemented in subclasses
        return Type.NUMBER;
    }
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    @Override
    public boolean isConstant() {
        // to be implemented in subclasses
        return leftChild.isConstant() && rightChild.isConstant();
    }
    
    /**
     * Method to Compile the Binary Operation.
     * 
     * @param p The program
     * @param bi the Binary Instruction to append.
     */
    public void compile(final Program p,final BinaryInstruction bi) {
        leftChild.compile(p);
        rightChild.compile(p);
        p.append(bi);
    }
    
    /**
     * Method to Stringify the Binary Operation.
     * 
     * @param symbol the symbol of the Instruction to stringify
     * @return String representing the Binary Operation
     */
    public String toString(final String symbol) {
        return "(" + leftChild.toString() + symbol + rightChild.toString() + ")";
    }
}
