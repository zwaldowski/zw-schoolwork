//
// Homework 10
// Zachary Waldowski
// CS 1331
//

The attached zip file needs to be unzipped so that the .class files are in the same folder as the Images and Levels directories.

My implementation of Homework 10 ("Dungeon Crawlers") includes the following extra features:

 - "Lava" tiles. These immediately kill the player.
 - "Heart" tiles. These restore 15 health points to the player.
 - "Ghost" monsters. These beasties only hit the bounds of the game board.

The level parser is entirely dynamic. The game loads what's in the Levels folder alphabetically. The levels themselves are plaintext, are a grid of characters. If the rows are uneven, then the extra spaces are filled in with floor tiles.

The key for the parser is as follows:
 - 'l': Lava tile
 - 'h': Heart tile
 - 'g': Gem tile
 - 'x': Wall
 - 'o': Plain floor tile
 - 'p': Player (the final occurrence is treated as the starting position of the player)
 - '|': Alien (SimpleMonster) moving up and down
 - '-': Alien (SimpleMonster) moving left and right
 - '/': Alien (SimpleMonster) moving diagonally up/right and down/left
 - '\' (a backslash): Alien (SimpleMonster) moving diagonally up/left and down/right
 - '_': Alien (SimpleMonster) with no movement
 - ':': Ghost moving up and down
 - '~': Ghost moving left and right
 - '{': Ghost moving diagonally up/right and down/left
 - '}': Ghost moving diagonally up/left and down/right
 - '.': Ghost with no movement

Any unrecognized character is treated like a plain floor tile. Any tile whose contents are movable are marked as a floor tile on the board and move independently; this includes the player and any monsters. Pickup items (gems, hearts) are replaced with a floor tile when they are removed from the board.