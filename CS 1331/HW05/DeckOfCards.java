import java.util.*; // Not using array stuff, just being lazy

//
// Homework 5, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents a card deck. (HW5, Problem 2)
 *
 * @author Zachary Waldowski
 * @version 0.21-winnerwinner
 */
public class DeckOfCards {
	/**
	 * Constants for all possible suits.
	 */
	public static final String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
	
	/**
	 * Constants for all possible faces.
	 */
	public static final String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	/**
	 * Constants for all possible face values.
	 */
	public static final int[][] faceValues = {{1, 11}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {10}, {10}, {10}};
	
	private final int numberOfCards = 52;
	
	private Card[] deck;
	private Card[] pile;
	private int cardsLeft;
	private int nextCard;
	private static Random dealer = new Random();
	
	/**
	 * The designated initializer for a deck of cards. Accepts no parameters.
	 */
	public DeckOfCards() {
		deck = new Card[numberOfCards];
		pile = new Card[numberOfCards];
		cardsLeft = numberOfCards;
		
		int counter = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < faces.length; j++) {
				deck[counter] = new Card(faces[j], suits[i]);
				counter++;
			}
		}
	}
	
	/**
	 * A read-only getter for Cards that are in the recieving deck.
	 * @return An array of Card objects.
	 */
	public Card[] getDeck() {
		return deck;
	}
	
	/**
	 * A read-only getter for Cards that have exited the recieving deck, or the "pile."
	 * @return An array of Card objects.
	 */
	public Card[] getPile() {
		return pile;
	}
	
	/**
	 * A read-only getter for the number of cards left in the Deck.
	 * @return An integer representing the number of cards that are not in the pile.
	 */
	public int getCardsLeft() {
		return cardsLeft;
	}
	
	/**
	 * Shuffles the deck.
	 *
	 * Operates by placing all cards in the pile onto the deck,
	 * and then swapping each card with another. This is called
	 * a Fisher-Yates shuffle.
	 */
	public void shuffle() {
		// Put everything in the pile back in the deck.
		for (int i = 0; i < numberOfCards - cardsLeft; i++) {
			deck[cardsLeft + i] = pile[i];
			pile[i] = null;
		}
	 	
		for (int i = numberOfCards - 1; i >= 0; i--) {
			int newIndex = dealer.nextInt(numberOfCards);
			Card oldCard = deck[i];
			Card newCard = deck[newIndex];
			deck[i] = newCard;
			deck[newIndex] = oldCard;
		}

		cardsLeft = numberOfCards;
	}
	 
	/**
	 * Draws a card from the recieving Deck.
	 *
	 * If no cards exist in the Deck prior to dealing,
	 * a null value is returned.
	 *
	 * @return A Card instance.
	 */
	public Card deal() {
		if (cardsLeft == 0)
			return null;
		
		int index = dealer.nextInt(cardsLeft);
		Card card = deck[index];
		
		for (int i = index + 1; i < numberOfCards; i++) {
			deck[i - 1] = deck[i];
		}
		deck[numberOfCards - 1] = null;
		
		cardsLeft--;
		
		pile[numberOfCards - cardsLeft - 1] = card;
		
		return card;
	}
	
	/**
	 * Concatenates the properties of a card deck.
	 * @return A description of the recieving card deck.
	 */
	public String toString() {
		String ret = "Card Deck: " + cardsLeft + " cards remaining\n";
		
		for (int i = 0; i < cardsLeft; i++) {
			ret = ret.concat("\t- " + deck[i] + "\n");
		}
		
		if (cardsLeft > 0) {
			ret = ret.concat("\n");
		}
		
		return ret;
	}
	
	/**
	 * Counts the number of each type of face and suit (4 and 13 respective)
	 *
	 * This is a dirty hack way to verify all the cards are in the deck
	 * We will call this after your shuffle to verify didn't lose any
	 * cards. We will call this after shuffle to verify. Please note,
	 * the cards must also appear shuffled to get credits.
	 *
	 * @return true if the shuffle was loss-less, false otherwise
	 */
	public static boolean verifyDeck(DeckOfCards cardDeck)
	{
		int[] faceCount = new int[faces.length];
		int[] suitCount = new int[suits.length];

		for (Card card : cardDeck.deck) {
			String face = card.getFace();
			String suit = card.getSuit();
			for (int i = 0; i < faces.length; i++)
				if (face.equals(faces[i]))
					faceCount[i]++;
			for (int i = 0; i < suits.length; i++)
				if (suit.equals(suits[i]))
					suitCount[i]++;
		}

		//verify counts
		for(int i=0; i < faceCount.length; i++)
			if (faceCount[i]!=4) //if not 4 of each (1 for each suit)
				return false;

		for(int i=0; i < suitCount.length; i++)
			if (suitCount[i]!=13)
				return false;

		return true;
	}

	/* A test driver for the DeckOfCard class.
	 *
	 * @param Command line arguments
	 */
	public static void main(String[] args)
	{
		DeckOfCards deck = new DeckOfCards();
		System.out.println(deck);
		System.out.println("Verify Complete Deck: "+DeckOfCards.verifyDeck(deck));

		deck.shuffle();
		System.out.println("Shuffled deck:\n" + deck);
		System.out.println("Verify Complete Deck After Shuffle: "+DeckOfCards.verifyDeck(deck));

		System.out.println("Dealt: "+deck.deal());
		System.out.println("Dealt: "+deck.deal());
		System.out.println("Deck state (first 2 cards should not show in deck):\n"+deck);
 		
		//deal rest of cards but leave one remaining
		while(deck.getCardsLeft() > 1) {
			deck.deal();
		}
		System.out.println("Last card: " + deck.deal());

		if (deck.deal() != null) {
			System.out.println("You've dealt more cards than possible!");
		} else {
			System.out.println("Edge case passed!");
		}

		System.out.println("All cards should be dealt now:\n"+deck);
	}
} // End class DeckOfCards