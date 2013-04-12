import java.awt.*;

// 
// Homework 3, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents a Dart. (HW3, Problem 1)
 *
 * @author Zachary Waldowski
 * @version 1.0 9/13/2012
 */
public class Dart {
	private int x;
	private int y;

	/**
	 * A runtime property used for setting the color of the drawn dart.
	 */
	public Color color;

	/**
	 * A runtime property used for setting the radius of the drawn dart.
	 */
	public int radius;

	/**
	 * The designated initializer for the Dart class.
	 *
	 * @param x The horizontal coordinate of the Dart.
	 * @param y The vertical coordinate of the Dart.
	 *
	 */
	public Dart(int x, int y) {
		this.x = x;
		this.y = y;
		this.radius = 2;
	} // end initializer

	/**
	 * Calculates the distance between the Dart and another coordinate.
	 *
	 * @param x2 The horizontal coordinate of the point to compare. 
	 * @param y2 The vertical coordinate of the point to compare.
	 * @return The distance, in points, between this Dart and another.
	 */
	public double distance(int x2, int y2) {
		// d = sqrt((x2-x1)^2+(y2-y1)^2)
		double x1 = this.x, y1 = this.y;
		double xDiff = Math.pow(x2 - x1, 2.0);
		double yDiff = Math.pow(y2 - y1, 2.0);
		return Math.sqrt(xDiff + yDiff);
	} // end distance

	/**
	 * A read-only getter for the horizontal coordinate of this Dart.
	 */
	public int x() {
		return x;
	} 

	/**
	 * A read-only getter for the vertical coordinate of this Dart.
	 */
	public int y() {
		return y;
	} 

	/**
	 * Draws the receiving Dart in a given graphics canvas.
	 *
	 * @param g A graphics canvas to draw the Dart into.
	 */
	public void draw(Graphics g) {
		Color backup = g.getColor();
		Rectangle rect = g.getClipBounds();
		g.setColor(color);
		g.fillOval(rect.x + x - radius, rect.y + y - radius, radius * 2, radius * 2);
		g.setColor(backup);
	} // end draw

} // end class Dart