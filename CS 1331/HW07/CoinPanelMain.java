import javax.swing.JPanel;
import javax.swing.JFrame;

//
// Homework 7, Problem 4
// Zachary Waldowski
// CS 1331
//

/**
 * This class creates the panel used for the coin playing game. (HW7.4)
 *
 * @author Zachary Waldowski
 * @version 0.1-lincoln
 */
public class CoinPanelMain {
	private static JFrame frame;

	/*
	 * Creates the main frame of the application.
	 */
	public static void main(String[] args) {
		frame = new JFrame("Coin Toss - ZW");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new CoinPanel());
		frame.pack();
		frame.setSize(320, 480);
		frame.setVisible(true);
	}

} // end class CoinPanelMain