package model.program;

/**
 * Multiplication Operation between two numbers.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Mul extends BinaryInstruction
{
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(stack.pop().doubleValue() * stack.pop().doubleValue());
    }
    
    @Override
    public String toString() {
        return "MUL";
    }
}
