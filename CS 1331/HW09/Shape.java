import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

interface Fillable {
	boolean getFilled();
	void setFilled(boolean isFilled);
}

/**
 * This class represents an abstract geometric object in a 2D space. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public abstract class Shape {
	protected Point topLeft;
	protected Point bottomRight;
	protected Color color;

	/**
	 * The designated initializer for a Shape.
	 * @param topLeftCorner The origin point of the receiving shape.
	 * @param bottomRightCorner The maximum point of the receiving shape.
	 */
	public Shape(Point topLeftCorner, Point bottomRightCorner) {
		topLeft = topLeftCorner;
		bottomRight = bottomRightCorner;
	}

	/**
	 * An accessory initializer for a Shape.
	 * Note that the bottom-right corner of the receiver will need to be set later on.
	 * @param topLeft The origin point of the shape.
	 */
	public Shape(Point topLeftCorner) {
		this(topLeftCorner, topLeftCorner);
	}

	/**
	 * A simple initializer for a Shape.
	 * Note that both corners of the receiving shape will need to be set later on.
	 */
	public Shape() {
		this(new Point(0, 0), new Point(0, 0));
	}

	/**
	 * A convenience initializer for a shape, generating Points for you.
	 * @param topLeftX The horizontal origin of the receiving shape.
	 * @param topLeftY The vertical origin of the receiving shape.
	 * @param bottomRightX The horizontal maximum of the receiving shape.
	 * @param bottomRightY The vertical maximum of the receiving shape.
	 */
	public Shape(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
		this (new Point(topLeftX, topLeftY), new Point(bottomRightX, bottomRightY));
	}

	/**
	 * A convenience initializer for a shape, generating an origin Point for you.
	 * Note that the bottom-right corner of the receiver will need to be set later on.
	 * @param topLeftX The horizontal origin of the receiving shape.
	 * @param topLeftY The vertical origin of the receiving shape.
	 */
	public Shape(int topLeftX, int topLeftY) {
		this (new Point(topLeftX, topLeftY), new Point(topLeftX, topLeftY));
	}

	/**
	 * A getter for the origin point of a shape.
	 * @return The origin point of the receiving shape.
	 */
	public Point getTopLeft() {
		return topLeft;
	}

	/**
	 * A setter for the minimum point of a shape.
	 * @param newTopLeft The new origin point of the receiving shape.
	 */
	public void setTopLeft(Point newTopLeft) {
		topLeft = newTopLeft;
	}

	/**
	 * A getter for the maximum point of a shape.
	 * This property effectively represents the width and height of the shape.
	 * @return The maximum point of the receiving shape.
	 */
	public Point getBottomRight() {
		return bottomRight;
	}

	/**
	 * A setter for the maximum point of a shape.
	 * @param newBottomRight The new maximum point of the receiving shape.
	 */
	public void setBottomRight(Point newBottomRight) {
		bottomRight = newBottomRight;
	}
	
	/**
	 * A getter for the fill color of a shape.
	 * This property can be used by GUI implementations in drawing.
	 * @return The fill color for the receiving shape.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * A setter for the fill color of a shape.
	 * @param newColor The new fill color of the receiving shape.
	 */
	public void setColor(Color newColor) {
		color = newColor;
	};

	/**
	 * This abstract method should be implemented by subclasses.
	 * It is used to draw the outline and/or fill the shape.
	 * @param g A Graphics context.
	 */
	public abstract void draw(Graphics g);

	/**
	 * This method can be used to determine whether the given shape can be filled.
	 * @return true if the shape is 2D, false if 1D
	 */
	protected boolean supportsFilling() {
		return (this instanceof Fillable);
	}

} // end class Shape