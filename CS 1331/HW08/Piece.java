import javax.swing.ImageIcon;
import java.awt.Point;

//
// Homework 8, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents a single Piece that is going to be used on the 8-Puzzle game board. (HW8.1)
 *
 * @author Zachary Waldowski
 * @version 8.8
 */
public class Piece {
	private final int value;
	private ImageIcon image;
	private final Point expectedPoint;
	
	/**
	 * Initializes an object of the Piece class with a value, image, and final expected coordinate.
	 * 
	 * @param cValue Integer value for the piece
	 * @param cImage Icon for the piece
	 * @param cExpectedCol Horizontal (x) value for the final expected point
	 * @param cExpectedRow Vertical (y) value for the final expected point
	 */
	public Piece(int cValue, ImageIcon cImage, int cExpectedCol, int cExpectedRow) {
		this(cValue, cImage, new Point(cExpectedCol, cExpectedRow));
	}
	
	/**
	 * Initializes an object of the Piece class with a value, image, and final expected point.
	 * 
	 * @param cValue Integer value for the piece
	 * @param cImage Icon for the piece
	 * @param cExpectedPoint Final expected point for the piece
	 */
	public Piece(int cValue, ImageIcon cImage, Point cExpectedPoint) {
		value = cValue;
		image = cImage;
		expectedPoint = cExpectedPoint;
	}
	
	/**
	 * Read-only getter for the value of the piece.
	 * @return The integer value of the receiving piece.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Read-only getter for the image representation of the piece.
	 * @return The image for the receiving piece.
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * Read-only getter for the final expected location of the piece.
	 * @return A Point representing the expected location of the receiving piece.
	 */
	public Point getExpectedPoint() {
		return expectedPoint;
	}
	

} // end class Piece