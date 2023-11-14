// import scanner
import java.util.Scanner;

import java.io.File;

/**
 * menu class which displays options to the user
 * 
 * @author Jamie Fergus 190018054
 */
public class Menu {
	
	// declare field
	private ListOfHealthProfessionals healthProfessionalList;
	private Tree myTree;
	
	
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
		
		healthProfessionalList = new ListOfHealthProfessionals();
		healthProfessionalList.readListFromFile();
		
		new File("data").mkdir();
		
		for(int i=0; i<healthProfessionalList.getListSize(); i++) {
        	new File("data\\"+healthProfessionalList.getNameFromList(i)).mkdir();
        }
		
		
		displayMessage();
		int c = 0;
		String name, profession, location, newName, newProfession, newLocation, startDate, endDate;
		
		
		do {
			displayMenu();		
			c = checkInt("Please make a choice, and press ENTER: ");	
			switch (c)
			{
				case 1:
					healthProfessionalList.printList();
					break;
				case 2:
					name = checkString("Please enter the name of the health professional");
					profession = checkString("Please enter the proffesion of the health professional");
					location = checkString("Please enter the health professional's location");
					
					healthProfessionalList.addToList(name, profession, location);
					healthProfessionalList.writeListToAFile();
					break;
				case 3:
					name = checkString("Please enter the name of the health professional, you wish to delete");
					
					healthProfessionalList.deleteItem(name);
					healthProfessionalList.writeListToAFile();
					break;
				case 4:
					name = checkString("Please enter the name of the health professional, you wish to edit");
					
					newName = checkString("Please enter the new name of the health professional");
					newProfession = checkString("Please enter the new proffesion of the health professional");
					newLocation = checkString("Please enter the health professional's new location");
					
					healthProfessionalList.editItem(name, newName, newProfession, newLocation);
					healthProfessionalList.writeListToAFile();
					break;
				case 5:
					
					for(int i=0; i<healthProfessionalList.getListSize(); i++) {
						searchAllFiles("data\\"+healthProfessionalList.getNameFromList(i)+"\\", healthProfessionalList.getNameFromList(i));
			        }
					break;
				case 6:
					
					name = checkString("Please enter the name of the health professional, you wish to schedule an appointment for.");
					
					while (healthProfessionalList.findItem(name) == -1) {
						name = checkString("Error - Name not on list - Please enter the name of the health professional, you wish to schedule an appointment for.");
					}
					
					int month = checkInt("Please enter the month for the appointment");
					
					while (month<1 || month>12) {
						month = checkInt("Error - Invalid month provided - Please enter the month for the appointment");
					} 
					
					int day = checkInt("Please enter the day for the appointment.");
					
					while (day<1 || (month==2 && day>28) || ((month==1||month==3||month==5||month==7||month==8||month==10||month==12)&& day>31) || ((month==4||month==6||month==9||month==11)&& day>30)) {
						day = checkInt("Error - Invalid day provided - Please enter the day for the appointment");
					} 
					
					String date = month + "," + day;
					
					int startTime = checkInt("Please enter the start time of the appoinment, in 24 hours format.");
					
					while (startTime < 800 || startTime > 1730 || startTime % 100 != 0 && startTime % 100 != 30) {
						startTime = checkInt("Error - Invalid time provided - Please enter the start time between 800 and 1730, in 30 minute intervals, of the appoinment, in 24 hours format.");
					} 
					
					String treatmentType = checkString("Please enter the type of treatment.");
					
					for(int i=0; i<healthProfessionalList.getListSize(); i++) {
						scheduleAppointment("data\\"+name+"\\"+date+"\\", startTime, treatmentType);
			        }
					
					
					break;
				case 7:
					
					name = checkString("Please enter the name of the health professional, you wish to seach an appointments for.");
					
					while (healthProfessionalList.findItem(name) == -1) {
						name = checkString("Error - Name not on list - Please enter the name of the health professional, you wish to seach an appointments for.");
					}
					
					int startMonth = checkInt("Please enter the month you want to start the search from.");
					
					while (startMonth<1 || startMonth>12) {
						startMonth = checkInt("Error - Invalid month provided - Please enter the month you want to start the search from.");
					} 
					
					int startDay = checkInt("Please enter the day you want to start the search from.");
					
					while (startDay<1 || (startMonth==2 && startDay>28) || ((startMonth==1||startMonth==3||startMonth==5||startMonth==7||startMonth==8||startMonth==10||startMonth==12)&& startDay>31) || ((startMonth==4||startMonth==6||startMonth==9||startMonth==11)&& startDay>30)) {
						day = checkInt("Error - Invalid day provided - Please enter the day you want to start the search from.");
					} 
					
					int endMonth = checkInt("Please enter the month you want to end the search at.");
					
					while (endMonth < startMonth || endMonth<1 || endMonth>12) {
						endMonth = checkInt("Error - Invalid month provided - Please enter the month you want to end the search at.");
					} 
					
					int endDay = checkInt("Please enter the day you want to end the search at.");
					
					while ((startMonth == endMonth && endDay < startDay) || endDay<1 || (endMonth==2 && startDay>28) || ((endMonth==1||endMonth==3||endMonth==5||endMonth==7||endMonth==8||endMonth==10||endMonth==12)&& endDay>31) || ((endMonth==4||endMonth==6||endMonth==9||endMonth==11)&& endDay>30)) {
						day = checkInt("Error - Invalid day provided - Please enter the day you want to end the search at.");
					} 
					
					startDate = startMonth + "," + startDay;
					endDate = endMonth + "," + endDay;
					
					for(int i=0; i<healthProfessionalList.getListSize(); i++) {
						searchSpecificFiles("data\\"+name+"\\", startDate, endDate);
			        }
					
					break;
				case 8:
					
					break;
				case 0:
					healthProfessionalList.writeListToAFile();
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
		System.out.println("1 - View list of health profesionals.");
		System.out.println("2 - Add health professional.");
		System.out.println("3 - Delete health professional.");
		System.out.println("4 - Edit health professional.");
		System.out.println("5 - Display all health professional appointments.");
		System.out.println("6 - Schedule an appointment.");
		System.out.println("7 - Search for an appointment.");
		System.out.println("8 - Undo.");
		
		System.out.println("0 - Exit");
	}

	/**
	 * display all appointments
	 * 
	 * @param path
	 * @param startDate
	 * @param endDate
	 */
	public void searchAllFiles(String path, String name) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
			  System.out.println("date " + listOfFiles[i].getName());
			  myTree = new Tree();
			  myTree.readFromFile(path + listOfFiles[i].getName());
			  myTree.printTree();
		  }
		}
		if (listOfFiles.length == 0){
			  System.out.println("There are no appointments for " + name + ".");
		  }
		 
	}
	
	/**
	 * search for appointment between two dates
	 * 
	 * @param path - the file path
	 * @param startDate - the start date of the appointments
	 * @param endDate - the end date of the appoint
	 */
	public void searchSpecificFiles(String path, String startDate, String endDate) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		boolean foundDates = false;

		for (int i = 0; i < listOfFiles.length; i++) {
			
			if (listOfFiles[i].getName().equals(startDate)) {
				foundDates = true;
			}
			if (foundDates = true) {
				if (listOfFiles[i].isFile()) {
					  System.out.println("date " + listOfFiles[i].getName());
					  myTree = new Tree();
					  myTree.readFromFile(path + listOfFiles[i].getName());
					  myTree.printTree();
				  }
			}
			if (listOfFiles[i].getName().equals(endDate)) {
				foundDates = false;
			}
		} 
	}
	
	/**
	 * display all appointments
	 * 
	 * @param path - the file path
	 * @param startDate - the start date of the appointments
	 * @param treatmentType - the type of treatment
	 */
	public void scheduleAppointment(String path, int startTime, String treatmentType) {
		myTree = new Tree();
		if (myTree.isTreeEmpty() == true) {
			myTree.createEmptyTree();
		}
		
		int endTime;
		
		if (startTime % 100 == 0) {
			endTime = startTime + 30;
		}
		else {
			endTime = startTime + 70;
		}
		
		myTree.addToTree(myTree.getRoot(), null, startTime, endTime, treatmentType);
		myTree.writeToAFile(path);
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
	
	/**
	 * input validation for String
	 * 
	 * @param userPrompt - contains prompt for user
	 * @return
	 */
	public String checkString(String userPrompt) {
		
		// prompt user for input
		Scanner s = new Scanner(System.in);
		System.out.println(userPrompt);
			
		// while invalid float entered
		while (!s.hasNext())
		{
			// prompt user again
			s.next();
			System.out.println("Error - Please enter a name. " + userPrompt);
		}
		String validString = s.next();
		return validString;
	}
}
