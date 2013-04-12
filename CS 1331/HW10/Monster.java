import java.awt.*;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the abstract interface for a harmful NPC in the game. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public abstract class Monster extends Character {

    /**
     * The default value to damage the player with.
     */
    protected final int kDefaultMonsterDamageValue = 5;

    /**
     * A value that decrements the player's health.
     */
    private final int damageValue;

    /**
     * The designated initializer for a Monster.
     * @param iPosition The receiving Monster's current and initial position.
     * @param iDamageValue The value by which the player's health is decremented upon collision.
     */
    public Monster(Point iPosition, int iDamageValue) {
        super(iPosition);
        damageValue = iDamageValue == 0 ? kDefaultMonsterDamageValue : iDamageValue;
    }

    /**
     * The simplified initializer for a Monster compatible with the superclass.
     * @param iPosition The receiving Monster's current and initial position.
     */
    public Monster(Point iPosition) {
        this(iPosition, 0);
    }

    /**
     * Action called when the player enters the same space as this object.
     *
     * Decrements the player's health by the designated value.
     *
     * @param p The current Player.
     * @param l The containing Level.
     */
    public void playerDidEnter(Player p, Level l) {
        p.setHealth(p.getHealth() - damageValue);
    }

    /**
     * Abstract method: move within the current level when the game's runloop timer fires.
     * @param l The current level.
     */
    public abstract void step(Level l);

} // end class Monster