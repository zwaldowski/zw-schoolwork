import java.util.Random;

//
// Homework 7, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class represents a coin. (HW7.1)
 *
 * @author Zachary Waldowski
 * @version 0.1-lincoln
 */
public class Coin {

	public enum Side {
		COIN_SIDE_HEADS, COIN_SIDE_TAILS;
		public String toString() {
			return this == Coin.Side.COIN_SIDE_HEADS ? "Heads" : "Tails";
		}
	};

	private Side side;
	private final Random random = new Random();

	/**
	 * The designated initializer for the Coin class.
	 */
	public Coin() {
		flip();
	}

	/**
	 * Randomly assigns the side of the coin to heads or tails.
	 */
	public void flip() {
		side = random.nextBoolean() ? Side.COIN_SIDE_HEADS : Side.COIN_SIDE_TAILS;
	}

	/**
	 * A read-only getter for the side of the coin.
	 * @return The upward-facing side of the receiving coin.
	 */
	public Side getSide() {
		return side;
	}

} // end class Coin