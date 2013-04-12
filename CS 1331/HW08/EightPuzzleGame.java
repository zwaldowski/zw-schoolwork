import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//
// Homework 8, Problem 4
// Zachary Waldowski
// CS 1331
//

/**
 * This class provides a driver for the EightPuzzlePanel GUI. (HW8.4)
 *
 * @author Zachary Waldowski
 * @version 8.8
 */
public class EightPuzzleGame {
	private static JFrame frame;
	private static EightPuzzle puzzleBoard;
	
	/*
	 * Creates the main frame of the application.
	 */
	static public void main(String[] args) {
		frame = new JFrame("Eight Puzzle - ZW");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		puzzleBoard = new EightPuzzle();
		EightPuzzlePanel panel = new EightPuzzlePanel(puzzleBoard);
		frame.add(panel);
		frame.pack();
		frame.setSize(480, 480);
		frame.setVisible(true);
	
	}

} // end class EightPuzzleGame