import java.util.*; // Not using array stuff, just being lazy

//
// Homework 5, Problems 3 and 4
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates a player of a Blackjack game. (HW5, Problem 3/4)
 *
 * @author Zachary Waldowski
 * @version 0.21-winnerwinner
 */
public class BlackjackPlayer {
	private Card[] hand;
	private String playerName;
	private int numberOfCards;
	private static final int maximumHand = 22;
	private static final int[] invalidCardValue = {0};
	
	/**
	 * The designated initializer for the BlackjackPlayer class.
	 *
	 * @param playerName The name of the player.
	 */
	public BlackjackPlayer(String newPlayerName) {
		playerName = newPlayerName;
		numberOfCards = 0;
	}
	
	/**
	 * A read-only getter for the name of the player.
	 * @return The name of the recieving player.
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * A read-only getter for the number of cards in a player's hand.
	 * @return The number of card's in the recieving player's hand.
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}
	
	/**
	 * Gives the recieving player a new hand.
	 *
	 * @param firstCard A Card.
	 * @param secondCard Another Card.
	 */
	public void newHand(Card firstCard, Card secondCard) {
		hand = new Card[maximumHand];
		hand[0] = firstCard;
		hand[1] = secondCard;
		numberOfCards = 2;
	}
	
	/**
	 * Gives the player a new Card.
	 * 
	 * If the player has no existing hand, an exception is raised.
	 * If the player has too many cards, an exception is raised.
	 *
	 * @param A Card to add to the player's hand.
	 */
	public void addCard(Card card) throws Exception {
		if (hand == null || numberOfCards == 0) {
			throw new Exception("Player has no hand.");
		}
		
		if (numberOfCards == maximumHand) {
			throw new Exception("Player has too many cards.");
		}
		
		hand[numberOfCards] = card;
		numberOfCards++;
	}
	
	/**
	 * Removes all the cards from the player's hand.
	 *
	 * If the player has no existing hand, an exception is raised.
	 */
	public void clearHand() throws Exception {
		if (hand == null || numberOfCards == 0) {
			throw new Exception("Player has no hand.");
		}
		
		for (int i = 0; i < maximumHand; i++) {
			hand[i] = null;
		}
		numberOfCards = 0;
	}
	
	/**
	 * Evaluates the numerical value of a Card, with respect to a Blackjack game.
	 *
	 * @return An integer array of all possible values for the given card.
	 */
	public static int[] evaluateCard(Card card) {
		String thisFace = card.getFace();
		for (int i = 0; i < DeckOfCards.faces.length; i++) {
			if (DeckOfCards.faces[i].equals(thisFace)) {
				return DeckOfCards.faceValues[i];
			}
		}
		return invalidCardValue;
	}
	
	/**
	 * Returns all possible values of all the player's cards into a single array.
	 *
	 * @return An array of integer arrays.
	 */
	public int[][] handValues() {
		int[][] handValues = new int[numberOfCards][];
		for (int i = 0; i < numberOfCards; i++) {
			handValues[i] = evaluateCard(hand[i]);
		}
		return handValues;
	}
	
	/**
	 * Calculates the player's score in the game.
	 *
	 * @return The value of all their cards, including special cards.
	 */
	private int totalScore() throws Exception {
		int[][] values = handValues();
		int[][] multiples = new int[values.length][];
		int numberOfMultiples = 0, runningTotal = 0;
		
		for (int[] card : values) {
			if (card.length == 1) {
				runningTotal += card[0];
			} else {
				multiples[numberOfMultiples] = card;
				numberOfMultiples++;
			}
		}
			
		for (int i = 0; i < numberOfMultiples; i++) {
			int[] weirdCard = multiples[i];
			if (weirdCard.length == 2) {
				int higherCard, lowerCard;
				if (weirdCard[0] > weirdCard[1]) {
					higherCard = weirdCard[0];
					lowerCard = weirdCard[1];
				} else {
					higherCard = weirdCard[1];
					lowerCard = weirdCard[0];
				}
				
				if (runningTotal + higherCard > 21) {
					runningTotal += lowerCard;
				} else {
					runningTotal += higherCard;
				}
			} else {
				throw new Exception("Unhandled type of special card.");
			}
		}
		
		return runningTotal;
	}
	
	/**
	 * Calculates the player's state in the game.
	 *
	 * @return true or false if the player has busted
	 */
	public boolean bust() throws Exception {
		if (totalScore() > 21)
			return true;
		else
			return false;
	}
	
	/**
	 * Conditionally returns the player's winning score.
	 *
	 * @return If the player hasn't busted, returns their score; otherwise -1.
	 */
	public int bestScore() throws Exception {
		int runningTotal = totalScore();
		
		if (runningTotal > 21)
			return -1;
		else
			return runningTotal;		
	}
	
	/**
	 * Concatenates the properties of a Blackjack player.
	 * @return A description of the recieving Blackjack player.
	 */
	public String toString() {
		String ret = "Blackjack Player: " + playerName + "\n";
		
		for (int i = 0; i < numberOfCards; i++) {
			ret = ret.concat("\t- " + hand[i] + "\n");
		}
		
		if (numberOfCards > 0) {
			ret = ret.concat("\n");
		}
		
		return ret;
	}
	
	/**
	 * A test driver for the BlackjackPlayer class.
	 *
	 * @param Command line arguments
	 */
	public static void main(String[] args) throws Exception {
		// Given test cases
		BlackjackPlayer p = new BlackjackPlayer("George");
		DeckOfCards d = new DeckOfCards();
		p.newHand(d.deal(), d.deal());
		
		System.out.println("Check hand by individual cards (Should print two cards)");
		for (int i = 0; i < p.getNumberOfCards(); i++) {
			System.out.println(p.hand[i]);
		}
		System.out.println();

		System.out.println(p);
		System.out.println("Bust? "+p.bust()+"\n");

		System.out.println("Testing Aces: ");
		p.addCard(new Card("Ace", "Spades"));
		System.out.println(p+"\n");
		System.out.println("Testing Aces: ");
		p.addCard(new Card("Ace", "Clubs"));
		System.out.println(p+"\n");

		System.out.println("Adding cards to guarantee bust...");
		p.addCard(new Card("Jack", "Hearts"));
		System.out.println(p);
		System.out.println("Bust? "+p.bust());
		p.addCard(new Card("Jack", "Spades"));

		System.out.println(p);
		System.out.println("Bust? "+p.bust());
		if (p.bust() == false)
			System.out.println("Something is wrong - Didn't bust!");
		System.out.println();

		System.out.println("Testing clear (no cards should print: ");
		p.clearHand();
		System.out.println(p+"\n");
		
		// Custom test cases: basic adding
		BlackjackPlayer z = new BlackjackPlayer("Zachary");
		Card firstCard = new Card("2", "Diamonds");
		Card secondCard = new Card("3", "Hearts");
		Card thirdCard = new Card("Ace", "Spades");
		System.out.println("Adding cards: \n\t- " + firstCard + "\n\t- " + secondCard + "\n\t- " + thirdCard + "\n");
		z.newHand(firstCard, secondCard);
		z.addCard(thirdCard);
		System.out.println("Player: " + z);
		System.out.println("Score should be 16, and we shouldn't bust.");
		int result = z.bestScore();
		if (result == 16)
			System.out.println("It is.");
		else
			System.out.println("It isn't. It's " + result);
		boolean didBust = z.bust();
		if (didBust == true)
			System.out.println("It did.");
		else
			System.out.println("It didn't.");
	}
	
} // End class BlackjackPlayer