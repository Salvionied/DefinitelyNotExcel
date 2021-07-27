package model.program;

/**
 * tan takes the top value from the OperandStack
 * and pushes the result of tan(number).
 * 
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Tan extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.tan(stack.pop().doubleValue()));
    }
        
    @Override
    public String toString() {
        return "TAN";
    }

}
