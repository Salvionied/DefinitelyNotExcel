package model.program;

/**
 * Floor rounded division between two numbers.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class FDiv extends BinaryInstruction
{
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        final double rightChild = stack.pop().doubleValue();
        final int result = (int)(stack.pop().doubleValue() / rightChild);
        stack.push(result);
    }
    
    @Override
    public String toString() {
        return "FDIV";
    }
}
