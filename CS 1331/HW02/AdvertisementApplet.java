import javax.swing.JApplet;
import java.awt.*;
import java.awt.geom.*;

// 
// Homework 2, Problem 3
// Zachary Waldowski
// CS 1331
//

/**
 * This class draws an advertisement. (HW02, Problem 3)
 */
public class AdvertisementApplet extends JApplet {
	/**
	 * This generated value represents the scale of the square workspace for the shape.
	 */
	float scale;

	/**
	 * Using `scale`, this function returns a multiplied point for the canvas.
	 */
	public int adjustedPoint(float point, float size) {
		return Math.round((point / size) * scale);
	}

	/**
	 * Draws the "advertisement", using three thickly-bordered rectangles, a
	 * stroke around the whole thing, and "Coming Soon" text. Thoroughly impressive.
	 */
	public void paint(Graphics g) {
		Rectangle size = g.getClipBounds();

		// draw background
		g.setColor(new Color(204, 204, 204)); 
		g.fillRect(size.x, size.y, size.width, size.height);

		// Math for where we put our shapes
		scale = Math.min(size.width - 20, size.height - 20);
		final int shapeX = Math.round((size.width - scale) / 2);
		final int shapeY = Math.round((size.height - scale) / 2) - 10;

		// Math for drawing the outer rects
		final int outerRectSize = adjustedPoint(30, 48);
		final int innerRectSize = adjustedPoint(24, 48);

		Polygon wholeShape = new Polygon();
		Color outerRectColor, innerRectColor;
		int outerRectX, outerRectY, innerRectX, innerRectY;

		final int gap = adjustedPoint(6, 48);

		// Back shape, outer
		outerRectX = shapeX + adjustedPoint(15, 48);
		outerRectY = shapeY + adjustedPoint(3, 48);
		wholeShape.addPoint(outerRectX, outerRectY + gap);
		wholeShape.addPoint(outerRectX, outerRectY);
		wholeShape.addPoint(outerRectX + outerRectSize, outerRectY);
		wholeShape.addPoint(outerRectX + outerRectSize, outerRectY + outerRectSize);
		wholeShape.addPoint(outerRectX + outerRectSize - gap, outerRectY + outerRectSize);
		g.setColor(new Color(138, 146, 156));
		g.fillRect(outerRectX, outerRectY, outerRectSize, outerRectSize);

		// Back shape, inner
		innerRectX = shapeX + adjustedPoint(18, 48);
		innerRectY = shapeY + gap;
		g.setColor(new Color(110, 108, 115));
		g.fillRect(innerRectX, innerRectY, innerRectSize, innerRectSize);

		// Mid shape, outer
		outerRectX = shapeX + adjustedPoint(3, 48);
		outerRectY = shapeY + adjustedPoint(9, 48);
		g.setColor(new Color(131, 174, 230));
		g.fillRect(outerRectX, outerRectY, outerRectSize, outerRectSize);

		// Mid shape, inner
		innerRectX = shapeX + adjustedPoint(6, 48);
		innerRectY = shapeY + adjustedPoint(12, 48);
		g.setColor(new Color(102, 135, 178));
		g.fillRect(innerRectX, innerRectY, innerRectSize, innerRectSize);

		// Front shape, outer
		outerRectX = shapeX + adjustedPoint(9, 48);
		outerRectY = shapeY + adjustedPoint(15, 48);
		wholeShape.addPoint(outerRectX + outerRectSize, outerRectY + outerRectSize);
		wholeShape.addPoint(outerRectX, outerRectY + outerRectSize);
		wholeShape.addPoint(outerRectX, outerRectY + outerRectSize - gap);
		g.setColor(new Color(204, 230, 69));
		g.fillRect(outerRectX, outerRectY, outerRectSize, outerRectSize);

		// Front shape, inner
		innerRectX = shapeX + adjustedPoint(12, 48);
		innerRectY = shapeY + adjustedPoint(18, 48);
		g.setColor(new Color(158, 178, 54));
		g.fillRect(innerRectX, innerRectY, innerRectSize, innerRectSize);

		// Mid shape, outer (again)
		outerRectX = shapeX + adjustedPoint(3, 48);
		outerRectY = shapeY + adjustedPoint(9, 48);
		wholeShape.addPoint(outerRectX, outerRectY + outerRectSize);
		wholeShape.addPoint(outerRectX, outerRectY);

		// Stroke, outer
		g.setColor(new Color(100, 100, 100));
		g.drawPolygon(wholeShape);

		// Text, bottom
		Font font = new Font("Helvetica", Font.BOLD, 14);
		String text = "Coming soon.";

		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		Rectangle2D bounds = metrics.getStringBounds(text, g);
		long textStartX = Math.round((size.width - bounds.getWidth()) / 2);
		g.drawString(text, (int)textStartX, Math.round(size.height - 15));
	}
	
}
