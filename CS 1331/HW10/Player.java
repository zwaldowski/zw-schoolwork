import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the playable character in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Player extends Character {

    /**
     * Stores the Game the Player is functioning in.
     */
    private final Game game;

    /**
     * Stores the number of health points remaining for the player: Max 100, Min 0.
     */
    private int health;

    /**
     * Stores the number of points accumulated by collected objects.
     */
    private int score;

    /**
     * Stores the images used to reflect the player's state.
     */
    private static final Map<Integer, ImageIcon> imageIcons = createIconMap();

    /**
     * Internal initializer for the possible states of the players' image.
     * @return A Map from 0-100 by tens to different images.
     */
    private static Map<Integer, ImageIcon> createIconMap() {
        Map<Integer, ImageIcon> result = new HashMap<Integer, ImageIcon>();
        result.put(0, new ImageIcon("Images/Player-0.png", "Player - 0"));
        result.put(10, new ImageIcon("Images/Player-10.png", "Player - 10"));
        result.put(20, new ImageIcon("Images/Player-20.png", "Player - 20"));
        result.put(30, new ImageIcon("Images/Player-30.png", "Player - 30"));
        result.put(40, new ImageIcon("Images/Player-40.png", "Player - 40"));
        result.put(50, new ImageIcon("Images/Player-50.png", "Player - 50"));
        result.put(60, new ImageIcon("Images/Player-60.png", "Player - 60"));
        result.put(70, new ImageIcon("Images/Player-70.png", "Player - 70"));
        result.put(80, new ImageIcon("Images/Player-80.png", "Player - 80"));
        result.put(90, new ImageIcon("Images/Player-90.png", "Player - 90"));
        result.put(100, new ImageIcon("Images/Player-100.png", "Player - 100"));
        return Collections.unmodifiableMap(result);
    }

    /**
     * Designated initializer for the Player. Defaults to 0, 0 position because the level will move it around.
     * @param iGame The Game instance being played in.
     */
    public Player(Game iGame) {
        super(new Point(0, 0));
        game = iGame;
        reset();
    }

    /**
     * Returns the player to perfect health and no valuables... such is life.
     */
    public void reset() {
        health = 100;
        score = 0;
    }

    /**
     * Contrary to other Tiles, the Player's image changes based on their health.
     * Round off their health to multiples of 10.
     * @return An image for drawing the player.
     */
    public ImageIcon getImageIcon() {
        int roundedHealth = (int) (Math.round(health / 10.0) * 10);
        return imageIcons.get(roundedHealth);
    }

    /**
     * Getter for the player's health points.
     * @return An integer, 0-100, representing the player's current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for the player's health points.
     * @param newHealth An integer, 0-100, representing the player's health.
     */
    public void setHealth(int newHealth) {
        health = Math.min(100, Math.max(0, newHealth));
        game.playerHealthDidChange();
    }

    /**
     * Getter for the player's score.
     * @return Any integer representing the player's current net worth.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for the player's score.
     * @param newScore A new value for the player's current net worth.
     */
    public void setScore(int newScore) {
        score = newScore;
        game.playerScoreDidChange();
    }

    /**
     * Moves the player on the game board - triggers Game to redraw.
     * @param newPosition A new row/column Point (not pixels) position for the player.
     */
    public void setPosition(Point newPosition) {
        super.setPosition(newPosition);
        game.playerPositionDidChange();
    }

    /**
     * As a Player can never enter a Player, this method does nothing.
     * @param p Hopefully, "this".
     * @param l The Level being played.
     */
    public void playerDidEnter(Player p, Level l) {}

} // end class Player