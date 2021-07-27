package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


/**
 * Class managing the gui
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class GuiBase {
    
    /**
     * Constructor for objects of class GuiBase.
     */
    public GuiBase() {
        //Create the main window
        final JFrame window = new JFrame("Definitely Not Excel");
        //Create the panel of the window
        final TabManager tabs = new TabManager();
        final JMenuBar menub = new MenuBar(tabs);
        window.setJMenuBar(menub);
        //set the size of the window
        window.setSize(1920,1080);
        //set the closing command
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add the masterPanel to the window
        window.add(tabs);
        //window.add(masterPanel);
        //set the window to be visible
        window.setVisible(true);
        //pack the graphics
        window.pack();
    }
}
