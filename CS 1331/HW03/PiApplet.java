import javax.swing.JApplet;
import java.awt.*;
import java.awt.geom.*;

// 
// Homework 3, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class approximates pi by "throwing" darts at a circle. (HW03, Problem 2)
 *
 * @author Zachary Waldowski
 * @version 1.0 9/13/2012
 */
public class PiApplet extends JApplet {
	/**
	 * This constant initialized value sets the number of iterations used to estimate Pi.
	 */
	private final int totalDarts;

	/**
	 * This generated value estimates Pi, representing the number of Darts inside the circle.
	 */
	private int dartsInsideCircle;

	/**
	 * A Dart representing the center of the drawn circle/square.
	 */
	private Dart centerDart;

	/**
	 * The designated initializer for a Pi applet.
	 * Sets the number of dart iterations with the given number of iterations.
	 */
	public PiApplet(int iterations) {
		this.totalDarts = iterations;
	}

	/**
	 * The default initializer for the Pi Applet.
	 * Normally uses 1000 darts.
	 */
	public PiApplet() {
		this(1000);
	}

	/*
	 * Generates a bounded, integral random number.
	 *
	 * Java's random returns a floating point between 0 and 1.
	 * This function extrapolates that for the maxiumum and
	 * bounds it to the minimum.
	 *
	 * @return A random number between the given bounds.
	 * @param min An integer that the random number can't go under.
	 * @param min An integer that the random number can't go above.
	 */
	private int random(int min, int max) {
		return Math.round((float)Math.random() * (max - min)) + min;
	}


	/*
	 * The primary drawing and calculation method for the applet.
	 *
	 * This method fits a square and circle within the drawing bounds,
	 * throws darts inside a circle, and estimates Pi using the number
	 * of darts that hit within the circle. All of this information
	 * is displayed on-screen.
	 */
	public void paint(Graphics g) {
		// Drawing colors
		Color squareBackgroundColor = Color.red;
		Color circleBackgroundColor = Color.green;
		Color outsideDartColor = Color.yellow;
		Color insideDartColor = Color.black;

		// Figuring out the geometry for our square
		Rectangle bounds = g.getClipBounds();
		final int size = Math.min(bounds.width - 20, bounds.height - 120);
		final int radius = Math.round(size / 2);
		final int shapeX = Math.round((bounds.width - size) / 2);
		final int shapeY = 10;

		// draw background square
		g.setColor(squareBackgroundColor); 
		g.fillRect(shapeX, shapeY, size, size);

		// draw background circle
		g.setColor(circleBackgroundColor); 
		g.fillOval(shapeX, shapeY, size, size);

		// configure 
		dartsInsideCircle = 0;
		this.centerDart = new Dart(radius, radius);

		// main dart iterative loop
    	g.setClip(new Rectangle(shapeX, shapeY, size, size));
		for (int i = 0; i < totalDarts; i++) {
			Dart thisIteration = new Dart(random(0, size), random(0, size));

			//System.out.println("x " + thisIteration.x() + " y " + thisIteration.y());

			double distance = centerDart.distance(thisIteration.x(), thisIteration.y());
			if (distance <= radius) {
				dartsInsideCircle++;
				thisIteration.color = insideDartColor;
			} else {
				thisIteration.color = outsideDartColor;
			}
			thisIteration.draw(g);
		}
    	g.setClip(bounds);

    	System.out.println("inside " + dartsInsideCircle + " total " + totalDarts);

		double calculatedPi = 4.0 * ((double)dartsInsideCircle / (double)totalDarts);

		// draw text
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		g.setColor(Color.black);

		int shapeMaxY = bounds.height - 90;

		g.drawString("Total Darts:  " + totalDarts, shapeX, shapeMaxY);
		shapeMaxY += 40;

		g.drawString("Calculated Pi:  " + calculatedPi, shapeX, shapeMaxY);
		shapeMaxY += 40;

		g.drawString("Official Pi:  " + Math.PI, shapeX, shapeMaxY);
	}
}
