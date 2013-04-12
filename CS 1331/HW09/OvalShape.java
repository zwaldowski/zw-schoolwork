import java.awt.Graphics;
import java.awt.Point;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a Shape that resembles an outline of an egg. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class OvalShape extends Shape implements Fillable {
	protected boolean filled;

	/**
	 * The designated initializer for an oval.
	 * @param topLeftCorner The origin point of the receiving oval.
	 * @param bottomRightCorner The maximum point of the receiving oval.
	 * @param isFilled Whether or not the receiving oval will be filled or be just an outline.
	 */
	public OvalShape(Point topLeftCorner, Point bottomRightCorner, boolean isFilled) {
		super(topLeftCorner, bottomRightCorner);
		filled = isFilled;
	}

	/**
	 * An accessory initializer for an oval.
	 * @param topLeftCorner The origin point of the receiving oval.
	 * @param bottomRightCorner The maximum point of the receiving oval.
	 */
	public OvalShape(Point topLeftCorner, Point bottomRightCorner) {
		this(topLeftCorner, bottomRightCorner, false);
	}

	/**
	 * An accessory initializer for an oval.
	 * Note that this isn't strictly necessary, but you asked me to.
	 * Note that the bottom-right corner of the receiver will need to be set later on.
	 * @param topLeft The origin point of the oval.
	 */
	public OvalShape(Point topLeftCorner) {
		this(topLeftCorner, topLeftCorner);
	}

	/**
	 * An simple initializer for a oval.
	 * Note that both corners of the receiving shape will need to be set later on.
	 */
	public OvalShape() {
		super();
	}

	/**
	 * A getter for whether or not the receiving oval is filled.
	 * @return true if the oval is colored in, false if it's just an outline.
	 */
	public boolean getFilled() {
		return filled;
	}

	/**
	 * A setter for the fill state of the receiving oval.
	 * @param newIsFilled true if the oval should be colored in, false if it's just an outline.
	 */
	public void setFilled(boolean newIsFilled) {
		filled = newIsFilled;
	}

	/**
	 * The drawing implementation for an oval.
	 * Draws a solid oval if isFilled is true, or an outline if false.
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
			g.fillOval(x, y, width, height);
		} else {
			g.drawOval(x, y, width, height);
		}
	}

} // end class OvalShape