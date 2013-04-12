import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the abstract interface for a collectible object in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
abstract class Item extends Tile {

    /**
     * Designated initializer for an Item.
     * @param iPosition The starting position for the initialized Item.
     */
    Item(Point iPosition) {
        super(iPosition);
    }

    /**
     * Implements the basic isSolid method of Tile - Items can collide with other characters.
     * @return Always false.
     */
    public boolean isSolid() {
        return false;
    }

    /**
     * Action called when the player enters the same space as this object.
     * Removes the receiving collectible Item from the game board.
     * @param p The current Player.
     * @param l The containing Level.
     */
    void playerDidEnter(Player p, Level l) {
        l.playerDidPickUpItem(this);
    }

} // end class Item