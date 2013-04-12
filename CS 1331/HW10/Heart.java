import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a pickup item that improves player health. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Heart extends Item {

    /**
     * A sprite representing the Heart.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Heart.png", "Heart");

    /**
     * A value that increments the player's helath.
     */
    private final int healthValue;

    /**
     * The designated initializer for Heart.
     * @param iPosition The receiving Heart tile's current and initial position.
     * @param iValue The receiving Heart tile's value that increments to the player's health.
     */
    public Heart(Point iPosition, int iValue) {
        super(iPosition);
        healthValue = iValue;
    }

    /**
     * Initializer for Heart compatible with the superclass' interface.
     * @param iPosition The receiving Heart tile's current and initial position.
     */
    public Heart(Point iPosition) {
        this(iPosition, 15);
    }

    /**
     * The sprite for a Heart, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Heart.
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Action called when the player enters the same space as this object.
     *
     * Removes the receiving collectible Item from the game board.
     *
     * Increments the player's health by the designated amount.
     *
     * @param p The current Player.
     * @param l The containing Level.
     */
    void playerDidEnter(Player p, Level l) {
        super.playerDidEnter(p, l);
        p.setHealth(p.getHealth() + healthValue);
    }

} // end class Heart