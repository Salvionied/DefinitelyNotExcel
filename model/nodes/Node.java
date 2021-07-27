package model.nodes;


import model.program.Program;

/**
 * An abstract syntax tree (AST) node.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Node {
    
    /**
     * Get the type of values produced by this node.
     * @return the type of this node
     */
    public Type getType() {
        // to be implemented in subclasses
        return Type.INVALID;
    }
    
    /**
     * Get whether this node always evaluates to the exact same value.
     * @return whether this node produces a constant value
     */
    public boolean isConstant() {
        // to be implemented in subclasses
        return true;
    }

    /**
     * Decompile this node into a string.
     * Note that the resulting string may have
     * extra parentheses.
     * @return a String representation of this AST
     */
    public String toString() {
        // to be implemented in subclasses
        return "";
    }
    
    /**
     * Return the String contained within the node. ErrorNode only.
     * @return String the error message
     */
    public String getValue() {
        //to implement in ErrorNode
        return "";
    }
    
    /**
     * Compile this node into the given Program.
     * @param p The program to append this node to
     */
    public void compile(final Program p) {
        // to be implemented in subclasses
    }

    
}
