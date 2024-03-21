/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A tool class used to draw lines.
 * 
 * @author James Lau
 * @version 1.0
 */
public class LineTool extends AbstractTool {

    @Override
    public Shape getShape() {
        // Return a line with start and end points.
        return new Line2D.Double(getStartingPoint(), getEndPoint());
    }
    
    @Override
    public boolean canFill() {
        return false;
    }
    
    /**
     * toString() Override method.
     */
    @Override
    public String toString() {
        return "Line";
    }
}
