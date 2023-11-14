// import scanner
import java.util.Scanner;

/**
 * menu class which displays options to the user
 * 
 * @author Jamie Fergus 190018054
 */
public class Menu {
	
	// declare field
	private RPNCalculator rpn;
	
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
		int c = 0;
		do {
			displayMenu();		
			c = checkInt("Please make a choice, and press ENTER: ");	
			switch (c)
			{
				case 1:
					rpn = new RPNCalculator();
					rpn.evaluatePostfix();
					break;
				case 2:
					displayInstuctions();
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
	 * displays instructions on how to use calculator
	 */
	private void displayInstuctions() {
		System.out.println("Instuctions :");
		System.out.println("\n\tUsing the Menu : \n\t-Enter '1' to use the RPN calculator. \n\t-Enter '2' to view instructions. \n\t-Enter '0' to exit the software.");
		System.out.println("\n\tUsing the RPN calulator : \n\t-Data must be entered in valid postfix notation. (e.g '4,2,+') \n\t-Data must be separated with a single delimiting character of your choice. (e.g ',') \n\t-Delimiting character must be specified in the second prompt. \n\t-Delimiting character must not be an operator. \n\t-Invalid postfix notation will result in error. \n\t-Only enter numerical data (1,2,3...) and operators ('+ - * /') separated by delimiting character. \n\t-Invalid inputs (e.g 'Strings') will also result in error");
	}
	
	/**
	 * displays the choice the user has on the menu
	 */
	private void displayMenu() 
	{
		System.out.println("\nMenu :");
		System.out.println("1 - Use RPN calculator");
		System.out.println("2 - View instructions");
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
