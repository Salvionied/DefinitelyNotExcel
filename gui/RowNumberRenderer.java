package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Renderer for the Row HEader column.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class RowNumberRenderer extends DefaultTableCellRenderer {
    
    /**
     * Constructor for the renderer.
     */
    public RowNumberRenderer() {
        super();
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    /**
     * Method to retrieve the actual Graphical component.
     * @param table the JTable object
     * @param value the value
     * @param isSelected true if the column is selected false otherwise
     * @param hasFocus if the element is seen on the window
     * @param row the row number
     * @param column the columnIndex
     * @return the Graphical rendered Component
     */
    public Component getTableCellRendererComponent(
        final JTable table, 
        final Object value, 
        final boolean isSelected, 
        final boolean hasFocus, 
        final int row, 
        final int column) {
        if (table != null) {
            final JTableHeader header = table.getTableHeader();
            
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }
        
        if (isSelected) {
            setFont(getFont().deriveFont(Font.BOLD));
            setBackground(new Color(202,231,193));
        }
        
        setText((value == null) ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        
        
        return this;
    }
}
