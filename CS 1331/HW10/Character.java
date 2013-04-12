import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the abstract interface for a moving person in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
abstract class Character extends Tile {

    /**
     * Designated initializer for a Character.
     * @param iPosition The starting position for the initialized Character.
     */
    Character(Point iPosition) {
        super(iPosition);
    }

    /**
     * Implements the basic isSolid method of Tile - Characters can collide with other characters.
     * @return Always false.
     */
    public boolean isSolid() {
        return false;
    }

} // end class Character