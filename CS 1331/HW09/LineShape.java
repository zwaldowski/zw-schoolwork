import java.awt.Graphics;
import java.awt.Point;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a Shape, representing a 1D link between of two points in a 2D space. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class LineShape extends Shape {

	/**
	 * The designated initializer for a line.
	 * @param topLeftCorner The starting point of the receiving line.
	 * @param bottomRightCorner The ending point of the receiving line.
	 */
	public LineShape(Point topLeftCorner, Point bottomRightCorner) {
		super(topLeftCorner, bottomRightCorner);
	}

	/**
	 * An accessory initializer for a line.
	 * Note that this isn't strictly necessary, but you asked me to.
	 * Note that the bottom-right corner of the receiver will need to be set later on.
	 * @param topLeft The starting point of the line.
	 */
	public LineShape(Point topLeftCorner) {
		this(topLeftCorner, topLeftCorner);
	}

	/**
	 * An simple initializer for a line.
	 * Note that both corners of the receiving shape will need to be set later on.
	 */
	public LineShape() {
		super();
	}

	/**
	 * The drawing implementation for a line.
	 * Draws a line segment.
	 */
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
	}

} // end class LineShape