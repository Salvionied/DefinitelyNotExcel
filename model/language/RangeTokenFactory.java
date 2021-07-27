package model.language;

//---
/**
 * A factory for tokens of type identifier. for example A0:B5
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @Version 0.0.1
 */
public class RangeTokenFactory extends RegExTokenFactory {

    /**
     * Create an IdentifierTokenFactory.
     */
    public RangeTokenFactory() {
        super("[A-Z_]+[0-9]+\\:[A-Z_]+[0-9]+");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.RANGE, getTokenText(), getTokenStartPosition());
    }
    
}
//---