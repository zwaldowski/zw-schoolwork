import javax.swing.*;
import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the abstract interface for the basic view of the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public abstract class Tile {

    /**
     * The width of a tile. All of ours are the same as our sprites - 24 pixels.
     */
    private static final int width = 24;

    /**
     * The height of a tile. All of ours are the same as our sprites - 24 pixels.
     */
    private static final int height = 24;

    /**
     * The initial position a tile (for resetting).
     */
    private final Point initialPosition;

    /**
     * The current position a tile.
     */
    private Point position;

    /**
     * Designated initializer for a tile.
     * @param iPosition The receiving tile's current and initial position.
     */
    public Tile(Point iPosition) {
        initialPosition = iPosition;
        position = iPosition;
    }

    /**
     * Setter for the current tile position.
     * @param newPosition A new location in columns/rows (not pixels) for the tile.
     */
    void setPosition(Point newPosition) {
        position = newPosition;
    }

    /**
     * Getter for the current tile position.
     * @return The location in columns/rows (not pixels) for the tile.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Read-only getter for the pixel width of all tiles.
     * @return Width of the tile in pixels. Only affects visual display.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Read-only getter for the pixel height of all tiles.
     * @return Height of the tile in pixels. Only affects visual display.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Abstract method: a read-only getter for the sprite representing this tile.
     * @return An image icon.
     */
    abstract ImageIcon getImageIcon();

    /**
     * Default implementation. Draws the image icon.
     * @param g A graphics context.
     */
    void draw(Graphics g) {
        getImageIcon().paintIcon(null, g, Tile.getWidth() * position.x, Tile.getHeight() * position.y);
    }

    /**
     * Returns the tile to its initial position.
     */
    public void reset() {
        position = initialPosition;
    }

    /**
     * Abstract method: determine whether a player can't touch this (apologies to MC Hammer).
     * @return true if the player should be stopped, false if playerDidEnter should be called.
     */
    abstract boolean isSolid();

    /**
     * Abstract method: an action called when the player enters the same space as this object.
     * If isSolid returns true, then this method's implementation should be empty.
     * @param p The current Player.
     * @param l The containing Level.
     */
    abstract void playerDidEnter(Player p, Level l);

} // end class Tile