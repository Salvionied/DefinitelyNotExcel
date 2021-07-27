package model.nodes;


import model.program.Neg;
import model.program.Program;


/**
 * A negation (e.g., -5, or -x).
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class Negation extends NumberNode {
    
    private final Node child;
    
    
    /**
     * Create a new IntNegation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super();
        this.child = child;
    }

    @Override
    public Type getType() {
        return Type.NUMBER;
    }
    
    @Override
    public boolean isConstant() {
        return child.isConstant();
    }

    @Override
    public void compile(final Program p) {
        child.compile(p);
        p.append(new Neg());
    }
    
    @Override
    public String toString() {
        
        return "(" + "-" + child.toString() + ")";
    }
    
}