import java.util.*; // Not using array stuff, just being lazy

//
// Homework 5, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates an card in a card deck. (HW5, Problem 1)
 *
 * @author Zachary Waldowski
 * @version 0.21-winnerwinner
 */
public class Card {
	private String face;
	private String suit;
	
	/**
	 * The designated initializer for the Card class.
	 *
	 * @param face The face value of the card, i.e., "A".
	 * @param suit The suit of the card, i.e., "Hearts".
	 */
	 public Card(String newFace, String newSuit) {
	 	face = newFace;
	 	suit = newSuit;
	 }
	
	/**
	 * A getter for the face/number of the card.
	 * @return The face value of the receiving card.
	 */
	 public String getFace() {
	 	return face;
	 }
	 
	 /**
	  * A getter for the suit of the card.
	  * @return The suit of the receiving card.
	  */
	 public String getSuit() {
	 	return suit;
	 }
	 
	 /**
	  * Concatenates the properties of a card.
	  * @return A description including the receiving card's face and suit.
	  */
	public String toString() {
		return "Card: " + getFace() + " of " + getSuit();
	}
	
	/**
	 * A test driver for the Card class.
	 *
	 * @param Command line arguments
	 */
	public static void main(String[] args) {
		String testFace = "A";
		String testSuite = "Spades";
		String testDesc = "Card: A of Spades";
		
		Card test = new Card(testFace, testSuite);
		if (!(test.getFace().equals(testFace))) {
			System.out.println("Test failed.");
			return;
		}
		
		if (!test.getSuit().equals(testSuite)) {
			System.out.println("Test failed.");
			return;
		}
		
		if (!test.toString().equals(testDesc)) {
			System.out.println("Test failed.");
			return;
		}
		
		System.out.println("Tests succeeded.");
	}
} // End class Card