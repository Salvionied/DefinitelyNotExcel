package model.language;

//---
/**
 * A factory for tokens of type CELLIDENTIFIER. Example A0 or B0 but even AA99.
 * 
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class CellIdentifierTokenFactory extends RegExTokenFactory {

    /**
     * Create an IdentifierTokenFactory.
     */
    public CellIdentifierTokenFactory() {
        super("[A-Z_]+[0-9]+");
    }

    /**
     * Produce a token.
     * @return the currently found token
     */
    public Token getToken() {
        return new Token(TokenType.CELLIDENTIFIER, getTokenText(), getTokenStartPosition());
    }
    
}
//---