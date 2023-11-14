import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ListOfHealthProfessionals {
	private LinkedList<HealthProfessional> list;
	
	/**
	 * default constructor
	 */
	public ListOfHealthProfessionals()
	{
		//initialise field
		list = new LinkedList<HealthProfessional>();
	}
	
	/**
	 * adds health professional
	 * 
	 * @param name - the name of the health professional
	 * @param profession - the profession of the health professional
	 * @param location - the location of the health profession
	 * @return true if added to list
	 */
	public boolean addToList(String name, String profession, String location) {
		int index = findItem(name);
		if (index == -1) {
			return list.add(new HealthProfessional(name, profession, location));
		}
		else {
			System.out.println("name is already in list, failed to add");
			return false;
		}
	}
	
	/**
	 * checks if list is empty
	 * 
	 * @return true if empty
	 */
	public boolean isListEmpty()
	{
		return list.isEmpty();
	}
	
	/**
	 * get health professional name from list
	 * 
	 * @param index of the position in the list
	 * @return the name of the health professional
	 */
	public String getNameFromList(int index) {
		return list.get(index).getName();
	}
	
	/**
	 * gets the size of the list
	 * 
	 * @return the size of the list
	 */
	public int getListSize() {
		return list.size();
	}
	
	/**
	 * displays a each name in the list
	 */
	public void printList()
	{
		for(int i=0; i<list.size(); i++) {
        	System.out.println(list.get(i).getName());
        }
		
	}
	
	/**
	 * finds a specific name in the list
	 * 
	 * @param name the name to be searched
	 * @return position in list or -1 if not found
	 */
	public int findItem(String name) {
		if (isListEmpty() == true) {
			return -1;
		}
		else {
			boolean found = false;
			int index = 0;
			while (found==false && index <= (list.size()-1)) {
				if (list.get(index).getName().equals(name)) {
					found = true;
					break;
				}
				index++;
			}
			if (found == true) {
				return index;
			}
			else {
				return -1;
			}
		}
	}
	
	/**
	 * deletes item in the list
	 * 
	 * @param name of item to be deleted
	 */
	public void deleteItem(String name) {
		int index = findItem(name);
		if (index == -1) {
			System.out.println("The name of the Health Professional provided could not be found, Delete failed");
			return;
		}
		else {
			list.remove(index);
			System.out.println("Item Deleted");
			return;
		}		
	}
	
	/**
	 * allows user to edit a specific entry in the list
	 * 
	 * @param name - the name of the health professional to be edited
	 * @param newName - the new name of the health professional
	 * @param newProfession - the new profession of the health professional
	 * @param newLocation - the new location of the health professional
	 */
	public void editItem(String name, String newName, String newProfession, String newLocation) {
		int index = findItem(name);
		if (index == -1) {
			System.out.println("The name of the Health Professional provided could not be found, Edit failed");
			return;
		}
		else {
			list.remove(index);
			addToList(newName, newProfession, newLocation);
			
			File sourceFile = new File("data\\"+name+".txt");
			File destFile = new File("data\\"+newName+".txt");
			sourceFile.renameTo(destFile);
			
		}
		System.out.println("The Item has been updated");
		return;
	}
	
	
	/**
	 * reads list of health professionals from a file
	 */
	public void readListFromFile() {
		// initialise default values for readers
		FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        try
        {
        	// read file
            fileReader = new FileReader("listOfHelathProfrossionals.txt");
            bufferedReader = new BufferedReader(fileReader); 
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            // loop if there is text on the line
            while (nextLine != null)
            {
            	// add data to list
            	String[] parts = nextLine.split("/");
            	
            	addToList(parts[0], parts[1], parts[2]);
            	
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
	 * writes the list of health professionals to a file
	 */
	public void writeListToAFile()
    {
		// initialise default values for writers
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        // try
        try
        {
        	// create or overwrite file to be created
            outputStream = new FileOutputStream("listOfHelathProfrossionals.txt");
            printWriter = new PrintWriter(outputStream); 
            
            
            // add to file
            for(int i=0; i<list.size(); i++) {
            	printWriter.println(list.get(i).getName()+"/"+list.get(i).getProfession()+"/"+list.get(i).getLocation());
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
}
