import java.util.*;

//
// Homework 4, Problem 2
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates an album in a music collection. (HW4, Problem 2)
 *
 * @author Zachary Waldowski
 * @version 0.1-fasterthanitunes
 */
public class Album {
	private String title;
	private String artist;
	private String genre;
	private Song favoriteTrack;
	private int favoriteTrackNumber;
	private static int numAlbums;

	/**
	 * The designated initializer for the Album class.
	 *
	 * @param title The name of the album, i.e., "Demon Days".
	 * @param favoriteTrack An instance of Song representing the favorite track.
	 * @param favoriteTrackNumber The track number of the favorite track.
	 */
	public Album(String title, Song favoriteTrack, int favoriteTrackNumber) {
		setTitle(title);
		setFavoriteTrack(favoriteTrack);
		setFavoriteTrackNumber(favoriteTrackNumber);
		numAlbums++;
	}

	/**
	 * A secondary initializer for the Album class.
	 * Favorite track number will be initialized to 1.
	 *
	 * @param title The name of the album, i.e., "Mylo Xyloto".
	 * @param favoriteTrack An instance of Song representing the favorite track.
	 */
	public Album(String title, Song favoriteTrack) {
		this(title, favoriteTrack, 1);
	}

	/**
	 * A getter for the name of the album.
	 * @return The title of the receiving album.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * A setter for the name of the album.
	 * @param title A new name for the album.
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	/**
	 * A getter for the artist of the album.
	 * @return The artist of the receiving album.
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * A setter for the artist of the album.
	 * @param title A new artist for the album.
	 */
	public void setArtist(String newArtist) {
		artist = newArtist;
	}

	/**
	 * A getter for the genre of the album.
	 * @return The genre of the receiving album.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * A setter for the genre of the album.
	 * @param title A new genre for the album.
	 */
	public void setGenre(String newGenre) {
		genre = newGenre;
		if (favoriteTrack != null)
			favoriteTrack.setGenre(genre);
	}

	/**
	 * A getter for the favorite track of the album.
	 * @return The favorite track of the receiving album.
	 */
	public Song getFavoriteTrack() {
		return favoriteTrack;
	}

	/**
	 * A setter for the favorite track of the album.
	 * @param title A new favorite track for the album.
	 */
	public void setFavoriteTrack(Song newFavoriteTrack) {
		favoriteTrack = newFavoriteTrack;
		setArtist(favoriteTrack.getArtist());
		setGenre(favoriteTrack.getGenre());
	}

	/**
	 * A getter for the number of the favorite track on the album.
	 * @return The favorite track number of the receiving album.
	 */
	public int getFavoriteTrackNumber() {
		return favoriteTrackNumber;
	}

	/**
	 * A setter for the number of the favorite track on the album.
	 * @param title A new number of the favorite track for the album.
	 */
	public void setFavoriteTrackNumber(int newFavoriteTrackNumber) {
		favoriteTrackNumber = newFavoriteTrackNumber;
	}

	/**
	 * Concatenates the properties of an album.
	 * @return A description including the receiving albums's title, artist, and genre.
	 */
	public String toString() {
		return getTitle() + " - " + getArtist() + " - " + getGenre();
	}
}