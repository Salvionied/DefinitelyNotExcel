package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the Saved States using the Memento patters.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Caretaker
{
    private List<SheetModel.Memento> savedStates;
    private int index = -1;
    private final SheetModel sm;
    
    /**
     * Create the helper to act on the state of the Object we want saved.
     * @param sm the object on which the caretaker keeps the State.
     */
    public Caretaker(final SheetModel sm) {
        savedStates = new ArrayList<SheetModel.Memento>();
        this.sm = sm;
    }
    
    /**
     * Add the newest State to the State list.
     */
    public void add() {
        if (savedStates.size() == 20) {
            savedStates.remove(0);
            savedStates.add(index,sm.saveToMemento());
        } else {
            index ++;
            savedStates.add(index,sm.saveToMemento());
        }
    }
    
    /**
     * Retrieve the previous state.
     */
    public void undo() {
        if (index - 1 > - 1) {
            index -= 1;
            sm.restoreFromMemento(savedStates.get(index));
        }
    }
    
    /**
     * Retrieve the future previous state.
     */
    public void redo() {
        if (index + 1 < savedStates.size()) {
            index += 1;
            sm.restoreFromMemento(savedStates.get(index));
        }
    }
    
    
    
}
