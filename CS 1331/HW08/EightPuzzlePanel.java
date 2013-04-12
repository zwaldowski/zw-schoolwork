import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//
// Homework 8, Problem 3
// Zachary Waldowski
// CS 1331
//

/**
 * This class provides a reference implementation of the GUI for an 8-Puzzle game board. (HW8.3)
 *
 * @author Zachary Waldowski
 * @version 8.8
 */
public class EightPuzzlePanel extends JPanel {
	private EightPuzzle puzzleBoard;
	private JButton[][] buttonBoard;
	
	private class EightPuzzleButtonListener implements ActionListener {
		Point point;

		/**
		 * Initializes the EightPuzzleButtonListener with a point value.
		 */
		public EightPuzzleButtonListener(Point iPoint) {
			point = iPoint;
		}

		/**
		 * Method called when the button is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			if (puzzleBoard.move(point)) {
				updateUI();
				if (puzzleBoard.isSolved()) {
					gameWon();
				}
			}
		}
	} // end private class EightPuzzleButtonListener
	
	private class ResetButtonListener implements ActionListener {
		
		/**
		 * Method called when the button is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			puzzleBoard.reset();
			updateUI();
		}
		
	} // end private class ResetButtonListener
	
	private class ScrambleButtonListener implements ActionListener {
		
		/**
		 * Method called when the button is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			puzzleBoard.scramble();
			updateUI();
		}
		
	} // end private class ScrambleButtonListener
	
	public EightPuzzlePanel(EightPuzzle newPuzzleBoard) {
		puzzleBoard = newPuzzleBoard;
		
		int boardSize = puzzleBoard.getBoardSize();
		
		buttonBoard = new JButton[boardSize][boardSize];
		
		JPanel centeredPanel = new JPanel();
		centeredPanel.setLayout(new BoxLayout(centeredPanel, BoxLayout.Y_AXIS));
		
		for (int row = 0; row < boardSize; row++) {
			JPanel centeredRowPanel = new JPanel();
			centeredRowPanel.setLayout(new BoxLayout(centeredRowPanel, BoxLayout.X_AXIS));
			for (int col = 0; col < boardSize; col++) {
				JButton cellButton = new JButton();
				Point thisPoint = new Point(col, row);
				cellButton.addActionListener(new EightPuzzleButtonListener(thisPoint));
				buttonBoard[col][row] = cellButton;
				centeredRowPanel.add(cellButton);
			}
			centeredPanel.add(centeredRowPanel);
		}
		
		add(centeredPanel);
		
		JPanel centeredBottomButtons = new JPanel();
		centeredBottomButtons.setLayout(new BoxLayout(centeredBottomButtons, BoxLayout.X_AXIS));
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetButtonListener());
		centeredBottomButtons.add(resetButton);
		
		JButton scrambleButton = new JButton("Scramble");
		scrambleButton.addActionListener(new ScrambleButtonListener());
		centeredBottomButtons.add(scrambleButton);
		
		add(centeredBottomButtons);
		
		puzzleBoard.scramble();
		updateUI();
	}
	
	public void updateUI() {
		super.updateUI();
		
		if (puzzleBoard != null) {
			Piece[][] currentBoard = puzzleBoard.getGameView();
			int boardSize = puzzleBoard.getBoardSize();

			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize; col++) {
					Piece thisPiece = currentBoard[col][row];
					JButton cellButton = buttonBoard[col][row];
					cellButton.setIcon(thisPiece.getImage());
				}
			}
		}
	}
	
	private void gameWon() {
		int result = JOptionPane.showConfirmDialog(this, "You win! Play again?", "Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (result) {
			case JOptionPane.YES_OPTION:
				puzzleBoard.scramble();
				updateUI();
			break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
			break;
			case JOptionPane.CANCEL_OPTION:
			default:
			break;
		}
		
	}

} // end class EightPuzzlePanel