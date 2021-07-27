package model.language;


/**
 * TokenFactory for Double Values for example. 99.23
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class DoubleTokenFactory extends RegExTokenFactory
{
    /**
     * Create a new Literal for Numbers in Double Format.
     */
    public DoubleTokenFactory() {
        // regular expression for an integer literal
        super("[0-9]+\\.[0-9]+");
    }

    @Override
    public Token getToken() {
        // return a token of the appropriate TokenType 
        // with its text and starting position
        return new Token(TokenType.DOUBLE, getTokenText(), getTokenStartPosition());
    }
}
