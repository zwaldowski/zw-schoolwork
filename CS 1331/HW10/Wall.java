import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a basic tile that has no operation but preventing the user from entering. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Wall extends Tile {

    /**
     * A sprite representing the Wall tile.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Wall.png", "Wall");

    /**
     * The designated initializer for Wall tile.
     * @param iPosition The receiving Wall tile's current and initial position.
     */
    public Wall(Point iPosition) {
        super(iPosition);
    }

    /**
     * The sprite for a Wall tile, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Wall tile.
     */
    ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Whether the player is stopped by the object.
     * The player cannot walk on a wall (parkour was outside the scope of the assignment).
     * @return Always true.
     */
    boolean isSolid() {
        return true;
    }

    /**
     * As a Player can never enter a Wall, this method does nothing.
     * @param p Hopefully, "this".
     * @param l The Level being played.
     */
    void playerDidEnter(Player p, Level l) {}

} // end class Wall