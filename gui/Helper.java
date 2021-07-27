package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * An Helper window displaying keyBoard Shortcuts and possible operators and functions.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Helper 
{
    /**
     * Create a secondary Frame Holding the Textile Helper.
     */
    public Helper() {
        final JFrame window = new JFrame("Helper");
        //set the size of the window
        window.setSize(500,200);
        //set the closing command
        
        final JScrollPane helper = getHelper();
        window.getContentPane().add(helper);
        //add the masterPanel to the window
        //set the window to be visible
        window.setVisible(true);
        //pack the graphics
        window.pack();

    }
    
    /**
     * Generate the JScrollPane with the helper text within.
     * @return JScrollpane object
     */
    private JScrollPane getHelper() {
        final JTextArea pane = new JTextArea();
        pane.setEditable(false);
        pane.setText(generateFunctionHelperText());
        final JScrollPane sp = new JScrollPane(pane);
        sp.setPreferredSize(new Dimension(500,200));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        return sp;
    }
    
    /**
     * Generate the tooltip text.
     */
    private String generateHelperText() {
        String text = "";
        text += "Definitely Not Excel Help Tools. \n\n"
            + "Shortcuts Available: \n" + "CTRL + Z -> undo.\n"
            + "CTRL + R -> redo.\n" + "CTRL + N -> Reset current SpreadSheet.\n"
            + "CTRL + C -> Copy the currently selected cells. \n"
            + "CTRL + V -> Paste Cells.\n"
            + "CTRL + X -> Cut the currently selected cells,"
            + "the in this way cut cells will be stored in the clipboard.\n"
            + "DEL -> delete all the currently selected cells.\n\n"
            + "CELL EDITING FUNCTIONS\n" 
            + "Prepend an \"=\" to execute calculations.\n"
            + "For referring to a cell use XY where X is "
            + "the Column of the cell and Y its row.\n Example A0"
            + "For referring to a range of cells use WX:YZ where"
            + " W and X are the column and row of the top left Cell \n.Y, "
            + "Z are the column and row of the bottom right cell. Example A0:B0\n\n";
        return text;
    }
    
    /**
     * Helper.
     */
    private String generateFunctionHelperText() {
        String res = generateHelperText();
        res += "Functions Available for Use with Ranges:\n"
            + "SUM(WX:YZ) returns the sum of the range given.\n"
            + "AVG(WX:YZ) returns the Average of the range given.\n"
            + "MIN(WX:YZ) returns the Minimum number amongst the given cells.\n"
            + "MAX(WX:YZ) returns the Maximum number amongst the given cells.\n\n"
            + "Functions Available for Use with Single Cells and numbers:\n"
            + "SQRT(X) -> the square root of the Number "
            + "or Single Cell within the parenthesis.\n"
            + "SIN(X) -> the sine of the Number or Single Cell within the parenthesis.\n"
            + "COS(X) -> the cosine of the Number or Single Cell within the parenthesis.\n"
            + "TAN(X) -> the Tangent of the Number or Single Cell within the parenthesis.\n\n"
            + "Binary Operators for arithmetic operations which "
            + "work between two numbers or Single CellIdentifiers:\n"
            + "+ -> Sum\n"
            + "- -> Subtraction\n"
            + "/ -> Division\n"
            + "_/ -> FloorDivision\n"
            + "^ -> Power\n"
            + "* -> Multiplication\n"
            + "% -> Modulo\n";
        return res;
    }
}
