/*
 * James Lau
 * TCSS 305 Autumn 2022
 * Assignment 5
 */
package model;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * A tool class used to draw lines.
 * 
 * @author James Lau
 * @version 1.0
 */
public class PencilTool extends AbstractTool {

    private final List<Point> myPoints = new ArrayList<>();

    @Override
    public void setStartingPoint(final Point thePoint) {
        super.setStartingPoint(thePoint);
        myPoints.clear();
        myPoints.add(thePoint);
    }

    @Override
    public void setEndPoint(final Point thePoint) {
        super.setEndPoint(thePoint);
        myPoints.add(thePoint);
    }

    @Override
    public Shape getShape() {
        if (myPoints.isEmpty()) {
            return null;
        }
        GeneralPath path = new GeneralPath();
        Point start = getStartingPoint();
        if (start == null) {
            start = myPoints.get(0);
        }
        path.moveTo(start.getX(), start.getY());
        // Use all points except the first, since moveTo already used it
        for (int i = 1; i < myPoints.size(); i++) {
            Point p = myPoints.get(i);
            path.lineTo(p.getX(), p.getY());
        }
        return path;
    }

    @Override
    public boolean canFill() {
        return false;
    }

    @Override
    public String toString() {
        return "Pencil";
    }
}