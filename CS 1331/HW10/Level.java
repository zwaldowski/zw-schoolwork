import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//
// Homework 10
// Zachary Waldowski
// CS 1331
//

/**
 * The primary model object in the game, representing the state of a given level. (HW10)
 *
 * @author Zachary Waldowski
 * @version 0.10
 */
public class Level {

    /**
     * Private class used to represent the initial state of a level, as parsed from a file.
     */
    private static class LevelParseOutput {
        /**
         * The initial tiles (Walls, Tiles) on the game board.
         */
        final Tile[][] tiles;

        /**
         * The starting point of the player in game board units, (0,0) by default.
         */
        final Point playerPosition;

        /**
         * The initial collectible items (Gem, Heart) on the game board.
         */
        final ArrayList<Item> items;

        /**
         * The initial NPCs (Alien, Ghost) on the game board.
         */
        final ArrayList<Monster> monsters;

        /**
         * Designated initializer for a level parse output.
         * @param iTiles A two-dimensional array of tile objects.
         * @param iPlayerPosition The starting position of the player.
         * @param iMonsters An array list of NPCs.
         * @param iItems An array list of stationary (collectible) tiles.
         */
        LevelParseOutput(Tile[][] iTiles, Point iPlayerPosition, ArrayList<Monster> iMonsters, ArrayList<Item> iItems) {
            tiles = iTiles;
            playerPosition = iPlayerPosition;
            monsters = iMonsters;
            items = iItems;
        }
    }

    /**
     * Parses a level from a .lvl file.
     *
     * The level parser is entirely dynamic. The game loads what's in the Levels folder alphabetically.
     * The levels themselves are plaintext, are a grid of characters.
     * If the rows are uneven, then the extra spaces are filled in with floor tiles.
     *
     * The key for the parser is as follows:
     * - 'l': Lava tile
     * - 'h': Heart tile
     * - 'g': Gem tile
     * - 'x': Wall
     * - 'o': Plain floor tile
     * - 'p': Player (the final occurrence is treated as the starting position of the player)
     * - '|': Alien (SimpleMonster) moving up and down
     * - '-': Alien (SimpleMonster) moving left and right
     * - '/': Alien (SimpleMonster) moving diagonally up/right and down/left
     * - '\' (a backslash): Alien (SimpleMonster) moving diagonally up/left and down/right
     * - '_': Alien (SimpleMonster) with no movement
     * - ':': Ghost moving up and down
     * - '~': Ghost moving left and right
     * - '{': Ghost moving diagonally up/right and down/left
     * - '}': Ghost moving diagonally up/left and down/right
     * - '.': Ghost with no movement
     *
     * @param file A File from the Java file manager API, presumably Files/#.lvl
     * @return A parsed level state, ready to be used as the initial state of a Level object.
     * @throws IOException when the levels cannot be read. Exception left unchecked so that Game takes care of it.
     */
    private static LevelParseOutput parseLevel(File file) throws IOException {
        int maxRow = 0, maxCol = 0;

        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);

        ArrayList<ArrayList<Tile>> allRows = new ArrayList<ArrayList<Tile>>();
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Item> items = new ArrayList<Item>();
        Point playerStartingPoint = new Point(0, 0);

        String line = buffer.readLine();
        while (line != null) {
            ArrayList<Tile> thisRow = new ArrayList<Tile>();

            for (int i = 0; i < line.length(); i++) {
                char thisCode = line.charAt(i);
                Point currentPoint = new Point(i, maxRow);

                if (thisCode == 'l') { // lava
                    thisRow.add(new Lava(currentPoint));
                } else if (thisCode == 'h') { // heart
                    Heart newItem = new Heart(currentPoint);
                    items.add(newItem);
                    thisRow.add(newItem);
                } else if (thisCode == 'g') { // gem
                    Gem newItem = new Gem(currentPoint);
                    items.add(newItem);
                    thisRow.add(newItem);
                } else if (thisCode == 'x') { // wall
                    thisRow.add(new Wall(currentPoint));
                } else { // floor + others
                    thisRow.add(new Floor(currentPoint));

                    if (thisCode == 'p') { // player
                        playerStartingPoint = currentPoint;
                    } else if (thisCode == '|') { // alien up/down
                        monsters.add(new Alien(currentPoint, Alien.Path.PATH_UP_DOWN));
                    } else if (thisCode == '-') { // alien left/right
                        monsters.add(new Alien(currentPoint, Alien.Path.PATH_LEFT_RIGHT));
                    } else if (thisCode == '/') { // alien northeast/southwest
                        monsters.add(new Alien(currentPoint, Alien.Path.PATH_NORTHEAST_SOUTHWEST));
                    } else if (thisCode == '\\') { // alien northwest/southeast
                        monsters.add(new Alien(currentPoint, Alien.Path.PATH_NORTHWEST_SOUTHEAST));
                    } else if (thisCode == '_') { // alien sitting
                        monsters.add(new Alien(currentPoint, Alien.Path.PATH_NONE));
                    } else if (thisCode == ':') { // ghost up/down
                        monsters.add(new Ghost(currentPoint, Alien.Path.PATH_UP_DOWN));
                    } else if (thisCode == '~') { // ghost left/right
                        monsters.add(new Ghost(currentPoint, Alien.Path.PATH_LEFT_RIGHT));
                    } else if (thisCode == '{') { // ghost northeast/southwest
                        monsters.add(new Ghost(currentPoint, Alien.Path.PATH_NORTHEAST_SOUTHWEST));
                    } else if (thisCode == '}') { // ghost northwest/southeast
                        monsters.add(new Ghost(currentPoint, Alien.Path.PATH_NORTHWEST_SOUTHEAST));
                    } else if (thisCode == '.') { // ghost sitting
                        monsters.add(new Ghost(currentPoint, Alien.Path.PATH_NONE));
                    }
                }
            }

            allRows.add(thisRow);

            maxRow++;
            maxCol = Math.max(line.length(), maxCol);
            line = buffer.readLine();
        }

        reader.close();
        buffer.close();

        // convert tile arrays, padding if necessary
        Tile[][] tiles = new Tile[maxRow][maxCol];

        for (int i = 0; i < allRows.size(); i++) {
            ArrayList<Tile> row = allRows.get(i);

            while (row.size() < maxCol) {
                row.add(new Floor(new Point(row.size(), i)));
            }

            tiles[i] = row.toArray(tiles[i]);
        }

        return new LevelParseOutput(tiles, playerStartingPoint, monsters, items);
    }

    /**
     * The designated initializer for a Level.
     *
     * @param iGame The game board object the level is being played in.
     * @param file A .lvl plaintext level arrangement file.
     * @throws IOException if the file cannot be opened for some reason. Left uncaught so that Game handles it.
     */
    public Level(Game iGame, File file) throws IOException {
        this(iGame, parseLevel(file));
    }

    /**
     * Stores the game board object the level is being played in.
     */
    private final Game game;

    /**
     * The parsed level state upon initialization. Used for resetting.
     */
    private final LevelParseOutput initialState;

    /**
     * The horizontal size of the game board in columns (not x pixels).
     */
    private int width;

    /**
     * The vertical size of the game board in rows (not y pixels).
     */
    private int height;

    /**
     * The current tiles (Walls, Tiles) on the game board.
     */
    private Tile[][] tiles;

    /**
     * The current position of the player in game board units.
     */
    private Point playerPosition;

    /**
     * The collectible items (Gem, Heart) on the game board.
     */
    private ArrayList<Item> items;

    /**
     * The NPCs (Alien, Ghost) on the game board.
     */
    private ArrayList<Monster> monsters;

    /**
     * The private initializer for a parsed Level.
     *
     * @param iGame The game board object the level is being played in.
     * @param parsed The initial state of the level.
     */
    private Level(Game iGame, LevelParseOutput parsed) {
        game = iGame;
        initialState = parsed;
        reset();
    }

    /**
     * Restores the receiving Level to its initial state.
     *
     * Shallow copies all state from the given initial state, resets all objects, and moves the player.
     */
    public void reset() {
        tiles = new Tile[initialState.tiles.length][];
        for (int i = 0; i < initialState.tiles.length; i++) {
            Tile[] row = initialState.tiles[i];
            Tile[] newRow = new Tile[row.length];
            System.arraycopy(row, 0, newRow, 0, row.length);
            tiles[i] = newRow;
        }
        playerPosition = new Point(initialState.playerPosition.x, initialState.playerPosition.y);
        monsters = new ArrayList<Monster>(initialState.monsters);
        items = new ArrayList<Item>(initialState.items);

        height = tiles.length;
        width = tiles[0].length;

        game.getPlayer().setPosition(playerPosition);

        for (Tile[] row : tiles) {
            for (Tile col : row) {
                col.reset();
            }
        }
    }

    /**
     * A read-only getter for the vertical size of the game board.
     * @return The vertical size of the receiving Level in units (not y pixels).
     */
    public int getRows() {
        return height;
    }

    /**
     * A read-only getter for the horizontal size of the game board.
     * @return The horizontal size of the receiving Level in units (not x pixels).
     */
    public int getColumns() {
        return width;
    }

    /**
     * A read-only getter for the number of collectible items remaining on the board.
     * @return The number of collectible items remaining in the receiving Level.
     */
    public int getNumberOfItems() {
        return items.size();
    }

    /**
     * Used to determine whether a point is contained within the bounds of the receiving Level.
     * @param point A point, in row/column integer units and not pixels, to check for validity.
     * @return true if the point is within the rectangle created by (0,0) and (columns, rows), false otherwise.
     */
    public boolean pointIsLegal(Point point) {
        return !(point.x < 0 || point.x >= width || point.y < 0 || point.y >= height);
    }

    /**
     * Used to determine whether a character subject to the bounds of this plane of existence
     * (i.e., Player, Alien) can move to a point.
     * @param point A point, in row/column integer units and not pixels, to check for validity.
     * @return true if the point is legal and no solid object exists at that point, false otherwise.
     */
    public boolean characterCanMoveToPosition(Point point) {
        if (!pointIsLegal(point)) return false;
        Tile thisTile = tiles[point.y][point.x];
        return !thisTile.isSolid();
    }

    /**
     * Internal method used by the delegating Level Panel to set the position of the player based on input.
     * @param newPoint A point, in row/column integer units and not pixels, to move the player to.
     */
    protected void setPlayerPosition(Point newPoint) {
        if (newPoint.equals(playerPosition)) return;
        if (!characterCanMoveToPosition(newPoint)) return;
        playerPosition = newPoint;
        Player player = game.getPlayer();
        player.setPosition(newPoint);
        checkForCollisions();
    }

    /**
     * Internal method to connect the run loop of the game to the run loop of monsters.
     */
    protected void step() {
        for (Monster monster : monsters) {
            monster.step(this);
        }
        checkForCollisions();
    }

    /**
     * Internal method to check if the player is inside the same coordinates as any NPC or collectible.
     */
    protected void checkForCollisions() {
        Player player = game.getPlayer();
        tiles[playerPosition.y][playerPosition.x].playerDidEnter(player, this);
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(playerPosition)) {
                monster.playerDidEnter(player, this);
            }
        }
    }

    /**
     * Internal method called when a collectible item responds to playerDidEnter.
     * @param i The sending tile.
     */
    protected void playerDidPickUpItem(Tile i) {
        Point positionToReplace = i.getPosition();
        tiles[playerPosition.y][playerPosition.x] = new Floor(positionToReplace);
        items.remove(i);
        game.numberOfItemsDidChange();
    }

    /**
     * The primary drawing interface for a level. Draws all tiles (non-moving), monsters (moving), and the player.
     * @param g A graphics context.
     */
    public void draw(Graphics g) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.draw(g);
            }
        }

        for (Monster monster : monsters) {
            monster.draw(g);
        }

        game.getPlayer().draw(g);
    }
}