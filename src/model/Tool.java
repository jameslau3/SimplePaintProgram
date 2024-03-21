/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * Interface that defines common behavior of tools used in the paint program.
 * 
 * @author James Lau
 * @version 1.0
 */
public interface Tool {
    /**
     * Method that returns shape that the tool will draw.
     * 
     * @return Shape that tool draws.
     */
    Shape getShape();
    
    /**
     * Method that sets the starting point of the shape being drawn.
     * 
     * @param thePoint the point that the starting point is set to.
     */
    void setStartingPoint(Point thePoint);
    
    /**
     * Method that sets the starting point of the shape being drawn.
     * 
     * @param thePoint
     */
    void setEndPoint(Point thePoint);

    /**
     * Method that returns whether if the tool can be filled or not.
     * 
     * @return Whether or not the tool is fillable.
     */
    boolean canFill();
    
    /**
     * Override of toString() method.
     */
    String toString();
}
