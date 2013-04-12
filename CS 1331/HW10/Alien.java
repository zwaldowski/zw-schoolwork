import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the basic form of monster in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Alien extends Monster {

    /**
     * A sprite representing the Alient.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Alien.png", "Alien");

    /**
     * Possible directions for the Alien to move in.
     */
    public enum Path {
        /**
         * The default path for a monster. The current position will always equal the starting position.
         */
        PATH_NONE,
        /**
         * The monster will move up the Y axis until it hits a solid object, then moves down.
         */
        PATH_UP_DOWN,
        /**
         * The monster will move left along the X axis until it hits a solid object, them moves right.
         */
        PATH_LEFT_RIGHT,
        /**
         * The monster moves up along the Y axis and right along the X axis until it hits a solid object, then moves
         * down and to the left.
         */
        PATH_NORTHEAST_SOUTHWEST,
        /**
         * The monster moves up along the Y axis and left along the X axis until it hits a solid object, then moves
         * down and to the right.
         */
        PATH_NORTHWEST_SOUTHEAST
    }

    /**
     * The path the current alien moves in.
     */
    private final Path path;

    /**
     * If true, the Alien moves along the starting axis, i.e., up for PATH_UP_DOWN.
     */
    private boolean direction;

    /**
     * Designated initializer for an Alien.
     * @param iPosition The receiving Alien's current and initial position.
     * @param iDamageValue The value by which the player's health is decremented upon collision.
     * @param iPath The direction this Alien should move in.
     */
    Alien(Point iPosition, int iDamageValue, Path iPath) {
        super(iPosition, iDamageValue);
        path = iPath;
    }

    /**
     * Initializer for Alien compatible with the superclass.
     * @param iPosition The receiving Alien's current and initial position.
     * @param iPath The direction this Alien should move in.
     */
    public Alien(Point iPosition, Path iPath) {
        this(iPosition, 0, iPath);
    }

    /**
     * Initializer for Alien compatible with the superclass.
     * @param iPosition The receiving Alien's current and initial position.
     */
    public Alien(Point iPosition) {
        this(iPosition, 0, Path.PATH_NONE);
    }

    /**
     * The sprite for an Alien, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Alien.
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Generates the next point the Alien should step in according to the current path.
     * @return A Point offset from the Alien's current position according to the Alien's current direction.
     */
    Point offsetPointForStep() {
        Point oldPosition = getPosition();
        Point newPosition = new Point(oldPosition.x, oldPosition.y);
        switch (path) {
            case PATH_UP_DOWN:
                newPosition.y += direction ? 1 : -1;
                break;
            case PATH_LEFT_RIGHT:
                newPosition.x += direction ? 1 : -1;
                break;
            case PATH_NORTHEAST_SOUTHWEST:
                newPosition.y += direction ? 1 : -1;
                newPosition.x += direction ? 1 : -1;
                break;
            case PATH_NORTHWEST_SOUTHEAST:
                newPosition.y += direction ? -1 : 1;
                newPosition.x += direction ? 1 : -1;
                break;
            case PATH_NONE:
            default:
                break;
        }
        return newPosition;
    }

    /**
     * Move within the current level when the game's runloop timer fires.
     *
     * Advance in the direction of the current path, if possible, otherwise reverse direction and make another step.
     *
     * @param l The current level.
     */
    public void step(Level l) {
        Point newPosition = offsetPointForStep();
        if (newPosition.equals(getPosition())) return;

        if (l.characterCanMoveToPosition(newPosition)) {
            setPosition(newPosition);
        } else {
            reverseDirection();
            step(l);
        }
    }

    /**
     * Used by subclasses that override step to reverse direction upon, say, hitting a wall.
     */
    protected void reverseDirection() {
        direction = !direction;
    }

} // end class Alien