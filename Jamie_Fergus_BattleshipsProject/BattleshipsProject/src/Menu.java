// Import io classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Import Scanner class
import java.util.Scanner;

/**
 * This class acts as the main menu for the game allowing the user to select different options to start the game, load game and view rules and info
 * 
 * @author Jamie Fergus
 */
public class Menu {
	private GameInterface game;

	/**
	 * Main method that creates an instance of the menu class
	 * 
	 * @param args - string
	 */
	public static void main(String[] args) {
		// Create a instance of your Menu class
		Menu menu = new Menu();
		// Call the processUserChoices() method
		menu.processUserChoices();
	}
	
	/**
	 * A method that processes the main menu choices entered by the user.
	 */
	public void processUserChoices() {
		// A local variable to hold a choice entered by the user
		int c = 0;
		
		// Allows the program to keep running until the exit option is selected
		do {
			// Call the method to display choices to the user
			displayMenu();
			
			// Call method to check user input is an integer
			c = checkInt("Please select one of the options above, by entering one of the numbers listed.");
		
			// call the method selected by the users choice
			switch (c)
			{
				case 1:
					// Call chooseGameMode() method
					chooseGameMode();
					break;
				case 2:
					// call loadGame() method
					game.loadGame();
					break;
				case 3:
					// call rulesAndInfo() method
					rulesAndInfo();
					break;
				case 0:
					break;
		 		default:
		 			// display error message
		 			System.out.println("Sorry, your choice wasn’t recognised, please try again.");
		 			break;
		 	}
		}
		// end loop
		while(c != 0); 
	} 
	
	/**
	 * A method that processes the choice of game modes entered by the user.
	 */
	public void chooseGameMode() {
		// A local variable to hold a choice entered by the user
		int c = 0;
				
		// Allows the program to keep running until the exit option is selected
		do {
			// Call the method to display choices to the user
			displayGameModes();
					
			// Call method to check user input is an integer
			c = checkInt("Please select one of the options above, by entering one of the numbers listed.");
				
			// call the method selected by the users choice
			switch (c) {
				case 1:
					// call startGame() method and starts player vs computer game
					game.startGame(false, false);
					break;	
				case 2:
					// call startGame() method and starts player vs player game
					game.startGame(true, false);
					break;
				case 0:
					// return to menu
					return;
				default:	
				 	// display error message
				 	System.out.println("Sorry, your choice wasn’t recognised, please try again.");
				 break;
			}
		}
		// end loop
		while(c != 0);
	}
	
	/**
	 * this method opens a file to be read 
	 */
	private void rulesAndInfo() {
		// initialise default values for readers
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        // initialise local variable for file name
		String fileName = "rulesAndInfo.txt";
		
		// call checkIfFileExists() method to check if file exists and is readable
		boolean checkFile = Menu.checkIfFileExists(fileName);
		
		// error handling if file does not exist or can not read file
		while (checkFile == false) {
			
			// error if file not found, return to menu
	        System.out.println("Error - File not found or can not be read.");
	        return;
		}
        
        try
        {
        	// read file from user input
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader); 
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            // loop if there is text on the line
            while (nextLine != null)
            {
            	// display line
                System.out.println(nextLine);
                // read next line
                nextLine = bufferedReader.readLine();
            }
        }
        // error handling for reader
        catch (IOException e)
        {
        	// display error message
            System.out.println("IO Error reading from file: " + e);
        }
        // make sure any files opened are closed
        finally
		{
			try
			{
				// close buffer if open
				if (bufferedReader != null) {
					bufferedReader.close();
	            }
			}
			// error handling for reader
			catch (IOException e)
	        {
	        	// display error message
	            System.out.println("IO Error closing file: " + e);
	        }
		}
	}

	/**
	 * Default constructor
	 */
	public Menu() {
		game = new GameInterface();
	}

	/**
	 * Method that displays the menu of options available to the user.
	 */
	public void displayMenu() {
		System.out.println("1. Start Game.");
		System.out.println("2. Load Game.");
		System.out.println("3. View Rules & How To Play.");
		System.out.println("0. Exit");
	}
	
	/**
	 * Method that displays the different game modes available to the user.
	 */
	public void displayGameModes() {
		System.out.println("1. Player vs. Computer.");
		System.out.println("2. Player vs. Player.");
		System.out.println("0. Return to Menu");
	}
	
	/**
	 * This method checks that the input from the user is a valid Integer and then gets the user to keep trying until an integer is entered.
	 * 
	 * @param userPrompt - String of the prompt to ask user to input
	 * @return validInt - integer that is valid
	 */
	public static int checkInt(String userPrompt) {
		{
			// prompt user for input
			Scanner s = new Scanner(System.in);
			System.out.println(userPrompt);
			
			// while invalid int entered
			while (!s.hasNextInt())
			{
				// prompt user again
				s.next();
				System.out.println("Error - Please enter only a whole number. " + userPrompt);
			}
			int validInt = s.nextInt();
			return validInt;
		}
	}
	
	/**
	 * This method checks that the input from the user is a valid char and then gets the user to keep trying until a valid char is entered.
	 * 
	 * @param userPrompt - String of the prompt to ask user to input
	 * @return validChar - char that is valid
	 */
	public static char checkChar(String userPrompt) {
		{
			// prompt user for input
			Scanner s = new Scanner(System.in);
			System.out.println(userPrompt);
			char checkChar =  s.next().toLowerCase().charAt(0);
			
			// while invalid char has been entered
			while (!Character.isLetter(checkChar))
			{
				// prompt user again
				System.out.println("Error - Please enter a single letter. " + userPrompt);
				checkChar = s.next().toLowerCase().charAt(0);
			}
			char validChar = checkChar;
			return validChar;
		}

	}
	
	/**
	 * method that checks if any key has been entered
	 */
	public static void anyKeyToContinue() {
		try {
            System.in.read();
        }  
        catch(Exception e)
        {}
	}
	
	
	/**
	 * Method that gets a file name as an input parameter and checks if the file exists and is readable.
	 * 
	 * @param file - a string that contains a file name
	 * @return a boolean that is true if the file exists and is readable
	 */
	public static boolean checkIfFileExists(String file) {
		// initialise variables
		boolean exists, readable;
		File temp = new File(file);
		
		// check if file exists
		exists = temp.exists();
		
		// check if file is readable
		readable= temp.canRead();
				
		// if files exists and is readable return true
		if (exists == true && readable == true) {
			return true;
		}
		
		// if file does not exists return false
		else {
			return false;
		}
	}
}
