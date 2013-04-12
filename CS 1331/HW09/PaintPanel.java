import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents an abstract geometric object in a 2D space. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class PaintPanel extends JPanel {
	private InputPanel inputPanel;
	private Shape currentShape;
	private ArrayList<Shape> shapes;

	/**
	 * Private subclass. Implements the event handlers for the paint panel.
	 */
	private class PaintbrushMouseListener extends MouseAdapter {

		/**
		 * Method executed when the mouse is pressed down.
		 * Adds the selected shape from the input panel to the stack,
		 * sets its origin point.
		 */
		public void mousePressed(MouseEvent e) {
			currentShape = inputPanel.popCurrentShape();
			currentShape.setTopLeft(e.getPoint());
			shapes.add(currentShape);
		}

		/**
		 * Method executed when the mouse is moved while pressed.
		 * Resets the shape's maximum point, and redraws.
		 */
		public void mouseDragged(MouseEvent e) {
			currentShape.setBottomRight(e.getPoint());
			repaint();
		}

		/**
		 * Method executed when the mouse is released.
		 * Finalizes the current shape and redraws.
		 */
		public void mouseReleased(MouseEvent e) {
			currentShape.setBottomRight(e.getPoint());
			currentShape = null;
			repaint();
		}

	} // end private class PaintbrushMouseListener

	/**
	 * Primary constructor for a paint panel.
	 * @param input An input panel
	 */
	public PaintPanel(InputPanel input) {
		super();

		inputPanel = input;
		shapes = new ArrayList<Shape>();

		PaintbrushMouseListener listener = new PaintbrushMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	/**
	 * Drawing implementation for a paint canvas.
	 * @param g A Graphics subspace
	 */
	protected void paintComponent(Graphics g) {
		// draw background
		Rectangle size = g.getClipBounds();
		g.setColor(Color.white); 
		g.fillRect(size.x, size.y, size.width, size.height);

		// draw all shapes
		for (Shape thisShape : shapes) {
			thisShape.draw(g);
		}
	}

}