package model.program;

/**
 * The OperandStack is a stack holding
 * the temporary values during execution.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class OperandStack {
    
    private Number[] stack;
    private int sp;
    
    /**
     * Create an empty OperandStack, with space for at most 10 elements.
     */
    public OperandStack() {
        stack = new Number[0];
        sp = -1;
    }
    
    /**
     * Push the given integer value on the stack.
     * @param value The value to push
     */
    public void push(final Number value) {
        sp++;
        Number[] temp = new Number[sp + 1];
        for (int i = 0; i < sp; i ++) {
            temp[i] = stack[i];
        }
        temp[sp] = value;
        stack = temp;
    }
    
    /**
     * Pop the top-most value off the stack.
     * @return the top-most value
     */
    public Number pop() {
        return stack[sp--];
    }
    
    /**
     * Method to retrieve the length of the Stack.
     * @return in the length of the stack
     */
    public int getLength() {
        return stack.length;
    }
    
}