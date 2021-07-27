package model.program;

/**
 * Modulo instruction, returns the reimainder of the division operation between two numbers.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Mod extends BinaryInstruction
{
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double right = stack.pop().doubleValue();
        final double left = stack.pop().doubleValue();
        stack.push(left % right);
    }
    
    @Override
    public String toString() {
        return "MOD";
    }
}

