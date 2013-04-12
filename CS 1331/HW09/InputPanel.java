import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a button panel of drawing controls for a paint app. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class InputPanel extends JPanel {
	private JButton ovalButton;
	private JButton rectButton;
	private JButton lineButton;
	private JCheckBox filledCheck;
	private ColorButton colorSampleWell;
	private Color currentColor;
	private Shape currentShape;

	/**
	 * Internal method used when the shape is changed.
	 * Used to maintain state (color and fillable).
	 */
	private void didChangeShape() {
		if (currentShape.supportsFilling()) {
			filledCheck.setEnabled(true);
			((Fillable)currentShape).setFilled(shouldFill());
		} else {
			filledCheck.setEnabled(false);
		}
	}

	/**
	 * Internal subclass: JButton that is filled with a single color.
	 * Primarily used to get mouse actions and layouting for free.
	 */
	private class ColorButton extends JButton {
		private Color fillColor;

		public void setColor(Color newColor) {
			fillColor = newColor;
			repaint();
		}

		public Color getColor() {
			return fillColor;
		}

		protected void paintComponent(Graphics g) {
			Insets inset = getInsets();
			Rectangle bounds = g.getClipBounds();
			int width = bounds.width - inset.left - inset.right;
			int height = bounds.height - inset.top - inset.bottom;

			// draw background
			g.setColor(fillColor); 
			g.fillRect(inset.left, inset.top, width, height);
		}
	}

	/**
	 * Internal class: listens for and responds to the shape selector boxes.
	 */
	private class ShapeButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if (button == ovalButton) {
				currentShape = new OvalShape();
			} else if (button == rectButton) {
				currentShape = new RectangleShape();
			} else if (button == lineButton) {
				currentShape = new LineShape();
			}
			didChangeShape();
		};

	} // end private class ShapeButtonListener

	/**
	 * Internal class: listens for and responds to the "Filled" checkbox.
	 */
	private class FillCheckListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JCheckBox checkbox = (JCheckBox)e.getSource();
			if (currentShape.supportsFilling()) {
				((Fillable)currentShape).setFilled(shouldFill());
			}
		}

	} // end private class FillCheckListener

	/**
	 * Internal class: listens for and responds to the "Color..." button 
	 * and the color well (see ColorButton).
	 */
	private class ColorWellButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Color newColor = JColorChooser.showDialog(null, "Choose Shape Color", currentColor);
			if (newColor != null) {
				currentColor = newColor;
				colorSampleWell.setColor(currentColor);
			}
		}

	} // end private class ColorWellButtonListener

	/**
	 * Internal class: listens for and responds to the "Exit" button.
	 */
	private class ExitButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	} // end private class ExitButtonListener

	/**
	 * Primary constructor for the input panel.
	 */
	public InputPanel() {
		currentColor = Color.black;
		currentShape = new OvalShape();

		JPanel centeredPanel = new JPanel();
		centeredPanel.setLayout(new GridLayout(0, 1));

		// shape buttons
		ovalButton = new JButton("Oval");
		rectButton = new JButton("Rectangle");
		lineButton = new JButton("Line");

		ActionListener shapeButtonListener = new ShapeButtonListener();

		ovalButton.addActionListener(shapeButtonListener);
		rectButton.addActionListener(shapeButtonListener);
		lineButton.addActionListener(shapeButtonListener);

		centeredPanel.add(ovalButton);
		centeredPanel.add(rectButton);
		centeredPanel.add(lineButton);

		// to fill or not to fill checkmark
		filledCheck = new JCheckBox("Filled");
		filledCheck.setHorizontalAlignment(SwingConstants.CENTER);
		filledCheck.addActionListener(new FillCheckListener());
		centeredPanel.add(filledCheck);

		// color button, label, and well
		JButton colorPickerButton = new JButton("Color...");
		colorSampleWell = new ColorButton();
		colorSampleWell.setColor(currentColor);

		ActionListener colorListener = new ColorWellButtonListener();

		colorPickerButton.addActionListener(colorListener);
		colorSampleWell.addActionListener(colorListener);

		centeredPanel.add(colorPickerButton);
		centeredPanel.add(new JLabel("Current Color:", SwingConstants.CENTER));
		centeredPanel.add(colorSampleWell);

		// exit button
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ExitButtonListener());
		centeredPanel.add(quitButton);
		add(centeredPanel);
	}

	/**
	 * Convenience getter for whether the current shape should be filled.
	 * @returns true if the filled check box is checked, false otherwise.
	 */
	private boolean shouldFill() {
		return filledCheck.isSelected();
	}

	/**
	 * Returns the current shape and creates a new one for GUI implementations.
	 *
	 * The return value will have its appropriate color set, but not its
	 * coordinate position values.
	 *
	 * @return An instance of the current selected shape.
	 */
	public Shape popCurrentShape() {
		Shape ret = currentShape;

		try {
			currentShape = currentShape.getClass().newInstance();
		}
		catch (InstantiationException e) {
			currentShape = new LineShape();
		}
		catch (IllegalAccessException e) {
			currentShape = new LineShape();
		}
		didChangeShape();

		ret.setColor(currentColor);
		return ret;
	}

} // end class InputPanel