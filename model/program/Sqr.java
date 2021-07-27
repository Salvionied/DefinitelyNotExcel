package model.program;

/**
 * SQR takes the top value from the OperandStack
 * and pushes the root of the result back to the OperandStack.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Sqr extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.sqrt(stack.pop().doubleValue()));
    }
        
    @Override
    public String toString() {
        return "SQR";
    }

}
