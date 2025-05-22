/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author James
 * @version 2.0
 */
public class PaintGUIJMenuBar extends JMenuBar implements PropertyChangeListener {
    
    /**
     * A generated serializationID.
     */
    private static final long serialVersionUID = 1513290478060257224L;
    /**
     * A string value of "Line".
     */
    private static final String LINE = "Line";
    /**
     * A string value of "Rectangle".
     */
    private static final String RECTANGLE = "Rectangle";
    /**
     * A string value of "Ellipse".
     */
    private static final String ELLIPSE = "Ellipse";
    /**
     * Black and white line image icon.
     */
    private static final ImageIcon LINE_ICON_BW = new ImageIcon("icons/line_bw.gif");
    /**
     * Black and white rectangle image icon.
     */
    private static final ImageIcon RECTANGLE_ICON_BW = new ImageIcon("icons/rectangle_bw.gif");
    /**
     * Black and white ellipse image icon.
     */
    /**
     * Black and white ellipse image icon.
     */
    private static final ImageIcon ELLIPSE_ICON_BW = new ImageIcon("icons/ellipse_bw.gif");
    /**
     * Black and white pencil image icon.
     */
    private static final ImageIcon PENCIL_ICON_BW = new ImageIcon("icons/pencil_bw.gif");
    /**
     * Colored line image icon.
     */
    private static final ImageIcon LINE_ICON = new ImageIcon("icons/line.gif");
    /**
     * Colored rectangle image icon.
     */
    private static final ImageIcon RECTANGLE_ICON = new ImageIcon("icons/rectangle.gif");
    /**
     * Colored ellipse image icon.
     */
    private static final ImageIcon ELLIPSE_ICON = new ImageIcon("icons/ellipse.gif");
    /**
     * Colored pencil image icon.
     */
    private static final ImageIcon PENCIL_ICON = new ImageIcon("icons/pencil.gif");
    /**
     * A custom color of UW's purple.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /**
     * A custom color of UW's gold.
     */
    private static final Color UW_GOLD = new Color(232, 211, 162);
    
    /**
     * The max thickness for the thickness slider.
     */
    private static final int MAX_THICKNESS = 15;
    
    /**
     * The initial starting thickness on the thickness slider.
     */
    private static final int INITIAL_THICKNESS = 3;
    
    /**
     * Major tick spacing for thickness slider.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     * Minor tick spacing for thickness slider.
     */
    private static final int MINOR_TICK_SPACING = 1;
    
    /**
     * Current color selected.
     */
    private Color myColor;
    
    /**
     * Current fill color selected.
     */
    private Color myFillColor;
    
    /**
     * JSlider that is used in menu bar.
     */
    private JSlider mySlider;
    /**
     * A button that is used to clear the canvas.
     */
    private JMenuItem myClearButton;
    /**
     * A checkbox that is used to decide whether a shape is filled or not if fillable.
     */
    private JCheckBoxMenuItem myFillCheckBox;
    /**
     * Button group for menu buttons.
     */
    private final ButtonGroup myMenuButtonGroup;
    /**
     * A button that sets the tool to line.
     */
    private JRadioButtonMenuItem myLineButton;
    /**
     * A button that uses the tool to rectangle.
     */
    private JRadioButtonMenuItem myRectangleButton;
    /**
     * A button that uses the tool to ellipse.
     */
    private JRadioButtonMenuItem myEllipseButton;
    /**
     * A button that uses the tool to pencil.
     */
    private JRadioButtonMenuItem myPencilButton;
    /**
     * The panel that the menu bar is being added with.
     */
    private final PaintGUIDrawingPanel myPanel;
    
    /**
     * Constructor used to intialize JMenuBar components.
     * @param thePanel the panel that is using the menu bar.
     */
    public PaintGUIJMenuBar(final PaintGUIDrawingPanel thePanel) {
        super();
        myColor = UW_PURPLE;
        myFillColor = UW_GOLD;
        myPanel = thePanel;
        myMenuButtonGroup = new ButtonGroup();
    }
    
    /**
     * Private method used to create the thickness slider used in the paint program.
     * @return JMenuItem that has thickness slider in it.
     */
    private JMenuItem createmySlider() {
        final JMenuItem item = new JMenu("Thickness");
        mySlider = new JSlider();
        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0, 
                MAX_THICKNESS, INITIAL_THICKNESS);
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        mySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = mySlider.getValue();
                myPanel.setThickness(value);
            }
        });
        item.add(mySlider);
        return item;
    }
    
    /**
     * Private method that creates the clear button used in the paint program.
     * @return a clear button.
     */
    private JMenuItem createClearButton() {
        myClearButton = new JMenuItem("Clear");
        myClearButton.setEnabled(false);
        myClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.getShapeArray().clear();
                myPanel.clearCanvas();
                myPanel.repaint();
                myClearButton.setEnabled(false);
            }
        });
        myMenuButtonGroup.add(myClearButton);
        return myClearButton;
    }
    
    /**
     * Private method that creates the color button used in the paint program.
     * @return a color button that opens a new window for a color changer.
     */
    private JMenuItem createColorButton() {
        final JMenuItem colorButton = new JMenuItem("Color...");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color choice = JColorChooser.showDialog
                        (null, "Color Chooser", myColor);
                // If user chooses a color.
                if (choice != null) {
                    myPanel.setColor(choice);
                    myColor = choice;
                } // No choice                
            }
        });
        myMenuButtonGroup.add(colorButton);
        return colorButton;
    }
    
    /**
     * Private method that creates a button called fill, used to change color for a shape
     * that can be filled.
     * @return a fill color button that opens a new window for a fill color changer.
     */
    public JMenuItem createFillColorButton() {
        final JMenuItem button = new JMenuItem("Fill Color...");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color choice = JColorChooser.showDialog
                        (null, "Fill Color Chooser", myFillColor);
                // If user chooses a color.
                if (choice != null) {
                    myPanel.setFillColor(choice);
                    myFillColor = choice;
                } // No choice                
            }
        });
        myMenuButtonGroup.add(button);
        return button;
    }
    
    /**
     * Getter that returns the current color.
     * @return the color of the tool.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Getter that returns the current fill color.
     * @return the fill color for the tool.
     */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
     * Method that creates a fill checkbox that allows user to fill a shape if possible.
     * @return a checkbox that enables or disables filling.
     */
    public JCheckBoxMenuItem createFillCheckBox() {
        myFillCheckBox = new JCheckBoxMenuItem("Fill");
        myFillCheckBox.setEnabled(false);
        myFillCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myFillCheckBox.isSelected()) {
                    myPanel.setFilled(true);
                } else {
                    myPanel.setFilled(false);
                }
            }
        });
        return myFillCheckBox;
    }
    
    /**
     * Method used to create the tool buttons in the the JMenuBar.
     * @param theAction the action listener associated with the button.
     * @return a JRadioButtonMenuItem with a specific tool.
     */
    public JRadioButtonMenuItem createToolButton(final Action theAction) { 
        JRadioButtonMenuItem button;
        if (LINE.equals(theAction.toString())) {
            myLineButton = new JRadioButtonMenuItem(theAction);
            myLineButton.setIcon(LINE_ICON);
            myLineButton.setSelected(true);
            myMenuButtonGroup.add(myLineButton);
            button = myLineButton;
        } else if (RECTANGLE.equals(theAction.toString())) {
            myRectangleButton = new JRadioButtonMenuItem(theAction);
            myRectangleButton.setIcon(RECTANGLE_ICON_BW);
            myMenuButtonGroup.add(myRectangleButton);
            button = myRectangleButton;
        } else if (ELLIPSE.equals(theAction.toString())) {
            myEllipseButton = new JRadioButtonMenuItem(theAction);
            myEllipseButton.setIcon(ELLIPSE_ICON_BW);
            myMenuButtonGroup.add(myEllipseButton);
            button = myEllipseButton;
        } else {
            myPencilButton = new JRadioButtonMenuItem(theAction);
            myPencilButton.setIcon(PENCIL_ICON_BW);
            myMenuButtonGroup.add(myPencilButton);
            button = myPencilButton;
        }
        return button;
    }
    
    /**
     * Method used to create the about button in the JMenuBar.
     * @return the about button.
     */
    private JMenuItem createAboutButton() {
        final JMenuItem item = new JMenuItem("About...");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null
                        , "James Lau\nAutumn 2022\nProgram for Painting!"
                        , "TCSS 305 Paint", JOptionPane.INFORMATION_MESSAGE
                        , new ImageIcon("icons/paintbrush.gif"));
            }
        });
        return item;
    }
    
    /**
     * Helper method used to set up the option menu in the menubar.
     */
    public void setUpOptionMenu() {
        final JMenu optionMenu = new JMenu("Options");
        optionMenu.add(createmySlider());
        optionMenu.add(new JSeparator());
        optionMenu.add(createColorButton());
        optionMenu.add(createFillColorButton());
        optionMenu.add(new JSeparator());
        optionMenu.add(createFillCheckBox());
        optionMenu.add(new JSeparator());
        optionMenu.add(createClearButton());      
        add(optionMenu);
    }
    
    /**
     * Helper method used to set up the help menu.
     */
    public void setUpHelpMenu() {
        final JMenu aboutMenu = new JMenu("Help");
        aboutMenu.add(createAboutButton());
        add(aboutMenu);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        // Clear button property change check
        if (theEvent.getPropertyName().equals("Array Size")) {
            myClearButton.setEnabled(myPanel.getShapeArray().size() != 0);
        // Fill box property change check
        } else if (theEvent.getPropertyName().equals("Tool Change")) {
            myFillCheckBox.setEnabled(!(myPanel.getTool().canFill()));
        // Tool change properyu change check
        } else if (theEvent.getPropertyName().equals("Tool Icon Change")) {
            if (theEvent.getNewValue().toString().equals(myLineButton.getText())) {
                myLineButton.setIcon(LINE_ICON);
                myRectangleButton.setIcon(RECTANGLE_ICON_BW);
                myEllipseButton.setIcon(ELLIPSE_ICON_BW);
            } else if (theEvent.getNewValue().toString().equals(myRectangleButton.getText())) {
                myLineButton.setIcon(LINE_ICON_BW);
                myRectangleButton.setIcon(RECTANGLE_ICON);
                myEllipseButton.setIcon(ELLIPSE_ICON_BW);
            } else if (theEvent.getNewValue().toString().equals(myEllipseButton.getText())) {
                myLineButton.setIcon(LINE_ICON_BW);
                myRectangleButton.setIcon(RECTANGLE_ICON_BW);
                myEllipseButton.setIcon(ELLIPSE_ICON);
            } else if (theEvent.getNewValue().toString().equals(myPencilButton.getText())) {
                myLineButton.setIcon(LINE_ICON_BW);
                myRectangleButton.setIcon(RECTANGLE_ICON_BW);
                myEllipseButton.setIcon(ELLIPSE_ICON_BW);
            }
        }
    }
}
