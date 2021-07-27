package model.program;

/**
 * Subtraction Operation between two numbers.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class Sub extends BinaryInstruction {
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(- stack.pop().doubleValue() + stack.pop().doubleValue());
    }
        
    @Override
    public String toString() {
        return "SUB";
    }

}