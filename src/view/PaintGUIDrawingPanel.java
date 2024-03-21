/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import model.LineTool;
import model.Tool;
/**
 * Drawing JPanel of the Paint GUI.
 * 
 * @author James Lau
 * @version 2.0
 */
public class PaintGUIDrawingPanel extends JPanel {
    /**
     * A generated SerializationID.
     */
    private static final long serialVersionUID = 7775565409916319467L;   
    /**
     * A white background color for the drawing panel.
     */
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    /**
     * A custom color that resembles UW's purple.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    /**
     * A custom color of UW's gold.
     */
    private static final Color UW_GOLD = new Color(232, 211, 162);
    /**
     * The starting thickness size for tools to use.
     */
    private static final int DEFAULT_THICKNESS = 3;
    
    /**
     * Support for firing property change events from this class.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
    
    // Other frame components
    /**
     * The current tool that is selected.
     */
    private Tool myTool;
    /**
     * The current color that is selected.
     */
    private Color myColor;
    /**
     * The current fill color that is selected.
     */
    private Color myFillColor;
    /**
     * If the shape is filled or not.
     */
    private boolean myShapeFilled;
    /**
     * The current thickness that is selected.
     */
    private BasicStroke myCurrentThickness;
    /**
     * Field that indicates if the canvas was just cleared.
     */
    private boolean myCanvasJustCleared;
    /**
     * List of all previous shapes and all the features that the shapes had.
     */
    private List<ShapeComponents> myPreviousShapes;   
    /**
     * Returns whether if the canvas is drawn of not. 
     */
    private boolean myArrayDrawn;
    
    public PaintGUIDrawingPanel() {
        super();
        setBackground(BACKGROUND_COLOR);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        initializePanelComponents();
        addMouseListener(new MyMouseInputAdapter());
        addMouseMotionListener(new MyMouseInputAdapter());
        myArrayDrawn = false;
        myShapeFilled = false;
        myCanvasJustCleared = false;
    }
    /**
     * Paints the current path.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        // Draw previous shapes
        int counter = 0;
        for (final ShapeComponents s : myPreviousShapes) {
            if (myPreviousShapes.get(counter).isFilled()) {
                g2d.setColor(myPreviousShapes.get(counter).getFillColor());
                g2d.fill(s.getShape());
            }
            g2d.setColor(myPreviousShapes.get(counter).getColor());
            g2d.setStroke(myPreviousShapes.get(counter).getThickness());
            g2d.draw(s.getShape());
            counter += 1;
        }
        counter = 0;
        if (!myCanvasJustCleared) {
            if (myShapeFilled) {
                g2d.setColor(myFillColor);
                g2d.fill(myTool.getShape());
            }
            g2d.setStroke(myCurrentThickness);
            g2d.setPaint(myColor);
            g2d.draw(myTool.getShape());
        }
        myCanvasJustCleared = false;
    }
    
    /**
     * Helper method used to initialize Drawing Panel components.
     */
    private void initializePanelComponents() {
        myTool = new LineTool();
        myColor = UW_PURPLE;
        myFillColor = UW_GOLD;
        myCurrentThickness = new BasicStroke(DEFAULT_THICKNESS);
        myPreviousShapes = new ArrayList<>();
    }
    
    /**
     * Getter that returns the current tool.
     * @return the current tool.
     */
    public Tool getTool() {
        return myTool;
    }
    
    /**
     * Method that sets the tool to another selected one.
     * @param theTool the tool that is set to.
     */
    public void setTool(final Tool theTool) {
        myPCS.firePropertyChange("Tool Change", myTool.canFill(), theTool.canFill());
        myPCS.firePropertyChange("Tool Icon Change", myTool, theTool);
        myTool = theTool;
    }
    
    /**
     * Method that sets the color to another selected one.
     * @param theColor the color that is set to.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Method that sets the shape to have a fill or not of possible.
     * @param theBoolean true or false whether if the shape is filled or not.
     */
    public void setFilled(final boolean theBoolean) {
        myShapeFilled = theBoolean;
    }
    
    /**
     * Method that sets the fill color to another selected one.
     * @param theColor the color that is set to.
     */
    public void setFillColor(final Color theColor) {
        myFillColor = theColor;       
    }
    
    
    /**
     * Method that sets the thickness of the tool.
     * @param theThickness the new thickness size of the tool.
     */
    public void setThickness(final int theThickness) {
        myCurrentThickness = new BasicStroke(theThickness);
    }
    
    /**
     * Method that clears the entire canvas.
     */
    public void clearCanvas() {
        myPreviousShapes.clear();
        myArrayDrawn = false;
        myCanvasJustCleared = true;
        repaint();
    }
    
    /**
     * Getter that returns all the currently drawn shapes and their features.
     * @return an array of all drawn shapes.
     */
    public List<ShapeComponents> getShapeArray() {
        return new ArrayList<ShapeComponents>(myPreviousShapes);
    }
    
    /**
     * Adds a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    
    /**
     * Removes a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
    
    /**
     * Mouse adapter class that listens for mouse press, mouse dragged, and mouse released.
     * @author James Lau
     * @version 1.0
     *
     */
    private class MyMouseInputAdapter extends MouseInputAdapter {
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (!myCurrentThickness.equals(new BasicStroke(0))) {
                myTool.setStartingPoint(theEvent.getPoint());
                repaint();
            }
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (!myCurrentThickness.equals(new BasicStroke(0))) {
                myTool.setEndPoint(theEvent.getPoint());
                repaint();
            }
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (!myCurrentThickness.equals(new BasicStroke(0))) {
                final boolean drawn = myArrayDrawn;
                myPreviousShapes.add(new ShapeComponents(myTool.getShape()
                        , myColor, myFillColor, myShapeFilled, myCurrentThickness));
                myArrayDrawn = true;
                if (drawn != myArrayDrawn) {
                    myPCS.firePropertyChange("Array Size", drawn, myArrayDrawn);
                }
            }
        }
    }

}
