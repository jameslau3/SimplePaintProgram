package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

public class ShapeComponents {
    /**
     * The shapes drawn.
     */
    private final Shape myShape;
    /**
     * The color of the shape.
     */
    private final Color myColor;
    /**
     * The fill color of the shape.
     */
    private final Color myFillColor;
    /**
     * Whether the shape is filled or not.
     */
    private final Boolean myShapeFilled;
    /**
     * The thickness of the shape.
     */
    private final BasicStroke myThickness;
    
    /**
     * Constructs a class that holds all the component values of a drawn shape.
     */
    public ShapeComponents(final Shape theShape, final Color theColor, 
            final Color theFillColor, final Boolean theShapeFilled
            , final BasicStroke theThickness) {
        myShape = theShape;
        myColor = theColor;
        myFillColor = theFillColor;
        myShapeFilled = theShapeFilled;
        myThickness = theThickness;
    }
    
    /**
     * Getter that returns the shape drawn.
     * @return the Shape drawn.
     */
    public Shape getShape() {
        return myShape;
    }
    /**
     * Getter that returns the color of the shape drawn.
     * @return the color of the Shape.
     */
    public Color getColor() {
        return myColor;
    }
    /**
     * Getter that returns the fill color of the shape drawn.
     * @return the fill color of the shape drawn.
     */
    public Color getFillColor() {
        return myFillColor;
    }
    /**
     * Getter that returns whether if the shape is filled or not.
     * @return the status of the shape being filled or not.
     */
    public Boolean isFilled() {
        return myShapeFilled;
    }
    /**
     * Getter that returns the thickness of the shape drawn.
     * @return the thickness of the shape drawn.
     */
    public BasicStroke getThickness() {
        return myThickness;
    }
}
