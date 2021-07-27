package model.program;

/**
 * sin takes the top value from the OperandStack
 * and pushes the result of sin().
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public class Sin extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.sin(stack.pop().doubleValue()));
    }
        
    @Override
    public String toString() {
        return "SIN";
    }

}
