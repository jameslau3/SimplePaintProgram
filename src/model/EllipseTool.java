/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A tool class used to draw ellipses.
 * 
 * @author James Lau
 * @version 2.0
 */
public class EllipseTool extends AbstractTool {

    @Override
    public Shape getShape() {
        final Ellipse2D.Double e = new Ellipse2D.Double();
        if (getEndPoint().getX() < getStartingPoint().getX() 
                && getEndPoint().getY() < getStartingPoint().getY()) {
            e.setFrame(getEndPoint().getX(), getEndPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else if (getEndPoint().getX() < getStartingPoint().getX()) {
            e.setFrame(getEndPoint().getX(), getStartingPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else if (getEndPoint().getY() < getStartingPoint().getY()) {
            e.setFrame(getStartingPoint().getX(), getEndPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        } else {
            e.setFrame(getStartingPoint().getX(), getStartingPoint().getY(),
                    Math.abs(getEndPoint().getX() - getStartingPoint().getX()),
                    Math.abs(getEndPoint().getY() - getStartingPoint().getY()));
        }
        return e;
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
        return "Ellipse";
    }
}