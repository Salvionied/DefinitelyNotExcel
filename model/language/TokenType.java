package model.language;

/**
 * A program in a programming language is made up 
 * of different kinds of tokens.
 * This enumeration represents these different kinds.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public enum TokenType {
    CELLIDENTIFIER("Coordinates of a Cell"),
    RANGE("Range of cells"),
    LITERAL("literal"),
    IDENTIFIER("Strings and Functions"),
    DOUBLE("double"),
    PLUS("addition"),
    MINUS("negation or subtraction"), 
    STAR("multiplication"), 
    SLASH("division"),
    EQUAL("result"),
    PERCENT("modulo"), 
    EXP("exp"),
    FLOOR("floor division"),
    OPEN_PAREN("open parenthesis"), 
    CLOSED_PAREN("closed parenthesis"),

    END_OF_FILE("end of file"); 

    
    private final String name;
    
    
    /**
     * Initialize a TokenType.
     * @param name The human-readable name of this TokenType.
     */
    private TokenType(final String name) {
        this.name = name;
    }

    /**
     * Get the human-readable name.
     * @return the name of this TokenType.
     */
    public String getName() {
        return name;
    }

}