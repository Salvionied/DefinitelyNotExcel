package gui;

import model.SheetModel;
import model.SheetModelListener;
import model.nodes.Node;
import model.nodes.StringNode;

import javax.swing.table.AbstractTableModel;

/**
 * Table Model class to interface Backend to FrontEnd
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class TableModel extends AbstractTableModel
{
    public String path;
    private SheetModel sm;
    
    /**
     * Constructor for TableModel Object.
     */
    public TableModel() {
        super();
        path = "";
        this.sm = new SheetModel(100,50);
        eventHandler();
    }
    
    /**
     * Method to Notify Changes in the BackEnd.
     */
    private void eventHandler() {
        sm.addSheetModelListener(new SheetModelListener() {
            public void sheetModelChanged(final SheetModel sm) {
                fireTableDataChanged();
            }
        });
    }
    
    /**
     * reset the Model.
     */
    public void reset() {
        sm.resetSheetModel(100,50);
        
    }

    /**
     * Get The SheetModel.
     * 
     * @return SheetModel Object
     */
    public SheetModel getSM() {
        return sm;
    }
    
    /**
     * Get an element within the BackEnd SheetModel.
     * 
     * @param row the row of the cell
     * @param column the column of the cell
     * @return Node object contained at row:column
     */
    public Node get(final int row, final int column) {
        return sm.get(row,column);
    }
    
    /**
     * Method to allow for copying the contents of cells.
     * @param row the rowIndex
     * @param col the columnIndex
     * @return String The content of the Cell in String Format.
     */
    public String getCopyOfContent(final int row, final int col) {
        final Node n = get(row,col);
        if (n != null) {
            if (n instanceof StringNode) {
                return n.toString();
            } else {
                return "=" + n.toString();
            }
        } else { return ""; }
    }
    
    @Override
    public int getRowCount() {
        return sm.getHeight();
    }
    
    @Override
    public int getColumnCount() {
        int count = 0;
        for ( int i = 0; i < sm.getHeight(); i++) {
            if (sm.getWidth(i) > count) {
                count = sm.getWidth(i);
            }
        }
        return count;
    }
    
    @Override
    public String getValueAt(final int rowIndex,final int columnIndex) {
        return sm.getCellResult(rowIndex,columnIndex).toString();
    }
    
    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return true;
    }
    
    @Override
    public void setValueAt(final Object aValue,final int rowIndex,final int columnIndex) {
        sm.setParse(rowIndex, columnIndex,aValue.toString());
        sm.add();
    }
}
