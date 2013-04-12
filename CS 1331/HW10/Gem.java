import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the interface for the basic pickup item in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Gem extends Item {

    /**
     * A sprite representing the Gem.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Gem.png", "Gem");

    /**
     * A value that increments the player's score.
     */
    private final int scoreValue;

    /**
     * The designated initializer for Gem.
     * @param iPosition The receiving Gem tile's current and initial position.
     * @param iValue The receiving Gem tile's value that increments to the player's score.
     */
    public Gem(Point iPosition, int iValue) {
        super(iPosition);
        scoreValue = iValue;
    }

    /**
     * Initializer for Gem compatible with the superclass' interface.
     * @param iPosition The receiving Gem tile's current and initial position.
     */
    public Gem(Point iPosition) {
        this(iPosition, 5);
    }

    /**
     * The sprite for a Gem, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Gem.
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Action called when the player enters the same space as this object.
     *
     * Removes the receiving collectible Item from the game board.
     *
     * Increments the player's score by the designated amount.
     *
     * @param p The current Player.
     * @param l The containing Level.
     */
    void playerDidEnter(Player p, Level l) {
        super.playerDidEnter(p, l);
        p.setScore(p.getScore() + scoreValue);

    }

} // end class Gem