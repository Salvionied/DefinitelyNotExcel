package model;

/**
 * Event Listener for SheetModel
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public interface SheetModelListener
{
    
    /**
     * React to modification of the SheetModel
     * @param sm the model that changed.
     */
    public abstract void sheetModelChanged(SheetModel sm);
    
}
