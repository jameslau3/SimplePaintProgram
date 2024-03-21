/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package view;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * @author James
 * @version 2.0
 */
public class PaintGUIJToolBar extends JToolBar {
    
    /**
     * A generated serialization ID.
     */
    private static final long serialVersionUID = -4897866395156216771L;
    
    /**
     * A string value of "Line".
     */
    private static final String LINE = "Line";
    
    /**
     * A string value of "icons/".
     */
    private static final String FILE_HEADER = "icons/";
    
    /**
     * A string value of ".gif".
     */
    private static final String GIF_EXTENSION = ".gif";
    
    /**
     * A string value of "_bw.gif".
     */
    private static final String BW_EXTENSION = "_bw.gif";
    
    /**
     * Button group of the toggle buttons.
     */
    private final ButtonGroup myButtonGroup;
    
    /**
     * Constructs a new tool bar with a button group.
     */
    public PaintGUIJToolBar() {
        super();
        myButtonGroup = new ButtonGroup();
    }
    
    /**
     * Creates a toggle button depending on the action parameter.
     * @param theAction the action listener associated with the button.
     * @return the button that is created.
     */
    public JToggleButton createToggleButton(final Action theAction) {
        final JToggleButton button;
        if (LINE.equals(theAction.toString())) {
            button = new JToggleButton(theAction);
            button.setIcon(new ImageIcon(FILE_HEADER + theAction.toString() + BW_EXTENSION));
            button.setSelectedIcon(new ImageIcon(
                    FILE_HEADER + theAction.toString() + GIF_EXTENSION));
            button.setSelected(true);
        } else  {
            button = new JToggleButton(theAction);
            button.setIcon(new ImageIcon(FILE_HEADER + theAction.toString() + BW_EXTENSION));
            button.setSelectedIcon(new ImageIcon(
                    FILE_HEADER + theAction.toString() + GIF_EXTENSION));
        }
        myButtonGroup.add(button);
        return button;
    }
    
}
