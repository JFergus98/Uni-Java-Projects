// Import java.io classes
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;

//Import Scanner class
import java.util.Scanner;

/**
 * 
 * @author Jamie Fergus
 * @version 1.0
 */
public class Files {
	// Declare variables
	private Film film[] = new Film[3];
	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";
	
	/**
	 * Main method that runs a test of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a test instance of your Files class
		Files testFile = new Files();
		
		// Call the processUserChoices() method
		testFile.processUserChoices();

	}
	
	/**
	 * Method used to test the methods in this class
	 */
	public void runFileTests() {
		// display running tests message
		System.out.println("Running file tests...");
		
		// call the readFileFromUser() method
		readFileFromUser();
		
		// call the writeToAFile() method
		writeToAFile();
		
		// call the copyFile() method
		copyFile();
		
		// call the decipherFile() method
		decipherFile();
		
		// call the processDetailsFile() method
		processDetailsFile();
	}
	
	/**
	 * Method that displays the a menu of options available to the user.
	 */
	public void displayMenu() {
		System.out.println("Please select one of the options below:");
		System.out.println("1. Option 1: Enter file name to read file.");
		System.out.println("2. Option 2; Enter file name to write to file.");
		System.out.println("3. Option 3: Enter two file names to copy the contents of one to the other.");
		System.out.println("4. Option 4: Decpher 'mystery.txt' file.");
		System.out.println("5. Option 5: Display competitors and their average score.");
		System.out.println("6. Option 6: Display 2D array.");
		System.out.println("7. Option 7: Display collection of films.");
		System.out.println("0. Exit");
	}
	
	/**
	 * A method that processes the choices entered by the user.
	 */
	public void processUserChoices() {
		// A local variable to hold a choice entered by the user
		int c = 0;
		// Allows the program to keep running until the exit option is selected
		do {
			// Call the method to display choices to the user
			displayMenu();
			// Call method to check user input is an integer
			c = checkInt();
			// call the method selected by the users choice
			switch (c)
			{
				// runs if user selects option 1
				case 1:
					// call the readFileFromUser() method from the Files class
					readFileFromUser();
					// End of option 1
					break;
				// runs if user selects option 2	
				case 2:
					// call the writeToAFile() method
					writeToAFile();
					// End of option 2
					break;
				// runs if user selects option 3
				case 3:
					// call the copyFile() method
					copyFile();
					// End of option 3
					break;
				// runs if user selects option 4	
				case 4:
					// call the decipherFile() method
					decipherFile();
					// End of option 4
					break;
				// runs if user selects option 5
				case 5:
					// call the processDetailsFile() method
					processDetailsFile();
					// End of option 5
					break;
				// runs if user selects option 6
				case 6:
					// call the write2DArray() method
					write2DArray();
					// End of option 6
					break;
				// runs if user selects option 7
				case 7:
					// call the ReadAndWriteObjects() method
					ReadAndWriteObjects();
					// End of option 7
					break;
				// runs if user inputs option 0	
				case 0:
					// End of option 0
					break;
				// error handling if user inputs wrong number
		 		default:
		 			// display error message
		 			System.out.println("Sorry, your choice wasn’t recognised, please try again");
		 		// End of error handling
		 		break;
			}
		}
		// end loop
		while(c != 0); 
	} 
	
	/**
	 * Method that asks the user for a file name, opens the file, reads the file line by line.
	 * As each line is read in it should be displayed to the console. 
	 */
	public void readFileFromUser()
    {
		// initialise default values for readers
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        // Prompt the user to enter the file name
        System.out.println("Please enter the name of the text file you wish to read. for example: test.txt");
        Scanner s = new Scanner(System.in);
		String fileSearch = s.nextLine();
		
		// call checkIfFileExists() method to check if file exists and is readable
		boolean checkFile = checkIfFileExists(fileSearch);
		
		// error handling if file does not exist or can not read file
		while (checkFile == false) {
			
			// Prompt the user to enter the file name again
	        System.out.println("Error - File not found or can not be read." + "\n" + "Please enter the name of the text file you wish to read. for example: test.txt");
			fileSearch = s.nextLine();
			
			// call checkIfFileExists() method to check if file exists and is readable
			checkFile = checkIfFileExists(fileSearch);
		}
		
        
		// try
        try
        {
        	// read file from user input
            fileReader = new FileReader(fileSearch);
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
        	// try
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
	 *  method that opens a file for writing to and 
	 *  then uses a loop to read in lines of text from the user one at a time 
	 *  and writes them into the file
	 */
	public void writeToAFile()
    {
		// initialise default values for writers
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        // try
        try
        {
        	// create or overwrite file to be created
            outputStream = new FileOutputStream("testOutput.txt");
            printWriter = new PrintWriter(outputStream); 
            
            // prompt user for 1st line of the document
            System.out.println("Please enter the 1st line to be entered for the file, or press 'enter' to cancel.");
            Scanner s = new Scanner(System.in);
    		String userLine = s.nextLine();
            
    		// loop until user presses the return key
            while (userLine.isEmpty() == false)
            {
            	// add line to file
            	printWriter.println(userLine);
            	
            	// prompt user for the next line of the document
            	System.out.println("Please enter the next line, or press 'enter' to cancel.");
            	userLine = s.nextLine();
            }
        }
        
        // error handling for writer
        catch (IOException e)
        {
        	// Display error message
            System.out.println("Error in file write: " + e);
        }
        // make sure any files opened are closed
        finally
		{
        	// close the output file if open
            if (printWriter != null) {
            	printWriter.close();	
            }
		}
    }
	
	/**
	 *  Method that asks the user for a 2 file names uses the copyAndDecipherFileCode() method to copy the content of the 1st file to the second file.
	 * 	Creating a copy of the first file.
	 */
	public void copyFile() {
		// initialise variable
		boolean decipher = false, process = false;
				
		// prompt the user for the input file name
        System.out.println("Please enter the name of the text file you wish to copy. For example: test.txt");
        Scanner s = new Scanner(System.in);
		String fileSearchInput = s.nextLine();
        
		// call checkIfFileExists() method to check if file exists and is readable
		boolean checkFile = checkIfFileExists(fileSearchInput);
				
		// error handling if file does not exist or can not read file
		while (checkFile == false) {
					
			// Prompt the user to enter the file name again
			System.out.println("Error - File not found or can not be read." + "\n" + "Please enter the name of the text file you wish to read. for example: test.txt");
			fileSearchInput = s.nextLine();
					
			// call checkIfFileExists() method to check if file exists and is readable
			checkFile = checkIfFileExists(fileSearchInput);
		}
		
        // prompt the user for an output file name
    	System.out.println("Please enter the name of the text file you wish to copy to. For example: test.txt");
    	String fileSearchOutput = s.nextLine();
    	
    	// Call method copyDecipherAndProcessFileCode()
    	copyDecipherAndProcessFileCode(fileSearchInput, fileSearchOutput, decipher, process);    
            
	}
	
	/**
	 * Method witch reads in 'mystery.txt' file and uses the copyAndDecipherFileCode() method to decipher the text and outputs it to another file 'deciphered.txt'.
	 */
	public void decipherFile() {
		// initialise variables
		String input = "mystery.txt";
		String output = "deciphered.txt";
		boolean decipher = true, process = false;
		
		// Call method copyDecipherAndProcessFileCode()
		copyDecipherAndProcessFileCode(input, output, decipher, process);    
	}
	
	/**
	 *  Method witch reads in 'details.txt' file and uses the copyDecipherAndProcessFileCode() method to sort each line displaying the name of the competitor and the average of their scores and outputs it to another file 'detailsSorted.txt'.
	 */
	public void processDetailsFile() {
		// initialise variables
		String input = "details.txt";
		String output = "detailsSorted.txt";
		boolean decipher = false, process = true;
			
		// Call method copyDecipherAndProcessFileCode()
		copyDecipherAndProcessFileCode(input, output, decipher, process);
	}
	
	/**
	 * 	Method that gets to files names as input parameters, opens the 1st file, reads the file line by line.
	 * 	If the first boolean parameter is true - as each line is read in it should be deciphered using the cipherDecipherString() method and then is displayed to the console and copied to the second file.
	 *  
	 *  If the second boolean parameter is true - as each line is read in it should and sorts each line it should display the name of the competitor and the average of their scores and outputs it to the output file.
	 *  
	 *  If both boolean parameter is false - as each line is read in it should be displayed to the console and copied to the second file. Creating a copy of the first file.
	 *  
	 * @param inputFile - String holding the name of the file to be copied or deciphered.
	 * @param outputFile - String holding the name of the file that will have each line of the first file copied too or decipher then copied too.
	 * @param needsDeciphered - boolean to check if the file needs deciphered.
	 * @param needsProcessed - boolean to check if the file needs processed.
	 */
	public void copyDecipherAndProcessFileCode(String inputFile, String outputFile, boolean needsDeciphered, boolean needsProcessed) {
		// initialise default values for readers and writers
		FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        // try
		try
        {
			// open the input file
            fileReader = new FileReader(inputFile);
            bufferedReader = new BufferedReader(fileReader); 
    		
            // open the output file
            outputStream = new FileOutputStream(outputFile);
            printWriter = new PrintWriter(outputStream);
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            // if file needs deciphered
            if (needsDeciphered == true){
            	
            	// while there are lines in the input file
            	while (nextLine != null) {
                    	
                    // call method cipherDecipherString() to decipher line
                    String decipheredLine = cipherDecipherString(nextLine);
                    	
                    // display it to screen
                    System.out.println(decipheredLine);
                    	
                  	// write it to the output file
                    printWriter.println(decipheredLine);
                    	
                    // read the next line
                    nextLine = bufferedReader.readLine();
                    
                    // end while
                }
            }
            	
            // if filed needs processed
            else if (needsProcessed == true){
            		
            	// while there are lines in the input file
                while (nextLine != null) {
                    	
                    // Declare local variable
                    int[] numbers = new int[5];
                    float total = 0f, average = 0f;
                    	
                	// extract the name and scores from the line of text
                    String[] parts = nextLine.split(" ");

                    	
                    // for loop code to calculate total
                    for (int i=0; i<numbers.length; i++) {
                    	numbers[i] = Integer.parseInt(parts[i+2]);
                    	total = total + numbers[i];
                	}
                    	
                    // calculate average
                    average = total / numbers.length;

                    // declare String to hold sorted line to be displayed and output to a file
                    String sortedLine = parts[1] + ", " + parts[0] + ": Average Score is " + average;
                    	
                	// Display to the console
                    System.out.println(sortedLine);
                    	
                    // Output to a file
                    printWriter.println(sortedLine);
                    	
                    // read next line
                    nextLine = bufferedReader.readLine();
                        
                // end while
                }
            }
            	
            // if file just needs copied
            else {
            		
            	// while there are lines in the input file
                while (nextLine != null) {
                    	
            	// display it to screen and,
                System.out.println(nextLine);
                	
              	// write it to the output file
                printWriter.println(nextLine);
                	
                // read the next line
                nextLine = bufferedReader.readLine();
                	
                // end while
               }
            } 
        }
        // error handling for read and write
        catch (IOException e)
        {
        	// display error message
            System.out.println("IO Error copying from file: " + e);
        }
		// make sure any files opened are closed
		finally
		{
			// try
			try
			{
				// close the input file if open
				if (bufferedReader != null) {
					bufferedReader.close();
	            }
			}
			// error handling for read
			catch (IOException e)
	        {
	        	// display error message
	            System.out.println("IO Error closing file: " + e);
	        }
            
            // close the output file if open
            if (printWriter != null) {
            	printWriter.close();	
            }
			
		}
	}
	
	/**
	 * Method that gets a file name as an input parameter and checks if the file exists and is readable.
	 * 
	 * @param file - a string that contains a file name
	 * @return a boolean that is true if the file exists and is readable
	 */
	public boolean checkIfFileExists(String file) {
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
	
	/**
	 *  Method that creates a 2D array of the first 10 prime numbers and writes them to a file and displays them on the console.
	 */
	public void write2DArray() {
		// Initialise variables
		int[] primeNumbers = new int[10];
		
		primeNumbers[0] = 2;
		primeNumbers[1] = 3;
		primeNumbers[2] = 5;
		primeNumbers[3] = 7;
		primeNumbers[4] = 11;
		primeNumbers[5] = 13;
		primeNumbers[6] = 17;
		primeNumbers[7] = 19;
		primeNumbers[8] = 23;
		primeNumbers[9] = 29;
		
		// initialise default values for writers
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        // try
        try
        {
        	// create or overwrite file to be created
            outputStream = new FileOutputStream("primeNumbers.txt");
            printWriter = new PrintWriter(outputStream); 
            
            // write 1st line
    		String nextLine = "The First 10 prime numbers are:";
            
    		// Display first line
    		System.out.println(nextLine);
    		
            // Output first line to file
            printWriter.println(nextLine);
            	
            // for loop to add each prime number
            for (int i=0; i<primeNumbers.length; i++) {
            	
            	nextLine = primeNumbers[i] + ", ";
            	
            	// Display next line
        		System.out.print(nextLine);
            	
            	// add next line to file
                printWriter.print(nextLine);
        	}
            // Display new line
    		System.out.println("");
        	
        	// add new line to file
            printWriter.println("");
        }
        
        // error handling for writer
        catch (IOException e)
        {
        	// Display error message
            System.out.println("Error in file write: " + e);
        }
        // make sure any files opened are closed
        finally
		{
        	// close the output file if open
            if (printWriter != null) {
            	printWriter.close();	
            }
		}
	}
	
	
	/**
	 *  method that reads from a files the fields of 3 film objects then displays them to console and to a file in a more readable format
	 */
	public void ReadAndWriteObjects() {
		// initialise default values for readers and writers
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
       
        // try
        try
        {
        	// read file from user input
            fileReader = new FileReader("collection.txt");
            bufferedReader = new BufferedReader(fileReader); 
            
            // open the output file
            outputStream = new FileOutputStream("sortedCollection.txt");
            printWriter = new PrintWriter(outputStream);
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            
            
            // loop if there is text on the line
            for (int i=0; i<film.length; i++)
            {
            	
            	// extract the different fields
                String[] parts = nextLine.split(", ");
            	
            	film[i].setTitle(parts[0]);
            	film[i].setDirector(parts[1]);
            	film[i].setRunningTime(Integer.parseInt(parts[2]));
            	film[i].setPurchaseCost(Integer.parseInt(parts[3]));
            	
            	String sortedLine = "Film " + i + " :" + "\n" + "Title - " + film[i].getTitle() + "\n" + "Director - " + film[i].getDirector() + "\n" + "Run time - " + film[i].getRunningTime() + " minutes" + "\n" +  "cost - £ " + film[i].getPurchaseCost() + "." + "\n";
            	
            	// display line
                System.out.println(sortedLine);
                
                // write it to the output file
                printWriter.println(sortedLine);
                
                // read next line
                nextLine = bufferedReader.readLine();
            } 
        }
        // error handling for read and write
        catch (IOException e)
        {
        	// display error message
            System.out.println("IO Error copying from file: " + e);
        }
		// make sure any files opened are closed
		finally
		{
			// try
			try
			{
				// close the input file if open
				if (bufferedReader != null) {
					bufferedReader.close();
	            }
			}
			// error handling for read
			catch (IOException e)
	        {
	        	// display error message
	            System.out.println("IO Error closing file: " + e);
	        }
            
            // close the output file if open
            if (printWriter != null) {
            	printWriter.close();	
            }
			
		}
	}
	
	/**
	 * method to encipher and decipher a given String using parallel arrays (crypt1 & crypt2)
	 *
	 * @param text A String containing text that is to be enciphered or deciphered
	 * @return A new String containing the result, e.g. the en/deciphered version of the String provided as an input
	 */
	private static String cipherDecipherString(String text)
	{
	    // declare variables we need
	    int i, j;
	    boolean found = false;
	    String temp="" ; // empty String to hold converted text

	    for (i = 0; i < text.length(); i++) // look at every character in text
	    {
	        found = false;
	        if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
	        {           
	            found = true; // yes!
	            temp = temp + crypt2.charAt(j); // add the cipher character to temp
	        } 
	        else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
	        {
	            found = true;
	            temp = temp + crypt1.charAt(j);
	        }

	        if (! found) // to deal with cases where char is NOT in crypt2 or 2
	        {
	            temp = temp + text.charAt(i); // just copy across the character
	        }
	    }
	    return temp;
	}
	
	/**
	 * This method checks that the input from the user is not a String and then gets the user to keep trying until and integer is entered.
	 * If an integer is entered that integer gets returned.
	 * 
	 * @return checkInt an integer that get set to the input from the user returned if the input is not a string
	 */
	public static int checkInt() {
		// Initialise variables
		boolean validInt = false;
		int checkInt = 0;
		
		// loop until user inputs integer
		do {
			
			// read in the choice entered by the user
			Scanner s = new Scanner(System.in);
		
			// error handling to make sure user input is an integer
			if (s.hasNextInt() == true) {
				checkInt = s.nextInt();
				validInt = true;
			}
			// Prompt user to try again
			else {
				System.out.println("Please enter only a whole number.");
			}
		}
		// end loop
		while (validInt == false);
		
		// return integer check by method
		return checkInt;
	}
	
	/**
	 * Default constructor initialising fields to their default value
	 */
	public Files() {
		film[0] = new Film();
		film[1] = new Film();
		film[2] = new Film();
	}
}
