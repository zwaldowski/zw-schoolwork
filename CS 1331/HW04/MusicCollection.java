import java.util.*;

//
// Homework 4, Problem 3
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates a a music collection. (HW4, Problem 3)
 *
 * @author Zachary Waldowski
 * @version 0.1-fasterthanitunes
 */
public class MusicCollection {
	private static Scanner scanner;

	/**
	 * Performs user operations on an album.
	 */
	public static void albumOptions(Album selectedAlbum) {
		System.out.println();

		int selection = -1;
		while (selection != 0) {
			System.out.println("\"" + selectedAlbum.getTitle() + "\" by " + selectedAlbum.getArtist() + ": " + selectedAlbum.getGenre());
			System.out.println("Album options:");
			System.out.println("[1] Get favorite track");
			System.out.println("[2] Change genre");
			System.out.println("[0] Return to main menu");

			System.out.print("Enter an option. > ");
			selection = scanner.nextInt();

			if (selection == 1) {
				System.out.println();
				System.out.println("Track no. " + selectedAlbum.getFavoriteTrackNumber() + ": " + selectedAlbum.getFavoriteTrack().toString());
				System.out.println();
			} else if (selection == 2) {
				System.out.println();
				System.out.print("Enter the new genre. > ");
				selectedAlbum.setGenre(scanner.next());
				System.out.println();
			} else {
				System.out.println();
			}
		}
	}

	/**
	 * Main entry point for this program. Generates three albums and performs user operations on them.
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		Song firstSong = new Song("Lost Art Of Keeping A Secret", "Queens of the Stone Age");
		Album firstAlbum = new Album("Rated R", firstSong, 2);

		Song secondSong = new Song("Frank Sinatra", "CAKE");
		Album secondAlbum = new Album("Fashion Nugget", secondSong);

		Song thirdSong = new Song("There There", "Radiohead", "Alternative");
		Album thirdAlbum = new Album("Hail to the Thief", thirdSong, 9);

		int selection = -1;
		while (selection != 0) {
			System.out.println("[1] " + firstAlbum.getTitle());
			System.out.println("[2] " + secondAlbum.getTitle());
			System.out.println("[3] " + thirdAlbum.getTitle());
			System.out.print("Select an album (0 to quit). > ");

			selection = scanner.nextInt();

			if (selection > 0 && selection < 4) {
				Album selectedAlbum;
				if (selection == 1)
					selectedAlbum = firstAlbum;
				else if (selection == 2)
					selectedAlbum = secondAlbum;
				else
					selectedAlbum = thirdAlbum;

				albumOptions(selectedAlbum);
			}
		}
	}
}








