import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Point;

//
// Homework 8, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates the logic of the 8-Puzzle game board. (HW8.2)
 *
 * @author Zachary Waldowski
 * @version 8.8
 */
public class EightPuzzle {
	private final int emptyValue = 0;
	private int boardSize;
	private final int shufflesNum = 100;
	private static final Random randomizer = new Random();
	private int currentColumn;
	private int currentRow;
	private Piece[][] board;
	private enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	/**
	 * Initializes the 8-puzzle with a given board sive.
	 * Creates and sets all the pieces on the square gameboard.
	 * @param newBoardSize The length and height of the game board.
	 */
	private EightPuzzle(int newBoardSize) {
		boardSize = newBoardSize;
		
		reset();
	}
	
	/**
	 * Designated initializer for the 8-puzzle.
	 * Creates and sets all the pieces on the square gameboard.
	 */
	public EightPuzzle() {
		this(3);
	}
	
	/**
	 * Read-only getter for the board size.
	 * @return The size of the given board.
	*/
	public int getBoardSize() {
		return boardSize;
	}
	
	/**
	 * Scrambles the board.
	 *
	 * Randomly picks a direction relative to the empty
	 * space to move a piece into. Continues until a valid
	 * action is made (number of pieces + 1) times.
	 */
	public void scramble() {
		int successes = 0;
		Direction[] directions = Direction.values();
		while (successes < shufflesNum) {
			int randomDirectionIdx = randomizer.nextInt(directions.length);
			Direction randomDirection = directions[randomDirectionIdx];
			if (moveEmpty(randomDirection))
				successes++;
		}
	}
	
	/**
	 * Moves a piece from the given direction into the empty space, 
	 * relative to the empty space.
	 *
	 * @param A direction, relative to the empty space to move a
	 * piece from.
	 * @return Whether or not the move succeeded.
	 */
	private boolean moveEmpty(Direction direction) {
		int xOffset = 0, yOffset = 0;
		switch (direction) {
			case LEFT:
			xOffset = -1;
			break;
			case UP:
			yOffset = -1;
			break;
			case RIGHT:
			xOffset = 1;
			break;
			case DOWN:
			yOffset = 1;
			break;
			default: break;
		}
		
		Point emptyPoint = getEmptyPoint();
		int newX = emptyPoint.x + xOffset, newY = emptyPoint.y + yOffset;
		
		if (newX < 0 || newX >= boardSize || newY < 0 || newY >= boardSize)
			return false;
		
		return move(new Point(newX, newY));
	}
	
	/**
	 * Moves a piece at the given point into the empty space, if possible.
	 *
	 * @param A valid point on the game board to move.
	 * @return true if the given point has an adjacent empty
	 * space, false otherwise.
	 */
	public boolean move(Point piecePoint) {
		Point emptyPoint = getEmptyPoint();

		int deltaX = emptyPoint.x - piecePoint.x;
		int deltaY = emptyPoint.y - piecePoint.y;
		
		if (deltaX > 1 || deltaX < -1 || deltaY > 1 || deltaY < -1)
			return false;
		
		if (deltaX != 0 && deltaY != 0)
			return false;
		
		swapPieces(piecePoint, emptyPoint);
		return true;
	}
	
	/**
	 * Checks the game board for the win state.
	 * @return True if all the pieces are in their original point, false otherwise.
	 */
	public boolean isSolved() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				Piece thisPiece = board[col][row];
				Point expectedPoint = new Point(col, row);
				if (!thisPiece.getExpectedPoint().equals(expectedPoint))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Resets the game board to its initial state.
	 */
	public void reset() {
		board = new Piece[boardSize][boardSize];
		
		int i = 1, lastPiece = boardSize * boardSize;
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (i == lastPiece) i = emptyValue;
				String imagePath = "Pieces/Piece" + i + ".jpg";
				board[col][row] = new Piece(i, new ImageIcon(imagePath), col, row);
				i++;
			}
		}
	}
	
	/**
	 * For use with graphical implementations, copies
	 * the current state of the game board. 
	 * @return A deep copy of the game board. All Piece objects
	 * are read-only, so this shouldn't be a problem.
	 */
	public Piece[][] getGameView() {
		Piece[][] newBoard = new Piece[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				newBoard[col][row] = board[col][row];
			}
		}
		return newBoard;
	}
	
	/**
	 * Finds and returns the empty piece.
	 * @return The piece that represents the empty space.
	 */
	private Piece getEmptyPiece() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				Piece thisPiece = board[col][row];
				if (thisPiece.getValue() == emptyValue)
					return thisPiece;
			}
		}
		return null;
	
	}
	
	/**
	 * Finds and returns the location of empty piece.
	 * @return The location that is not occupied by any piece.
	 */
	public Point getEmptyPoint() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				Piece thisPiece = board[col][row];
				if (thisPiece.getValue() == emptyValue)
					return new Point(col, row);
			}
		}
		return null;
	}
	
	/**
	 * Internal convenience: moves sets the piece at a certain point.
	 * @param piecePoint The point we're setting the value for.
	 * @param pointPiece The piece we're setting the value to.
	 */
	private void setPieceAtPoint(Point piecePoint, Piece pointPiece) {
		board[piecePoint.x][piecePoint.y] = pointPiece;
	}
	
	/**
	 * Internal convenience: gets the piece at a certain point.
	 * @param piecePoint The point we're getting the value for.
	 * @return The piece at a specific point
	 */
	private Piece getPieceAtPoint(Point piecePoint) {
		return board[piecePoint.x][piecePoint.y];
	}
	
	/**
	 * Internal convenience: swaps the pieces at two points.
	 * Does no safety checking
	 * @param startPoint The first point to swap.
	 * @param endPoint The second point to swap.
	 */
	private void swapPieces(Point startPoint, Point endPoint) {
		Piece startPiece = getPieceAtPoint(startPoint);
		Piece endPiece = getPieceAtPoint(endPoint);
		setPieceAtPoint(endPoint, startPiece);
		setPieceAtPoint(startPoint, endPiece);
	}

} // end class EightPuzzle