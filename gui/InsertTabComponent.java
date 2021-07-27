package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * TabComponent for adding new Tabs.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InsertTabComponent extends JPanel {
    private final TabManager pane;
    
    /**
     * Default constructor for the TabAdder component.
     * @param pane the TabManager.
     */
    public InsertTabComponent(final TabManager pane) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.pane = pane;
        setOpaque(false);
        final JButton button = new JButton("+");
        button.setUI(new BasicButtonUI());
        //Make it transparent
        button.setContentAreaFilled(false);
        //No need to be focusable
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEtchedBorder());
        //Remove the border
        button.setBorderPainted(false);
        eventHandler(button);
        add(button);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
    
    private void eventHandler(final JButton button) {
        button.addMouseListener(new MouseAdapt());
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                pane.addSpTab();
            }
        });
    }
}
