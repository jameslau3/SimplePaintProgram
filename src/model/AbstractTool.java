/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Point;

/**
 * Abstract class that implement methods shared by all tools.
 * 
 * @author James Lau
 * @version 1.0
 */
public abstract class AbstractTool implements Tool {
    
    /**
     * Constant used to initialize drawing point outside of drawing panel to prevent
     * creating a dot in the corner of the drawing panel.
     */
    public static final Point INITIAL_POINT = new Point(-10, -10);
    
    /**
     * Initial point for shape drawn by the tool.
     */
    private Point myStartingPoint;
    
    /**
     * Ending point for shape drawn by the tool.
     */
    private Point myEndingPoint;
    
    /**
     * Constructs a paint tool.
     */
    protected AbstractTool() {
        myStartingPoint = INITIAL_POINT;
        myEndingPoint = INITIAL_POINT;
    }
    
    @Override
    public void setStartingPoint(final Point thePoint) {      
        myStartingPoint = (Point) thePoint.clone();
        myEndingPoint = (Point) thePoint.clone();
    }
    
    @Override
    public void setEndPoint(final Point thePoint) {      
        myEndingPoint = (Point) thePoint.clone();
    }


    /**
     * Getter that returns the starting point of the shape.
     * @return the starting point of the shape.
     */
    protected Point getStartingPoint() {
        return myStartingPoint;
    }
    
    /**
     * Getter that returns the ending point of the shape.
     * @return the ending point of the shape.
     */
    protected Point getEndPoint() {
        return myEndingPoint;
    }
    
}
