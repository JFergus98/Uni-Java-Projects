// import libraries
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * creates a binary tree of appointments
 *
 * @author Jamie Fergus 190018054
 */
public class Tree {
	// declare field
	private TreeNode root;
	private final int[] startTimes = {1300, 1030, 900, 830, 800, 1000, 930, 1200, 1130, 1100, 1230, 1530, 1430, 1400, 1330, 1500, 1700, 1630, 1600, 1700, 1730};
	private final int[] endTimes = {1330, 1100, 930, 900, 830, 1030, 1000, 1230, 1200, 1130, 1300, 1600, 1500, 1430, 1400, 1530, 1730, 1700, 1630, 1730, 1800};
	
	/**
	 * default constructor
	 */
	Tree(){
		root = null;
	}

	/**
	 * gets the trees root node
	 * 
	 * @return the root node
	 */
	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * sets the trees root node
	 * 
	 * @param the root node
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	/**
	 * checks if the tree is empty
	 * 
	 * @return true if empty
	 */
	public boolean isTreeEmpty()
    {
    	return (root == null);
    }
	
	/**
	 * removes an appointment from the diary
	 * 
	 * @param current - the current node being processed
	 * @param previous - the previous node
	 * @param startTime - the time the appointment starts
	 * @return the current node
	 */
	public TreeNode deleteAppointment(TreeNode current, TreeNode previous, int startTime) {
		if (current == null) {
			System.out.println("The start time entered was not found");
		}
		
		else if (startTime < current.getStartTime()) {
			deleteAppointment(current.getLeft(), current, startTime);
		}
		
		else if (startTime > current.getStartTime()) {
			deleteAppointment(current.getRight(), current, startTime);
		}
		
		else if (startTime == current.getStartTime()) {
			current.setIsFull(false);
			current.setTreatmentType(null);
			System.out.println("The appointment has been removed");
			return current;
		}
		return current;
	}
	
	/**
	 * fills the tree with empty appointments
	 */
	public void createEmptyTree() {
		for(int i=0;i<20;i++) {
			setRoot(addToTree(root, null, startTimes[i], endTimes[i], "null"));
		}
		root.getRight().getRight().setIsFull(false);
	}
	
	/**
	 * recursive method adds a new node to the tree or allows a user to update the tree with a new appointment
	 * 
	 * @param current - the current node being processed
	 * @param previous - the previous node
	 * @param startTime - the time the appointment starts
	 * @param endTime - the time the appointment ends
	 * @param treatmentType - the type of treatment the patient will receive
	 * @return the current node
	 */
	public TreeNode addToTree(TreeNode current, TreeNode previous, int startTime, int endTime, String treatmentType) {
		if (current == null) {
			current = new TreeNode(startTime, endTime, previous);
		}
		
		else if (startTime < current.getStartTime()) {
			current.setLeft(addToTree(current.getLeft(), current, startTime, endTime, treatmentType));
		}
		
		else if (startTime > current.getStartTime()) {
			current.setRight(addToTree(current.getRight(), current, startTime, endTime, treatmentType));
		}
		
		else if (startTime == current.getStartTime()) {
			current.setIsFull(true);
			current.setTreatmentType(treatmentType);
			System.out.println("Appointment scheduled");
			return current;
		}
		return current;
	}
	
	/**
	 * writes the tree to file
	 * 
	 * @param filePath - String holds the pathway to the file to be written to
	 */
	public void writeToAFile(String filePath)
    {
		// initialise default values for writers
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        // try
        try
        {
        	// create or overwrite file to be created
            outputStream = new FileOutputStream(filePath);
            printWriter = new PrintWriter(outputStream); 
            
            String line = "";
            
            // add to file
            printWriter.print(readFromTree(root, line));
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
	 * recursive method that converts a trees nodes data into a readable sting.
	 * 
	 * @param current - current node
	 * @param line - String to hold nodes data
	 * @return line - updated String to hold nodes data
	 */
	public String readFromTree(TreeNode current, String line) {
		if (current != null) {
			line = line + current.getStartTime() +"/"+ current.getEndTime() +"/"+ current.getTreatmentType() +"/"+ current.getIsFull() + "\n";
			
			line = readFromTree(current.getLeft(), line);
			
			line = readFromTree(current.getRight(), line);
		}
		return line;
	}
	
	/**
	 * reads a binary tree from a file
	 * 
	 * @param filePath - String holds the pathway to the file to be read
	 */
	public void readFromFile(String filePath) {
		// initialise default values for readers
		FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        try
        {
        	// read file
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader); 
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            // loop if there is text on the line
            while (nextLine != null)
            {
            	// add data to node
            	String[] parts = nextLine.split("/");
            	setRoot(writeToTree(root, null, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], Boolean.parseBoolean(parts[3])));
            	
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
	 * recursive method creates a new tree from parameters
	 * 
	 * @param current - the current node being implemented	
	 * @param previous - current nodes parent node
	 * @param startTime - the time the appointment starts
	 * @param endTime - the time the appointment ends
	 * @param treatmentType - details on type of treatment to be provided
	 * @param isFull - boolean that shows if the appointment is full or free
	 * @return current - current node
	 */
	public TreeNode writeToTree(TreeNode current, TreeNode previous, int startTime, int endTime, String treatmentType, boolean isFull) {
		if (current == null) {
			current = new TreeNode(startTime, endTime, treatmentType, isFull, previous);
		}
		
		else if (startTime < current.getStartTime()) {
			current.setLeft(writeToTree(current.getLeft(), current, startTime, endTime, treatmentType, isFull));
		}
		
		else if (startTime > current.getStartTime()) {
			current.setRight(writeToTree(current.getRight(), current, startTime, endTime, treatmentType, isFull));
		}
		return current;
	}
	
	/**
	 * prints the tree
	 */
	public void printTree() {
		if (isTreeEmpty() == true) {
			System.out.println("tree is empty.");
		}
		else {
			System.out.format("%10s%14s%19s%n", "Start Time", "End Time", "Treatment Type");
			printTreeInorder(getRoot());
		}
		
	}
	
	/** 
	 * recursive method used to print the tree in order
	 * 
	 * @param current - the current node being processed
	 */
	public void printTreeInorder(TreeNode current) {
		if (current != null) {
			printTreeInorder(current.getLeft());
			
			if (current.getIsFull() == true) {
				System.out.format("%10s%14s%19s%n", current.getStartTime(), current.getEndTime(), current.getTreatmentType());
			}
			
			printTreeInorder(current.getRight());
		}
		else {
			return;
		}
	}
}
