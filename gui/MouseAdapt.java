package gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;


/**
 * MouseAdapter for Buttons on Tab Components.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class MouseAdapt extends MouseAdapter
{
    /**
     * When mouse enters the field of the button set the border to be visible.
     * @param e the MouseEvent
     */
    public void mouseEntered(final MouseEvent e) {
        final Component component = e.getComponent();
        if (component instanceof AbstractButton) {
            final AbstractButton button = (AbstractButton) component;
            button.setBorderPainted(true);
        }
    }

    /**
     * When mouse leaves the field of the button set the border to invisible.
     * @param e the MouseEvent
     */
    public void mouseExited(final MouseEvent e) {
        final Component component = e.getComponent();
        if (component instanceof AbstractButton) {
            final AbstractButton button = (AbstractButton) component;
            button.setBorderPainted(false);
        }
    }
}
