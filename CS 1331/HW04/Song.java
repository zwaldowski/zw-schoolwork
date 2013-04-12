import java.util.*;

//
// Homework 4, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class encapsulates a single track on an album in a music collection. (HW4, Problem 1)
 *
 * @author Zachary Waldowski
 * @version 0.1-fasterthanitunes
 */
public class Song {
	private String title;
	private String artist;
	private String genre;
	
	/**
	 * The designated initializer for the Song class.
	 *
	 * @param title The name of the song, i.e., "Gangnam Style".
	 * @param artist The name of the song's writer, i.e., "PSY".
	 * @param genre The type of song, i.e., "K-pop".
	 */
	public Song(String title, String artist, String genre) {
		setTitle(title);
		setArtist(artist);
		setGenre(genre);
	}

	/**
	 * A secondary initializer for the Song class.
	 * Genre will be initialized to "Unknown".
	 *
	 * @param title The name of the song, i.e., "15 Step".
	 * @param artist The name of the song's writer, i.e., "Radiohead".
	 */
	public Song(String title, String artist) {
		this(title, artist, "Unknown");
	}

	/**
	 * A getter for the name of the song.
	 * @return The title of the receiving song.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * A setter for the name of the song.
	 * @param title A new name for the song.
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	/**
	 * A getter for the artist of the song.
	 * @return The artist of the receiving song.
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * A setter for the artist of the song.
	 * @param title A new artist for the song.
	 */
	public void setArtist(String newArtist) {
		artist = newArtist;
	}

	/**
	 * A getter for the genre of the song.
	 * @return The genre of the receiving song.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * A setter for the genre of the song.
	 * @param title A new genre for the song.
	 */
	public void setGenre(String newGenre) {
		genre = newGenre;
	}

	/**
	 * Concatenates the properties of a song.
	 * @return A description including the receiving song's title, artist, and genre.
	 */
	public String toString() {
		return getTitle() + " - " + getArtist() + " - " + getGenre();
	}
}