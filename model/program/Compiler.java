package model.program;

import model.nodes.Node;

/**
 * A Compiler converts an AST into a compiled Program.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class Compiler {
    /**
     * Compile the given ASTTree.
     * @param node The root of an AST
     * @return the compiled program
     */
    public Program compile(final Node node)  {
        final Program program = new Program();
        node.compile(program);
        return program;
    }
}
