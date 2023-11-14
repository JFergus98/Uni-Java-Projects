// import scanner
import java.util.Scanner;

/**
 * menu class which displays options to the user
 * 
 * @author Jamie Fergus 190018054
 */
public class Menu {
	
	// declare field
	private Lottery lottery = new Lottery();
	
	/**
	 * main method, creates instance of menu.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.runMenu();
	}

	/**
	 * allows the user to select a choice from the menu
	 */
	private void runMenu() 
	{
		displayMessage();
		int c = 0;
		do {
			displayMenu();		
			c = checkInt("Please make a choice, and press ENTER: ");	
			switch (c)
			{
				case 1:
					lottery.playTheLottery();
					break;
				case 2:
					lottery.changeLotteryMax();
					break;
				case 3:
					lottery.changeNumberOfPlayers();
					break;
				case 4:
					lottery.changeWeeks();
					break;
				case 0:
					break;
				 default:
				 	System.out.println("Error, invalid input, please try again.");
				 	break;
			}	
		} while(c != 0); 
	}

	/**
	 * displays welcome message
	 */
	private void displayMessage() {
		System.out.println("Welcome to the lottery!");
		System.out.println("the default settings are as follows:\tnumber of player = 1, number of weeks = 1, max lottery number = 59.");
		System.out.println("Press the corresponding numbers start.");
	}

	/**
	 * displays the choice the user has on the menu
	 */
	private void displayMenu() 
	{
		System.out.println("\nMenu :");
		System.out.println("1 - Play lottery");
		System.out.println("2 - Change max lottery number.");
		System.out.println("3 - Change number of players.");
		System.out.println("4 - Change number of weeks.");
		System.out.println("0 - Exit");
	}

	/**
	 * This method checks that the input from the user is a valid Integer and then gets the user to keep trying until an integer is entered.
	 * 
	 * @param userPrompt - String of the prompt to ask user to input
	 * @return validInt - integer that is valid
	 */
	public int checkInt(String userPrompt) {
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
}
