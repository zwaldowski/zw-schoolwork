import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

//
// Homework 9
// Zachary Waldowski
// CS 1331
//

/**
 * This class drives the primary UI of a paint application. (HW9)
 *
 * @author Zachary Waldowski
 * @version 3.1
 */
public class Paint {
	private static JFrame frame;
	private static InputPanel inputPanel;
	private static PaintPanel paintPanel;

	/*
	 * Creates the main frame of the application.
	 */
	static public void main(String[] args) {
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		inputPanel = new InputPanel();
		paintPanel = new PaintPanel(inputPanel);
		
		frame.add(inputPanel, BorderLayout.WEST);
		frame.add(paintPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(360, 380);
		frame.setVisible(true);
	}
} // end class Paint