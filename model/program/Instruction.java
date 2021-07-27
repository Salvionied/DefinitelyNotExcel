package model.program;

/**
 * An IJVM-like instruction.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Instruction {
    /**
     * Execute this instruction.
     * @param storage The "memory" to use during the execution
     */
    public void execute(final Storage storage) {
        // to be implemented in subclasses
    }
    
    /**
     * Get a String with the disassembled instruction.
     * @return A String-representation of this instruction.
     */
    public String toString() {
        return "?";
    }
}

