/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;

/**
 * Presents the GUI for the PowerPaint application.
 * 
 * @author Alan Fowler (acfowler@uw.edu)
 * @author James Lau
 * @version Autumn 2022
 */
public final class PaintGUI {
    
    // constants (if any)
    /**
     * The number three, to prevent warnings.
     */
    private static final int THREE = 3;
    
    // instance fields
    /**
     * Frame used to store all components of the program.
     */
    private final JFrame myFrame;
    /**
     * Panel where shapes are drawn on.
     */
    private final PaintGUIDrawingPanel myDrawingPanel;
    /**
     * Menu bar used in the paint program window.
     */
    private final PaintGUIJMenuBar myMenuBar;
    /**
     * Toolbar used in the paint program window.
     */
    private final PaintGUIJToolBar myToolBar;
    
    public PaintGUI() {
        super();
        // initialize instance fields here
        myFrame = new JFrame("TCSS 305 Paint - Autumn 2022");
        myDrawingPanel = new PaintGUIDrawingPanel();
        myMenuBar = new PaintGUIJMenuBar(myDrawingPanel);
        myToolBar = new PaintGUIJToolBar();
        myDrawingPanel.addPropertyChangeListener(myMenuBar);
        myMenuBar.setUpOptionMenu();
        setUpTools();
        myMenuBar.setUpHelpMenu();
        // setup and display the GUI
        start();
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    private void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /** A ToolKit. */
        final Toolkit kit = Toolkit.getDefaultToolkit();
        
        // Set Icon of JFrame
        final ImageIcon img = new ImageIcon("files/paintbrush.gif");
        myFrame.setIconImage(img.getImage());
        myFrame.add(myToolBar, BorderLayout.SOUTH);
        myFrame.setJMenuBar(myMenuBar);
        myFrame.add(myDrawingPanel);
        myFrame.setSize((int) kit.getScreenSize().getWidth() / THREE
                , (int) kit.getScreenSize().getHeight() / THREE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true); 
    }
    
    /**
     * Helper method used to set up all the tool buttons in the paint program.
     */
    private void setUpTools() {
        final Action[] actions = {new LineAction(myDrawingPanel)
                , new RectangleAction(myDrawingPanel), new EllipseAction(myDrawingPanel)};
        final JMenu toolMenu = new JMenu("Tools");
        JMenuItem menuItem;
        JToggleButton toggleButton;
        for (final Action a : actions) {
            menuItem = myMenuBar.createToolButton(a);
            toggleButton = myToolBar.createToggleButton(a);
            toolMenu.add(menuItem);
            myToolBar.add(toggleButton);
        }
        myMenuBar.add(toolMenu);
    }
}
