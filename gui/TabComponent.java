package gui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;



/**
 * Deletable Tab Component.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class TabComponent extends JPanel {
    private final TabManager pane;
    
    /**
     * Constructor for a deletable TabComponent.
     * @param pane the TabManager object.
     */
    public TabComponent(final TabManager pane) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.pane = pane;
        setOpaque(false);
        
        //make JLabel read titles from TabManager
        final JLabel label = new JLabel() {
            public String getText() {
                final int i = pane.indexOfTabComponent(TabComponent.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        
        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        final JButton button = new TabButton();
        add(button);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
    
    /**
     * The tabButton Class for deleting tabs.
     * @author Edoardo Salvioni
     * @author Anton Tanev
     */
    private class TabButton extends JButton implements ActionListener {
        /**
         * TabButton constructor.
         */
        public TabButton() {
            super();
            final int size = 17;
            setText("x");
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //we use the same listener for all buttons
            addMouseListener(new MouseAdapt());
            addActionListener(this);
        }
    
        public void actionPerformed(final ActionEvent e) {
            final int i = pane.indexOfTabComponent(TabComponent.this);
            if (i != -1) {
                pane.remove(i);
            }
        }
    
    }
}
