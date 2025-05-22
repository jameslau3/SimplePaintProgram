/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import model.PencilTool;

/**
 * 
 * 
 * @author James Lau
 * @version 1.0
 */
public class PencilAction extends AbstractAction {
    
    /**
     * A generated serialization ID.
     */
    private static final long serialVersionUID = -6622265889726714782L;
    /**
     * A string value of "Pencil".
     */
    private static final String PENCIL = "Pencil";
    /**
     * Panel that is being painted.
     */
    private final PaintGUIDrawingPanel myPanel;
    /**
     * Constructs an action depending on shape.
     */
    public PencilAction(final PaintGUIDrawingPanel thePanel) {
        super(PENCIL);
        myPanel = thePanel;
        putValue(Action.SELECTED_KEY, true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setTool(new PencilTool());
    }

    @Override
    public String toString() {
        return PENCIL;
    }
}