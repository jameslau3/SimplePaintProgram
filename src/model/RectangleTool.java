/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A tool class used to draw rectangles.
 * 
 * @author James Lau
 * @version 2.0
 */
public class RectangleTool extends AbstractTool {

    @Override
    public Shape getShape() {
        final Rectangle2D.Double r = new Rectangle2D.Double();
        if (getEndPoint().getX() < getStartingPoint().getX() 
                && getEndPoint().getY() < getStartingPoint().getY()) {
            r.setRect(getEndPoint().getX(), getEndPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else if (getEndPoint().getX() < getStartingPoint().getX()) {
            r.setRect(getEndPoint().getX(), getStartingPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else if (getEndPoint().getY() < getStartingPoint().getY()) {
            r.setRect(getStartingPoint().getX(), getEndPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else {
            r.setRect(getStartingPoint().getX(), getStartingPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        }
        return r;
    }

    @Override
    public boolean canFill() {
        return true;
    }
    
    /**
     * toString() Override method.
     */
    @Override
    public String toString() {
        return "Rectangle";
    }
}
