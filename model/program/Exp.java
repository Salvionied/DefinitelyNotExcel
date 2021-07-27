package model.program;


/**
 * Power operation between two numbers, the first to the power of the second.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Exp extends BinaryInstruction {
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double right = stack.pop().doubleValue();
        final double left = stack.pop().doubleValue();
        stack.push(Math.pow(left,right));
    }
    
    @Override
    public String toString() {
        return "EXP";
    }

}
