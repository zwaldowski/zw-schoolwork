import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements a basic tile that kills the player. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Lava extends Tile {

    /**
     * A sprite representing the Lava tile.
     */
    private static final ImageIcon imageIcon = new ImageIcon("Images/Lava.png", "Lava");

    /**
     * The designated initializer for Lava tile.
     * @param iPosition The receiving Lava tile's current and initial position.
     */
    public Lava(Point iPosition) {
        super(iPosition);
    }

    /**
     * The sprite for a Lava tile, as determined by the local, singleton imageIcon instance.
     * @return The sprite for a Lava tile.
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Whether the player is stopped by the object.
     * The player can walk on the lava tile, as long as they like the smell of burnt flesh.
     * @return Always false.
     */
    boolean isSolid() {
        return false;
    }

    /**
     * Method called when the player walks over the lava.
     * In this implementation, the player dies instantly.
     * That is, in fact, rather cruel.
     * @param p The current Player.
     * @param l The containing Level.
     */
    void playerDidEnter(Player p, Level l) {
        p.setHealth(0);
    }

} // end class Lava