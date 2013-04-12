import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * This class implements the basic runloop, gatekeeper, and main entry point for the assignment. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Game {

    /**
     * Stores all level files loaded from the level directory.
     */
    private final File[] levelFiles;

    /**
     * Stores the current level.
     */
    private Level currentLevel;

    /**
     * Stores the index of the current level within the levelFiles array.
     */
    private int currentLevelIndex;

    /**
     * Stores the main run loop timer. Used to time movement of NPCs within the game.
     */
    private final javax.swing.Timer runLoopTimer;

    /**
     * The main player, i.e., the user.
     */
    private final Player player;

    /**
     * Stores the main frame (window) of the running game. Used purely to manipulate its size.
     */
    private JFrame frame;

    /**
     * A reference to the label displaying number of items left.
     */
    private JLabel statusLabel;

    /**
     * A reference to the label displaying points collected.
     */
    private JLabel scoreLabel;

    /**
     * A reference to the label displaying the player's remaining health.
     */
    private JLabel healthLabel;

    /**
     * A reference to the main panel used to render the current level.
     */
    private LevelPanel levelPanel;

    /**
     * Internal class: implements the run loop as invoked by the timer.
     * Causes the internal state of the level to advance one step, and causes the level to redraw.
     */
    private class RunLoopTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentLevel.step();
            levelPanel.repaint();
        }
    } // end private class RunLoopTimerListener

    /**
     * Internal class: Gets the levels from the Level directory by file extension.
     */
    private class LevelFilenameFilter implements FilenameFilter {
        public boolean accept(File dir, String filename) {
            return filename.endsWith(".lvl");
        }
    } // end private class LevelFilenameFilter

    /**
     * Internal class: Sorts filenames in a collection by filename.
     */
    private class FileNameComparator implements Comparator<File> {
        public int compare(File o1, File o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    } // end private class FileNameComparator

    /**
     * Designated initializer for Game.
     *
     * Sets up initial state, loads levels, sets up first level, and initializes run loop timer.
     */
    private Game() {
        player = new Player(this);

        levelFiles = new File("levels").listFiles(new LevelFilenameFilter());

        if ((levelFiles == null) || (levelFiles.length == 0)) {
            JOptionPane.showMessageDialog(null, "Level files were missing. The game can't be played.", "Oh No!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        Arrays.sort(levelFiles, new FileNameComparator());

        currentLevelIndex = 0;

        try {
            currentLevel = new Level(this, levelFiles[currentLevelIndex]);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Some files couldn't be read. The game can't be played.", "Oh No!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        runLoopTimer = new javax.swing.Timer(300, new RunLoopTimerListener());
    }

    /**
     * Internal, read-only getter for the current player of the receiving Game.
     * @return The Player instance for the current player.
     */
    Player getPlayer() {
        return player;
    }

    /**
     * Internal, read-only getter for the current level of the receiving Game.
     * @return The Level instance for the current level.
     */
    Level getLevel() {
        return currentLevel;
    }

    /**
     * Private method: redraw the level panel, safely (if we have one).
     */
    private void redraw() {
        if (levelPanel != null) {
            levelPanel.repaint();
        }
    }

    /**
     * Private method: reset UI when the level changes.
     */
    private void levelDidChange() {
        int tileWidth = Tile.getWidth();
        int tileHeight = Tile.getHeight();
        int numberRows = currentLevel.getRows();
        int numberCols = currentLevel.getColumns();
        int windowWidth = tileWidth * numberCols;
        int windowHeight = (tileHeight * (numberRows + 2));

        levelPanel.setSize(windowWidth, windowHeight);
        frame.setSize(windowWidth, windowHeight);
        numberOfItemsDidChange();
        playerScoreDidChange();
        runLoopTimer.start();
        redraw();
    }

    /**
     * Internal method for Level to call upon the collection of an item.
     * The level enters win state if no items are left.
     */
    void numberOfItemsDidChange() {
        redraw();
        statusLabel.setText("Items: " + currentLevel.getNumberOfItems());
        if (currentLevel.getNumberOfItems() == 0) win();
    }

    /**
     * Internal method for Player to call upon movement.
     */
    void playerPositionDidChange() {
        redraw();
    }

    /**
     * Internal method for Player to call when objects collide with it.
     * The level enters lose state if no health is left.
     */
    void playerHealthDidChange() {
        healthLabel.setText("Health: " + player.getHealth() + "%");
        if (player.getHealth() == 0)
            lose();
    }

    /**
     * Internal method for Level to call when the player's score changes.
     * Note that this could technically be in numberOfItemsDidChange, but for game design purposes
     * score might not always be related to the number of items picked up.
     */
    void playerScoreDidChange() {
        scoreLabel.setText("Score: " + player.getScore());
    }

    /**
     * Private method to reset the state of the level upon entering lose state.
     * Basically tells everything to start over, restarts the timer, and acts as if the level changed.
     */
    private void reloadCurrentLevel() {
        runLoopTimer.stop();
        currentLevel.reset();
        player.reset();
        levelDidChange();
    }

    /**
     * Private method called when the game enters lose state.
     * Asks the user whether they want to start over or quit the game.
     */
    private void lose() {
        runLoopTimer.stop();
        int result = JOptionPane.showConfirmDialog(levelPanel, "You died a horrible death. Would you like to try again?", "Oh No!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                reloadCurrentLevel();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Private method called to advance to the next level.
     * Acts similar to reloadCurrentLevel: sets up the level panel, restarts the timer.
     */
    private void nextLevel() {
        runLoopTimer.stop();
        currentLevelIndex++;
        try {
            currentLevel = new Level(this, levelFiles[currentLevelIndex]);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Some files couldn't be read. The game can't be played.", "Oh No!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        player.reset();
        levelDidChange();
    }

    /**
     * Private method called when the level enters win state.
     * If the game has more levels, prompts the user whether they want to continue to the next level.
     * If the game has no more levels, it congratulates the player then shows them the door.
     */
    private void win() {
        if (currentLevelIndex + 1 < levelFiles.length) {
            runLoopTimer.stop();
            int result = JOptionPane.showConfirmDialog(levelPanel, "You won. Next level?", "Hooray!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    nextLevel();
                    break;
                case JOptionPane.CLOSED_OPTION:
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                default:
                    break;
            }
        } else {
            int result = JOptionPane.showConfirmDialog(levelPanel, "You won, but there are no more levels. Would you like to quit?", "Hooray!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            levelPanel.setFocusable(false);
            runLoopTimer.stop();
        }
    }

    /**
     * Primary entry point for the assignment application.
     * Initializes the UI necessary for the game to run.
     * @param args Command-line arguments, as an array of strings.
     */
    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("Dungeon Crawlers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        game.frame = frame;

        game.statusLabel = new JLabel();
        game.scoreLabel = new JLabel("Score: 0");
        game.healthLabel = new JLabel("Health: 100%");

        JPanel statusPanel = new JPanel();
        statusPanel.add(game.statusLabel, BorderLayout.WEST);
        statusPanel.add(game.scoreLabel, BorderLayout.CENTER);
        statusPanel.add(game.healthLabel, BorderLayout.EAST);
        statusPanel.setSize(0, 24);

        game.levelPanel = new LevelPanel(game);

        frame.add(game.levelPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.pack();

        game.levelDidChange();

        frame.setVisible(true);
    }
}