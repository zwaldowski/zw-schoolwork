//
// Homework 7, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents a coin game player. (HW7.2)
 *
 * @author Zachary Waldowski
 * @version 0.1-lincoln
 */
public class Player {
	private final int startingMoney = 10;
	private int money;
	private Coin coin;

	/**
	 * The simple initializer for the Player class.
	 */
	public Player() {
		this(new Coin());
	}

	/**
	 * The designated initializer for the Player class.
	 * @param coin The player's coin.
	 */
	public Player(Coin icoin) {
		coin = icoin;
		reset();
	}

	/**
	 * A read-only getter for the amount of player money.
	 * @return The amount of money in the player's pot.
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * A read-only getter for current side of the coin.
	 * @return The side of the coin of the recieving player.
	 */
	public Coin.Side getCoinSide() {
		return coin.getSide();
	}

	/**
	 * Bets on the result of the coin toss.
	 * The bet amount will be added to or subtracted from the user's pot, depending on the result of the play.
	 * @param betAmount The amount of the bet to be added to or subtracted from the player's pot.
	 * @param side The coin side they are betting in favor of.
	 * @return true if the bet succeeded, false otherwise
	 */
	public boolean bet(int betAmount, Coin.Side side) {
		coin.flip();
		if (coin.getSide() == side) {
			money += betAmount;
			return true;
		} else {
			money -= betAmount;
			return false;
		}
	}

	/**
	 * Resets the player's money to the starting amount.
	 */
	public void reset() {
		money = startingMoney;
	}

} // end class Player