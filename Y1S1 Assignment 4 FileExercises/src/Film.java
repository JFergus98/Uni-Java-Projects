// Import Scanner class
import java.util.Scanner;

/**
 * Creates and holds details of a film
 * 
 * @author Jamie Fergus
 * @version 1.0 
 */
public class Film {
	
	/**
	 * Declare fields for Film class
	 */
	private String title;
	private String director;
	private int runningTime;
	private float purchaseCost;
	
	// Main method
	public static void main(String[] args) {
		// Create instance of Film class
		Film testFilm = new Film();
		
		// Calls test method
		testFilm.runFilmTest();
	}
	
	/**
	 * Method that tests the Film class
	 */
	public void runFilmTest() {
		// Display default values
		displayFilmDetails();
		// Call set method to set values of testFilm
		setAll();	
		// display testFilm
		displayFilmDetails();
	}

	/**
	 * Get method for title
	 * 
	 * @return title of film
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set method for title
	 * 
	 * @param title of film
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get method for director
	 * 
	 * @return director of film
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Set method for director
	 * 
	 * @param director of film
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get method for runningTime
	 * 
	 * @return runningTime of film
	 */
	public int getRunningTime() {
		return runningTime;
	}

	/**
	 * Set method for runningTime
	 * 
	 * @param runningTime of film
	 */
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	/**
	 * // Get method for purchaseCost
	 * 
	 * @return purchaseCost of film
	 */
	public float getPurchaseCost() {
		return purchaseCost;
	}

	/**
	 * Set method for purchaseCost
	 * 
	 * @param purchaseCost of film
	 */
	public void setPurchaseCost(float purchaseCost) {
		this.purchaseCost = purchaseCost;
	}
	
	/**
	 * Default constructor initialising fields to their default value
	 */
	public Film()
	{
		title = "default title";
		director = "default director";
		runningTime = 0;
		purchaseCost = 0;
	}
	
	/**
	 * Method to display film details
	 */
	public void displayFilmDetails() {
		System.out.println("Title : " + title);
		System.out.println("The director of " + title + " is " + director + ".");
		System.out.println("The run time of the film is " + runningTime + " minutes.");
		System.out.println(title + " costs £ " + purchaseCost + " to buy.");
		System.out.println("");
	}
	
	/**
	 * Method to set all the film details from the user
	 */
	public void setAll() {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the title of the film");
		setTitle(s.nextLine());
		System.out.println("Please enter the director of the film");
		setDirector(s.nextLine());
		System.out.println("Please enter the run time of the film (in minutes)");
		setRunningTime(s.nextInt());
		System.out.println("Please enter the cost the film");
		setPurchaseCost(s.nextFloat());
	}
	
}
