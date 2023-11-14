// import scanner
import java.util.Scanner;

/**
 * menu class which displays options to the user
 * 
 * @author Jamie Fergus 190018054
 */
public class Menu {
	
	// declare field
	private Tree shop;
	
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
		shop = new Tree();
		shop.readFromFile();
		displayMessage();
		int c = 0, itemID;
		do {
			displayMenu();		
			c = checkInt("Please make a choice, and press ENTER: ");	
			switch (c)
			{
				case 1:
					itemID = checkInt("Please enter the items ID: ");
					shop.setRoot(shop.addToTree(shop.getRoot(), null, itemID));
					break;
				case 2:
					shop.printTree(1);
					break;
				case 3:
					shop.printTree(2);
					break;
				case 4:
					shop.printTree(3);
					break;
				case 5:
					itemID = checkInt("Please enter the items ID: ");
					shop.findInTree(shop.getRoot(), itemID);
					break;
				case 6:
					System.out.format("%s%.2f%n","£ ", shop.calculateTotalCost(shop.getRoot(), 0));
					break;
				case 7:
					System.out.format("%s%.2f%n","£ ", shop.calculateTotalStockCost(shop.getRoot(), 0));
					break;
				case 8:
					itemID = checkInt("Please enter the items ID: ");
					shop.setRoot(shop.deleteFromTree(shop.getRoot(), null, itemID));
					break;
				case 0:
					shop.writeToAFile();
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
		System.out.println("Welcome to the shop!");
		System.out.println("Press the corresponding numbers to start.");
	}

	/**
	 * displays the choice the user has on the menu
	 */
	private void displayMenu() 
	{
		System.out.println("\nMenu :");
		System.out.println("1 - Add to item to shop.");
		System.out.println("2 - Print shop items inorder.");
		System.out.println("3 - Print shop items preorder.");
		System.out.println("4 - Print shop items postorder.");
		System.out.println("5 - Find item in shop.");
		System.out.println("6 - Calculate total cost.");
		System.out.println("7 - Calculate total stock cost.");
		System.out.println("8 - Remove item from shop.");
		
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
