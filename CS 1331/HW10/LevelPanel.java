import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class draws the contents of a Level in a visual space. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class LevelPanel extends JPanel {

    /**
     * Stores the game board object the level is being played in.
     */
    private final Game game;

    /**
     * Private listener used to move the player around based on keyboard input.
     */
    private class LevelPanelKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            Point oldPoint = game.getPlayer().getPosition();
            Point newPoint = new Point(oldPoint.x, oldPoint.y);
            int type = e.getKeyCode();
            switch (type) {
                case KeyEvent.VK_KP_UP:
                case KeyEvent.VK_UP:
                    newPoint.y--;
                    break;
                case KeyEvent.VK_KP_LEFT:
                case KeyEvent.VK_LEFT:
                    newPoint.x--;
                    break;
                case KeyEvent.VK_KP_RIGHT:
                case KeyEvent.VK_RIGHT:
                    newPoint.x++;
                    break;
                case KeyEvent.VK_KP_DOWN:
                case KeyEvent.VK_DOWN:
                    newPoint.y++;
                    break;
                default:
                    break;
            }
            game.getLevel().setPlayerPosition(newPoint);
        }
    }

    /**
     * Designated initializer for the level panel.
     * @param theGame The game board controlling the application.
     */
    public LevelPanel(Game theGame) {
        super();

        game = theGame;

        setFocusable(true);
        addKeyListener(new LevelPanelKeyListener());
    }

    /**
     * Basic drawing implementation for the game.
     *
     * Fills the background (white) and calls Level's drawing method.
     *
     * @param g A Graphics context.
     */
    protected void paintComponent(Graphics g) {
        Rectangle size = g.getClipBounds();
        g.setColor(Color.white);
        g.fillRect(size.x, size.y, size.width, size.height);
        game.getLevel().draw(g);
    }

} // end class LevelPanel