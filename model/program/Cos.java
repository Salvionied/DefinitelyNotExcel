package model.program;

/**
 * cos takes the top value from the OperandStack
 * and pushes the result of cos(number).
 * 
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Cos extends Instruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(Math.cos(stack.pop().doubleValue()));
    }
        
    @Override
    public String toString() {
        return "COS";
    }

}
