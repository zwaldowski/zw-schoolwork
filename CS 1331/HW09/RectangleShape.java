import java.awt.Graphics;
import java.awt.Point;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a Shape, representing a quadrilateral with four right angles. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class RectangleShape extends Shape implements Fillable {
	protected boolean filled;

	/**
	 * The designated initializer for a rectangle.
	 * @param topLeftCorner The origin point of the receiving rectangle.
	 * @param bottomRightCorner The maximum point of the receiving rectangle.
	 * @param isFilled Whether or not the receiving rectangle will be filled or be just an outline.
	 */
	public RectangleShape(Point topLeftCorner, Point bottomRightCorner, boolean isFilled) {
		super(topLeftCorner, bottomRightCorner);
		filled = isFilled;
	}

	/**
	 * An accessory initializer for a rectangle.
	 * @param topLeftCorner The origin point of the receiving rectangle.
	 * @param bottomRightCorner The maximum point of the receiving rectangle.
	 */
	public RectangleShape(Point topLeftCorner, Point bottomRightCorner) {
		this(topLeftCorner, bottomRightCorner, false);
	}

	/**
	 * An accessory initializer for a rectangle.
	 * Note that this isn't strictly necessary, but you asked me to.
	 * Note that the bottom-right corner of the receiver will need to be set later on.
	 * @param topLeft The origin point of the rectangle.
	 */
	public RectangleShape(Point topLeftCorner) {
		this(topLeftCorner, topLeftCorner);
	}

	/**
	 * An simple initializer for a rectangle.
	 * Note that both corners of the receiving shape will need to be set later on.
	 */
	public RectangleShape() {
		super();
	}

	/**
	 * A getter for whether or not the receiving Rectangle is filled.
	 * @return true if the rectangle is colored in, false if it's just an outline.
	 */
	public boolean getFilled() {
		return filled;
	}

	/**
	 * A setter for the fill state of the receiving Rectangle.
	 * @param newIsFilled true if the rectangle should be colored in, false if it's just an outline.
	 */
	public void setFilled(boolean newIsFilled) {
		filled = newIsFilled;
	}

	/**
	 * The drawing implementation for a rectangle.
	 * Draws a solid rectangle if isFilled is true, or an outline if false.
	 */
	public void draw(Graphics g) {
		int width = bottomRight.x - topLeft.x;
		int height = bottomRight.y - topLeft.y;
		int x = topLeft.x;
		int y = topLeft.y;

		if (width < 0) {
			x = bottomRight.x;
			width *= -1;
		}

		if (height < 0) {
			y = bottomRight.y;
			height *= -1;
		}

		g.setColor(getColor());
		if (filled) {
			g.fillRect(x, y, width, height);
		} else {
			g.drawRect(x, y, width, height);
		}
	}

} // end class RectangleShape