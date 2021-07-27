package model.program;

/**
 * Negates the top element of the stack, making it negative and pushing it back.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public class Neg extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push( - stack.pop().doubleValue());
    }
        
    @Override
    public String toString() {
        return "NEG";
    }

}
