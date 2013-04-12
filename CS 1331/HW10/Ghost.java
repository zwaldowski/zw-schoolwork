import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a derivative form of monster that ignores walls. (HW10)
 *
 * Ghosts are one of this assignments' three special features.
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Ghost extends Alien {

    /**
     * A sprite representing the Ghost.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Ghost.png", "Ghost");

    /**
     * The designated initializer for a Ghost.
     * @param iPosition The receiving Ghost's current and initial position.
     * @param iDamageValue The value by which the player's health is decremented upon collision.
     * @param iPath The direction this Alien should move in.
     */
    public Ghost(Point iPosition, int iDamageValue, Path iPath) {
        super(iPosition, iDamageValue == 0 ? 10 : iDamageValue, iPath);
    }

    /**
     * Initializer for Ghost compatible with the superclass.
     * @param iPosition The receiving Ghost's current and initial position.
     * @param iPath The direction this Alien should move in.
     */
    public Ghost(Point iPosition, Path iPath) {
        this(iPosition, 0, iPath);
    }

    /**
     * Initializer for Ghost compatible with the superclass.
     * @param iPosition The receiving Alien's current and initial position.
     */
    public Ghost(Point iPosition) {
        this(iPosition, 0, Path.PATH_NONE);
    }

    /**
     * The sprite for a Ghost, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Ghost.
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Move within the current level when the game's runloop timer fires.
     *
     * Unlike the superclass, Ghosts are only subject to the new point being legal.
     * @param l The current level.
     */
    public void step(Level l) {
        Point newPosition = offsetPointForStep();
        if (newPosition.equals(getPosition())) return;

        if (l.pointIsLegal(newPosition)) {
            setPosition(newPosition);
        } else {
            reverseDirection();
            step(l);
        }
    }

}
