import java.util.Scanner;

//
// Homework 5, Problem 5
// Zachary Waldowski
// CS 1331
//

/**
 * This class plays a real live Blackjack game (just without the snarky dealers or the booze).
 *
 * It is heavily based on the sample class, just cleaned up. Don't expect miracles.
 *
 * @author Zachary Waldowski/Sundeep Ghuman
 * @version 0.21-winnerwinner
 */
public class Blackjack {
	private BlackjackPlayer player, computer;
	private String dealerHand;
	private Card visibleCard;
	private DeckOfCards deck;
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * The designated initializer for a Blackjack game.
	 *
	 * @param playerName The name of the player.
	 */
	public Blackjack(String playerName) {
		deck = new DeckOfCards();
		
		computer = new BlackjackPlayer("Dealer");
		player = new BlackjackPlayer(playerName);
		
		dealStartingHands();
	}
	
	/**
	 * Deals the starting hands to the players.
	 */
	private void dealStartingHands() {
		deck.shuffle();
		
		Card visibleCard = deck.deal();
		computer.newHand(visibleCard, deck.deal());
		dealerHand = visibleCard + "and others";
		
		player.newHand(deck.deal(), deck.deal());
	}
	
	/**
	 * Resets both players' hands to start a new round.
	 */
	public void newRound() throws Exception {
		computer.clearHand();
		player.clearHand();
		
		dealStartingHands();
	}
	
	/**
	 * Concatenates the properties of a Blackjack game.
	 * @return A description of the recieving Blackjack game.
	 */
	public String toString() {
		return "Blackjack Game\nDealer's Hand: " + dealerHand + "\n" + player;
	}
	
	/*
	 * Prompt for user string input.
	 *
	 * @param prompt The prompt for input to print
	 * @return The String input that user entered in the console
	 */
	public static String promptLine(String prompt)
	{
		System.out.print(prompt+"> ");
		return scan.nextLine();
	}
	
	/*
	 * Makes a move for a player. If the player chooses to hit rather
	 * than stay, the method will return true indicating that the player
	 * will continue, unless the hit results in a bust and the player
	 * cannot go on.
	 *
	 * @return true if the player can go again, otherwise false
	 */
	public boolean playerMove() throws Exception {
		System.out.println(this);
		String move = promptLine("Hit? (yes/no/hit/stay)");
		if (move.equalsIgnoreCase("n") || move.equalsIgnoreCase("no") || move.equalsIgnoreCase("stay")) {
			return false;
		} else if (move.equalsIgnoreCase("y") || move.equalsIgnoreCase("yes") || move.equalsIgnoreCase("hit")) {
			hit(player);
			if (player.bust())
				return false;
			else 
				return true;
		} else {
			System.out.println("Not a valid move - try again");
			return playerMove();
		}
	}

	/*
	 * The computational loop for the computer's turn.
	 *
	 * @return true if the dealer wins or ties, otherwise false.
	 */
	public boolean dealerMove() throws Exception {
		int playerScore = player.bestScore();
		int computerScore = computer.bestScore();
		if (player.bust() || computer.bust())
			return true;
		
		System.out.println(player.getPlayerName() + "'s score: " + playerScore);
		System.out.println(computer);
		while (playerScore > computerScore) {
			promptLine("Dealer's turn - Hit enter to continue");
			System.out.println("\nDealer Hits");
			hit(computer);
			computerScore = computer.bestScore();
			System.out.println(computer);
			if (computer.bust()) {
				System.out.println("Dealer Busted!");
				return false;
			}
		}
		return true;
	}

	/**
	 * Gives a player a card from the deck.
	 * @param player A BlackjackPlayer.
	 */
	public void hit(BlackjackPlayer player) throws Exception  {
		player.addCard(deck.deal());
	}
	
	/**
	 * Asks the user if they want to play again.
	 */
	private static boolean wantsToPlayAgain() {
		String playAgain = promptLine("Play again? (yes/no):");
		if (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y")) {
			return true;
		}
		return false;
	}

	/**
	 * The primary entry point for the game.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) throws Exception {
		String player = promptLine("Enter your name:");
		Blackjack game = new Blackjack(player);
		boolean keepPlaying = true;

		while (keepPlaying)
		{
			boolean playerContinue = true;
			do {
				playerContinue = game.playerMove();
			} while (playerContinue);
	
			if (game.player.bust()) {
				System.out.println(player+" Busted - Loser!\nDealer Wins");
			} else {
				boolean dealerWin = game.dealerMove();
				System.out.println(player+"'s score: "+game.player.bestScore());
				if (dealerWin)
					System.out.println("Dealer Wins");
				else //Dealer plays until win or bust
					System.out.println(game.player.getPlayerName()+" Wins!");
			}
			
			if (game.deck.getCardsLeft() < 10) {
				System.out.println("Not enough cards to keep playing. See ya!");
				keepPlaying = false;
			} else {
				keepPlaying = wantsToPlayAgain();
			}
			
			if (keepPlaying) {
				game.newRound();
			}
		}
	}
	
} // End class Blackjack