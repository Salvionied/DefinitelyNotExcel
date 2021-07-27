package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Input Bar Class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InputBar {
    private JTextField bar;
    private TableModel dm;
    private int lastrow;
    private int lastcol;
    
    /**
     * Constructor for objects of class InputBar.
     * 
     * @param dm TableModel Object
     */
    public InputBar(final TableModel dm) {
        this.dm = dm;
        //create the TextField
        bar = new JTextField(50);
        bar.setText("Select a cell");
        eventHandler();
    }

    /**
     * Method for retrieving the JTexfield Object of the InputBar.
     * 
     * @return JTextField of the inputBar
     */
    public JTextField getbar() {
        return bar;
    }
    
    /**
     * Method for updating the Text contained within the InputBar.
     * 
     * @param lastrow lastly selected row
     * @param lastcol lastly selected column
     * @param content content of the Selected cell in string format
     */
    public void updateText(final int lastrow ,final int lastcol, final String content) {
        this.lastrow = lastrow;
        this.lastcol = lastcol;
        bar.setText(content);
    }
    
    /**
     * method to Set the InputBar content to display A message.
     * @param message the message to be shown.
     */
    public void updateText(final String message) {
        bar.setText(message);
    }
    
    /**
     * Private method for updating the cell content if the Inputbar
     * Has been used to modify the content of a cell.
     */
    private void eventHandler() {
        bar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dm.getSM().setParse(lastrow,lastcol,bar.getText());
                    updateText(lastrow,lastcol,dm.get(lastrow,lastcol).getValue());
                }
            }
        });
    }
}
