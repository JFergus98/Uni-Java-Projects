// import required classes
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * creates a lottery using MySet classes. Allows for 1 or multiple users and weeks.
 * 
 * @author Jamie Fergus 190018054 
 */
public class Lottery 
{
	//declare fields
	private MySet lotteryNumbers;
	private LinkedList<MySet> userNumbers;
	private int lotteryMax;
	private final int cardinalityMax = 6;
	private int numberOfPlayers;
	private int weeks;
	
	/**
	 * default constructor
	 */
	Lottery()
	{
		//initialise fields
		lotteryNumbers = new MySet();
		userNumbers = new LinkedList<MySet>();
		lotteryMax = 59;
		numberOfPlayers = 1;
		weeks = 1;
	}
	
	/**
	 * allows the set amount number of users to play the lottery for the set amount of weeks
	 */
	public void playTheLottery()
	{
		// declare local fields
		int total[] = new int[numberOfPlayers];
		int netGain[] = new int[numberOfPlayers];
		
		// get player numbers
		getPlayerNumbers();
		
		// for each week
		for (int i=0; i<weeks;i++)
		{
			// get lottery numbers
			generateNewLotteryNumbers();
			
			// display this weeks lottery numbers
			System.out.print("\nWeek " + (i+1) + ":\n\tLottery numbers are: ");
			lotteryNumbers.printSet();
			
			// for each player display winnings and update total and netGain
			for (int i2=0; i2<numberOfPlayers;i2++)
			{
				int winnings = displayWinnings(i2, userNumbers.get(i2));
				total[i2] = total[i2] + winnings;
				netGain[i2] = netGain[i2] + winnings - 2;
			}
			System.out.println();
		}
		// for each player display total and netGain
		for (int i=0; i<numberOfPlayers;i++)
		{
			String s;
			NumberFormat myFormat = NumberFormat.getInstance();
			
			if (netGain[i]<0) {
				netGain[i] = netGain[i] * -1;
				s = "loss";
			}
			else {
				s = "gain";
			}
			System.out.print("\nPlayer " + (i+1) + " won a total of £ " + myFormat.format(total[i]) + " with a net " + s + " of £ " + myFormat.format(netGain[i])+ ".");
		}
		System.out.println();
	}
	
	/**
	 * displays a message telling if a user won and if they did how much
	 * 
	 * @param player - an int used as an index for the players
	 * @param numbers - MySet - the current players Set of numbers
	 * 
	 * @return an int holding the winnings of the user
	 */
	public int displayWinnings(int player, MySet numbers)
	{
		// initialise local fields
		NumberFormat myFormat = NumberFormat.getInstance();
		int winnings = calculateWinnings(numbers);
		
		// display win or loss message
		if (winnings > 0) {
			System.out.print("\tPlayer " +  (player+1) + " won £" + myFormat.format(winnings) + ". ");
		}
		else {
			System.out.print("\tPlayer " +  (player+1) + " lost. ");
		}
		return winnings;
	}
	
	/**
	 * calculates the winnings of a user
	 * 
	 * @param numbers - the users set
	 * @return - an int holding how much the user won
	 */
	public int calculateWinnings(MySet numbers)
	{
		// initialise field
		int i = numbers.intersection(lotteryNumbers).getCardinality();
		
		// return correct amount
		if (i==6) {
			return 1000000;
		}
		else if (i==5) {
			return 1000;
		}
		else if (i==4) {
			return 100;
		}
		else if (i==3) {
			return 25;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * method clears the list of players set of numbers if it is not empty, then stores the new list of sets.
	 */
	public void getPlayerNumbers() 
	{
		// clear if not empty
		if (userNumbers.isEmpty()==false) {
			userNumbers.clear();
		}
		
		// for each player add to list
		for (int i=0; i<numberOfPlayers;i++) 
		{
			userNumbers.add(selectNumbers(i+1));
		}
	}
	
	/**
	 * prompts a user and allows them to enter their set of numbers and checks if it is valid
	 * 
	 * @param player - an int holding an index to the player
	 * @return - MySet - users set of numbers
	 */
	public MySet selectNumbers(int player)
	{
		MySet numbers = new MySet();
		System.out.println("Player " + player + " please enter your numbers.\nEach number entered should be within the range of 1 and " + lotteryMax + " and duplicates will not be accepted.");
		String userPrompt;
		
		while (numbers.getCardinality() < cardinalityMax)
		{
			userPrompt = "Player " + player + " please enter number " + (numbers.getCardinality()+1) + ".";
			int i = checkInt(userPrompt);
			if (i < 1 || i > lotteryMax) {
				System.out.println("Error - number outwith range.");
			}
			else {
				numbers.addToSet(i);
			}
		}
		return numbers;
	}
	
	/**
	 * clears and gets a new set of lottery numbers
	 */
	public void generateNewLotteryNumbers()
	{
		if (lotteryNumbers.isSetEmpty()==false) {
			lotteryNumbers.clearSet();
		}
		generateRandomNumbers(lotteryNumbers);
	}
	
	/**
	 * randomly generates a set of numbers
	 * 
	 * @param numbers - the set to be generated
	 */
	public void generateRandomNumbers(MySet numbers)
	{
		while (numbers.getCardinality() < cardinalityMax)
		{
			numbers.addToSet(getRandomNumber(1,lotteryMax));
		}
	}
	
	/**
	 * used to change the number of players
	 */
	public void changeNumberOfPlayers()
	{
		String userPrompt = "Please enter the number of players, within the range of 1 and 10.";
		
		numberOfPlayers = changeInt(userPrompt, 1, 10);
	}
	
	/**
	 * used to change the number of weeks
	 */
	public void changeWeeks()
	{
		String userPrompt = "Please enter the number of weeks, you will be playing, within the range of 1 and 52.";
		
		weeks = changeInt(userPrompt, 1, 52);
	}
	
	/**
	 * used to change the max lottery number
	 */
	public void changeLotteryMax()
	{
		String userPrompt = "Please enter a number, within the range of 6 and 100.";
		
		lotteryMax = changeInt(userPrompt, 1, 10);
	}

	/**
	 * prompts the user and allows them to enter a new int value and checks if it is in the range
	 * 
	 * @param userPrompt - contains a message for the user
	 * @param min - the minimum accepted int value
	 * @param max - the maximum accepted int value
	 * @return the int value that was entered and accepted
	 */
	public int changeInt(String userPrompt, int min, int max)
	{
		int num = 0;
		boolean b = true;
		
		while (b == true)
		{
			int i = checkInt(userPrompt);
			
			if (i < min || i > max) {
				System.out.println("Error - number outwith range.");
			}
			else {
				b = false;
				num = i;
			}
		}
		return num;
	}
	
	/**
	 * this method returns a random integer from a range from parameters
	 * 
	 * @param min - integer holding the minimum value that could be returned
	 * @param max - integer holding the maximum value that could be returned
	 * @return - integer of result
	 */
	public static int getRandomNumber(int min, int max)
	{
		// get random number
	    int randomInt = (int)(Math.random()*((max-min)+1))+min;
	    // return random number
	    return randomInt;
	}
	
	/**
	 * This method checks that the input from the user is a valid Integer and then gets the user to keep trying until an integer is entered.
	 * 
	 * @param userPrompt - String of the prompt to ask user to input
	 * @return validInt - integer that is valid
	 */
	public int checkInt(String userPrompt)
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
