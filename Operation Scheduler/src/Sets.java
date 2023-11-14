/** 
 * Imports all the java libraries used in code
 */
import java.lang.reflect.Array; 
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/** 
 * Creates the set class
 */
public class Sets {
	/** 
	 * Sets up all the sets used in the program for each months date
	 */
	private Set<Integer> fixMonth;
	private Set<Integer> fixDay1;
	private Set<Integer> fixDay2;
	private Set<Integer> fixDay3;
	private Set<Integer> userMonth;
	private Set<Integer> userDay;
	int day = 0;
	int userChoice;
	
	/** 
	 * Creates a new instance of the month set (1-12)
	 */
	
	public void newMonth()
	{
		fixMonth = new HashSet<Integer>();
	}
	
	/** 
	 * Creates a new instance of the feb set (1-28)
	 */
	public void newFeb()
	{
		fixDay1 = new HashSet<Integer>();
		genFeb();
	}
	
	/** 
	 * Creates a new instance of the 30 day set (1-30)
	 */
	public void new30()
	{
		fixDay2 = new HashSet<Integer>();
		gen30();
	}
	
	/** 
	 * Creates a new instance of the 31 day set (1-31)
	 */
	public void new31()
	{
		fixDay3 = new HashSet<Integer>();
		gen31();
	}
	/** 
	 * Creates a new instance of the users month set
	 */
	public void newUserM()
	{
		userMonth = new HashSet<Integer>();
	}
	/** 
	 * Creates a new instance of the users day set
	 */
	public void newUserD()
	{
		userDay = new HashSet<Integer>();
	}
	
	/** 
	 * Adds in the numbers 1-12 to the month set
	 */
	public void genMonth()
	{
		int month = 1;
		while(fixMonth.size() < 12) {
			fixMonth.add(month);
			month++;

		}
	}
	/** 
	 * Adds in 1-28 to the feb set
	 */
	public void genFeb()
	{
		int day = 1;
		while(fixDay1.size() < 28) {
			fixDay1.add(day);
			day++;

		}
	}
	/** 
	 * Adds in 1-30 to the 30 day set
	 */
	public void gen30()
	{
		int day = 1;
		while(fixDay2.size() < 30) {
			fixDay2.add(day);
			day++;

		}
	}
	/** 
	 * Adds in 1-31 to the 31 day set
	 */
	public void gen31()
	{
		int day = 1;
		while(fixDay3.size() < 31) {
			fixDay3.add(day);
			day++;

		}
	}
	/** 
	 * Collects the user input for what month they want to use, validates it and stores it into the user month set
	 */
	public void genUserM()
	{
		System.out.println("Enter the month (1-12): ");
		Scanner userM = new Scanner(System.in);  
	    userChoice = userM.nextInt();
	    if(userChoice > 12 || userChoice < 1) 
	    {
	    	genUserM();
	    }
		while(userMonth.size() < 1) {
			userMonth.add(userChoice);
		}
	}
	
	/** 
	 * Based on what month the user input, it generates the set to the corresponding months days (eg 30 for june)
	 */
	
	public void genUserD()
	{
		Set<Integer> copy_Month = new HashSet<Integer>(fixMonth);
		Set<Integer> copy_userMonth = new HashSet<Integer>(userMonth);
		copy_Month.retainAll(copy_userMonth);
		
		if(userChoice == 1 || userChoice == 3 || userChoice == 5 || userChoice == 7 || userChoice == 8 || userChoice == 10 || userChoice == 12) 
		{
			new31();
			System.out.println("Enter the day (1-31): ");
			Scanner userD = new Scanner(System.in);  
		    int userChoice2 = userD.nextInt();
		    if (userChoice2 > 31 || userChoice2 < 1) 
		    {
		    	genUserD();
		    }
			while(userDay.size() < 1) {
				userDay.add(userChoice2);

			}
		}
		else if(userChoice == 4|| userChoice == 6 || userChoice == 9 || userChoice == 11) 
		{
			new30();
			System.out.println("Enter the day (1-30): ");
			Scanner userD = new Scanner(System.in);  
		    int userChoice2 = userD.nextInt();
		    if (userChoice2 > 30 || userChoice2 < 1) 
		    {
		    	genUserD();
		    }
			while(userDay.size() < 1) {
				userDay.add(userChoice2);

			}
		}
		else 
		{
			newFeb();
			System.out.println("Enter the day (1-28): ");
			Scanner userD = new Scanner(System.in);  
		    int userChoice2 = userD.nextInt();
		    if (userChoice2 > 28 || userChoice2 < 1) 
		    {
		    	genUserD();
		    }
			while(userDay.size() < 1) 
			{
				userDay.add(userChoice2);

			}
		}
	}
	/** 
	 * Method to display the month and day user selected
	 */
	public void display() {
		System.out.println("The month chosen:" + userMonth);
		System.out.println("The day chosen:" + userDay);
	}
	
	public int getDayInt() {
		return userDay.iterator().next();
	}
	
	public int getMonthInt() {
		return userMonth.iterator().next();
	}

}