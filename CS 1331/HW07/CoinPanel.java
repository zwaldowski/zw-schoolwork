import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

//
// Homework 7, Problem 3
// Zachary Waldowski
// CS 1331
//

/**
 * This class drives the UI for a coin betting game. (HW7.3)
 *
 * @author Zachary Waldowski
 * @version 0.1-lincoln
 */
public class CoinPanel extends JPanel {
	
	private Player player;
	private JLabel moneyLabel;
	private JLabel flipLabel;
	private JLabel warningLabel;
	private IntTextField betTextField;

	/**
	 * Internal helper: update text labels using current state.
	 */
	private void updateLabels() {
		moneyLabel.setText("Current money: " + player.getMoney());
		flipLabel.setText("Current flip: " + player.getCoinSide());
		warningLabel.setText(null);
	}

	/**
	 * Designated initializer for a coin panel.
	 */
	public CoinPanel() {
		player = new Player();

		JPanel centeredPanel = new JPanel();
		centeredPanel.setLayout(new BoxLayout(centeredPanel, BoxLayout.Y_AXIS));

		// money label
		moneyLabel = new JLabel();
		centeredPanel.add(moneyLabel);

		// flip label
		flipLabel = new JLabel();
		centeredPanel.add(flipLabel);

		// enter a bet label
		JLabel enterABetLabel = new JLabel("Enter a bet:");
		centeredPanel.add(enterABetLabel);

		// bet text field
		betTextField = new IntTextField(0);
		centeredPanel.add(betTextField);

		// bet heads button
		JButton betHeadsButton = new JButton("Bet Heads");
		betHeadsButton.addActionListener(new BetListener(Coin.Side.COIN_SIDE_HEADS));
		centeredPanel.add(betHeadsButton);

		// bet tails button
		JButton betTailsButton = new JButton("Bet Tails");
		betTailsButton.addActionListener(new BetListener(Coin.Side.COIN_SIDE_TAILS));
		centeredPanel.add(betTailsButton);

		// reset button
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetListener());
		centeredPanel.add(resetButton);

		// don't have money label
		warningLabel = new JLabel();
		centeredPanel.add(warningLabel);

		// update labels
		updateLabels();

		add(centeredPanel);
	}

	/**
	 * Internal subclass of JTextField that only accepts integers.
	 */
	private class IntTextField extends JTextField {

		/**
		 * Helper class that does the actual checking for integers only. Apparently
		 * this class is the primary data storage for a (subclassed?) text field.
		 * I'm not sure, but it's what the documentation implies. But it works.
		 */
		private class IntDocument extends PlainDocument {
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				try {
					Integer.parseInt(str);
				} catch (NumberFormatException e) {
					return;
				}

				super.insertString(offs, str, a);
			}
		} // end private class IntTextField

		/**
		 * Convince the text field to use our custom document.
		 */
		protected Document createDefaultModel() {
			return new IntDocument();
		}

		/**
		 * Public initializer. Initializes to 0.
		 */
		public IntTextField() {
			this(0);
		}

		/**
		 * Designated initializer. Initializes text field string using integer.
		 */
		public IntTextField(int startValue) {
			setIntValue(startValue);
		}

		/**
		 * Exception-safe getter for the current value of the text field.
		 * @return The integer value of this text field, or 0 if it's invalid.
		 */
		public int getIntValue() {
			try {
				return Integer.parseInt(this.getText());
			} catch (NumberFormatException e) {
				return 0;
			}
		}

		/**
		 * Sets the integer value of the recieving text field by
		 * parsing the given value into a string.
		 * @param value An integer to set as the contents of the text field.
		 */
		public void setIntValue(int value) {
			setText(Integer.toString((value)));
		}

	} // end private class IntTextField

	/**
	 * Event listener: place a bet based on the current bet value when the
	 * appropriate button is pressed.
	 */
	private class BetListener implements ActionListener {
		Coin.Side side;

		/**
		 * Initializes the BetListener with a coin value.
		 */
		public BetListener(Coin.Side coinSide) {
			side = coinSide;
		}

		/**
		 * Method called when the button is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			int bet = betTextField.getIntValue();

			if (player.getMoney() < bet) {
				warningLabel.setText("Not enough money!");
				return;
			}

			if (bet < 0)
				return;

			player.bet(bet, side);
			updateLabels();
		}

	} // end private class BetListener

	/**
	 * Event listener: reset the player when the appropriate button is pressed.
	 */
	private class ResetListener implements ActionListener {

		/**
		 * Method called when the button is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			player.reset();
			updateLabels();
		}

	} // end private class ResetListener

} // end class CoinPanel