import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a basic tile that has no operation. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
class Floor extends Tile {

    /**
     * An image representing the Floor.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Floor.png", "Floor");

    /**
     * The designated initializer for Floor.
     * @param iPosition The receiving Floor tile's current and initial position.
     */
    public Floor(Point iPosition) {
        super(iPosition);
    }

    /**
     * The sprite for a floor, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a floor.
     */
    ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Whether the player is stopped by the object. The player can walk on the floor unmolested.
     * @return Always false.
     */
    boolean isSolid() {
        return false;
    }

    /**
     * Method called when the player walks over the floor.
     * In this implementation, nothing happens.
     * That would be cruel.
     * @param p The current Player.
     * @param l The containing Level.
     */
    void playerDidEnter(Player p, Level l) {}

} // end class Floor