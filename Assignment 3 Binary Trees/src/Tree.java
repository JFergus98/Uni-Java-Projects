// import libraries
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * creates a binary tree
 *
 * @author Jamie Fergus 190018054
 */
public class Tree {
	// declare field
	private TreeNode root;
	
	
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
	 * writes the tree to file
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
            outputStream = new FileOutputStream("shop.txt");
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
			line = line + current.getItemID() +"/"+ current.getName() +"/"+ current.getCost() +"/"+ current.getStock() + "\n";
			
			line = readFromTree(current.getLeft(), line);
			
			line = readFromTree(current.getRight(), line);
		}
		return line;
	}
	
	/**
	 * reads a binary tree from a file
	 */
	public void readFromFile() {
		// initialise default values for readers
		FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        try
        {
        	// read file
            fileReader = new FileReader("shop.txt");
            bufferedReader = new BufferedReader(fileReader); 
            
            // read 1st line
            String nextLine = bufferedReader.readLine();
            
            // loop if there is text on the line
            while (nextLine != null)
            {
            	// add data to node
            	String[] parts = nextLine.split("/");
            	setRoot(writeToTree(root, null, Integer.parseInt(parts[0]), parts[1], Float.parseFloat(parts[2]), Integer.parseInt(parts[3])));
            	
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
	 * @param itemID - items id
	 * @param name - name of item
	 * @param cost - cost of item
	 * @param stock - stock of item
	 * @return node - current node
	 */
	public TreeNode writeToTree(TreeNode current, TreeNode previous, int itemID, String name, float cost, int stock) {
		if (current == null) {
			current = new TreeNode(itemID, name, cost, stock, previous);
		}
		
		else if (itemID < current.getItemID()) {
			current.setLeft(writeToTree(current.getLeft(), current, itemID, name, cost, stock));
		}
		
		else if (itemID > current.getItemID()) {
			current.setRight(writeToTree(current.getRight(), current, itemID, name, cost, stock));
		}
		return current;
	}
	
	/**
	 * removes node from tree or lowers the stcok
	 * 
	 * @param toDelete - the current node
	 * @param previous - the previous node
	 * @param itemID - the item id of the node to be deleted
	 * @return
	 */
	public TreeNode deleteFromTree(TreeNode toDelete, TreeNode previous, int itemID) {
		if (toDelete == null) {
			System.out.println("The itemID entered was not found");
		}
		
		else if (itemID < toDelete.getItemID()) {
			deleteFromTree(toDelete.getLeft(), toDelete, itemID);
		}
		
		else if (itemID > toDelete.getItemID()) {
			deleteFromTree(toDelete.getRight(), toDelete, itemID);
		}
		
		else if (itemID == toDelete.getItemID()) {
			if (toDelete.getStock() > 1) {
				toDelete.decreaseStock();
				System.out.println("Stock decreased.");
			}
			else {
				if (toDelete.getLeft() == null && toDelete.getRight() == null){
					if (previous == null) {
						toDelete = null;
					}
					else if (previous.getLeft() == toDelete) {
						previous.setLeft(null);
					}
					else {
						previous.setRight(null);
					}
				}
				
				else if (toDelete.getLeft() != null && toDelete.getRight() == null) {
					if (previous == null) {
						toDelete = toDelete.getLeft();
					}
					else if (previous.getLeft().getItemID() == toDelete.getItemID()) {
						previous.setLeft(toDelete.getLeft());
					}
					else {
						previous.setRight(toDelete.getLeft());
					}
				}
				
				else if (toDelete.getLeft() == null && toDelete.getRight() != null) {
					if (previous == null) {
						toDelete = toDelete.getRight();
					}
					else if (previous.getLeft().getItemID() == toDelete.getItemID()) {
						previous.setLeft(toDelete.getRight());
					}
					else {
						previous.setRight(toDelete.getRight());
					}
				}
				
				else {
					
					TreeNode current = toDelete.getLeft();
					previous = null;
					
					if (current.getRight() == null) {
						toDelete.setItemID(current.getItemID());
						toDelete.setLeft(current.getLeft());
					}
					
					while (current.getRight() != null) {
						previous = current;
						current = current.getRight();
					}
					if (current.getRight() == null) {
						toDelete.setItemID(current.getItemID());
						toDelete.setName(current.getName());
						toDelete.setCost(current.getCost());
						toDelete.setStock(current.getStock());
						
						if (previous != null) {
							previous.setRight(current.getLeft());
						}
						else {
							toDelete.setLeft(current.getLeft());
						}
					}
				}
				System.out.println("The item has been removed.");
			}
			return toDelete;
		}
		return toDelete;
	}
	
	/**
	 * calculates the total cost of items
	 * 
	 * @param current - current node
	 * @param total - int containing total cost
	 * 
	 * @return the total cost
	 */
	public float calculateTotalCost(TreeNode current, float total) {
		if (current != null) {
			total = calculateTotalCost(current.getLeft(), total);
			
			total = total + current.getCost();
			
			total = calculateTotalCost(current.getRight(), total);
		}
		else {
			return total;
		}
		
		return total;
	}
	
	/**
	 * calculates the total cost of stock
	 * 
	 * @param current - the current node
	 * @param total - int containing total cost of stock
	 * 
	 * @return the total cost of stock
	 */
	public float calculateTotalStockCost(TreeNode current, float total) {
		if (current != null) {
			total = calculateTotalCost(current.getLeft(), total);
			
			total = total + current.getCost() * current.getStock();
			
			total = calculateTotalCost(current.getRight(), total);
		}
		else {
			return total;
		}
		
		return total;
	}
	
	/**
	 * finds the node with the item id entered, prints the nodes data
	 * 
	 * @param current - the current node being checked
	 * @param itemID - the item id being searched
	 */
	public void findInTree(TreeNode current, int itemID) {
		if (current == null) {
			System.out.println("The itemID entered was not found");
		}
		
		else if (itemID < current.getItemID()) {
			findInTree(current.getLeft(), itemID);
		}
		
		else if (itemID > current.getItemID()) {
			findInTree(current.getRight(), itemID);
		}
		
		else if (itemID == current.getItemID()) {
			System.out.format("%8s%10s%10s%10s%n", "ItemID", "Name", "Cost(£)", "Stock");
			System.out.format("%8d%10s%10.2f%10d%n", current.getItemID(), current.getName(), current.getCost(), current.getStock());
			return;
		}
		return;
	}
	
	/**
	 * prints the tree
	 * 
	 * @param value - int value used to determine which traversal will be used 
	 */
	public void printTree (int value) {
		if (isTreeEmpty() == true) {
			System.out.println("Shop is empty.");
		}
		else {
			System.out.format("%8s%10s%10s%10s%n", "ItemID", "Name", "Cost(£)", "Stock");
			if (value == 1) {
				printTreeInorder(getRoot());
			}
			else if (value == 2) {
				printTreePreorder(getRoot());
			}
			else if (value == 3){
				printTreePostorder(getRoot());
			}
		}
		
	}
	
	/** 
	 * recursive method used to print the tree inorder
	 * 
	 * @param current - the current node being processed
	 */
	public void printTreeInorder(TreeNode current) {
		if (current != null) {
			printTreeInorder(current.getLeft());
			
			System.out.format("%8d%10s%10.2f%10d%n", current.getItemID(), current.getName(), current.getCost(), current.getStock());
			
			printTreeInorder(current.getRight());
		}
		else {
			return;
		}
	}
	
	/** 
	 * recursive method used to print the tree preorder
	 * 
	 * @param current - the current node being processed
	 */
	public void printTreePreorder(TreeNode current) {
		if (current != null) {
			System.out.format("%8d%10s%10.2f%10d%n", current.getItemID(), current.getName(), current.getCost(), current.getStock());
			
			printTreePreorder(current.getLeft());
			
			printTreePreorder(current.getRight());
		}
		else {
			return;
		}
	}
	
	/** 
	 * recursive method used to print the tree postorder
	 * 
	 * @param current - the current node being processed
	 */
	public void printTreePostorder(TreeNode current) {
		if (current != null) {
			printTreePostorder(current.getLeft());
			
			printTreePostorder(current.getRight());
			
			System.out.format("%8d%10s%10.2f%10d%n", current.getItemID(), current.getName(), current.getCost(), current.getStock());
		}
		else {
			return;
		}
	}
	
	/**
	 * recursive method adds allows user to add new node to the tree
	 * 
	 * @param current - the current node being processed
	 * @param previous - the previous node
	 * @param itemID - the id of the item being added
	 * @return the current node
	 */
	public TreeNode addToTree(TreeNode current, TreeNode previous, int itemID) {
		if (current == null) {
			String name = getString("Please enter the items name : ");
			float cost = checkFloat("Please enter the items cost : £ ");
			
			current = new TreeNode(itemID, name, cost, previous);
			
			System.out.println("Item added to shop.");
		}
		
		else if (itemID < current.getItemID()) {
			current.setLeft(addToTree(current.getLeft(), current, itemID));
		}
		
		else if (itemID > current.getItemID()) {
			current.setRight(addToTree(current.getRight(), current, itemID));
		}
		
		else if (itemID == current.getItemID()) {
			current.increaseStock();
			System.out.println("Stock increased.");
			return current;
		}
		return current;
	}
	
	/**
	 * input validation for float
	 * 
	 * @param userPrompt - contains prompt for user
	 * @return
	 */
	public float checkFloat(String userPrompt) {
		
		// prompt user for input
		Scanner s = new Scanner(System.in);
		System.out.println(userPrompt);
			
		// while invalid float entered
		while (!s.hasNextFloat())
		{
			// prompt user again
			s.next();
			System.out.println("Error - Please enter only a number. " + userPrompt);
		}
		float validFloat = s.nextFloat();
		return validFloat;	
	}
	
	/**
	 * input validation for String
	 * 
	 * @param userPrompt - contains prompt for user
	 * @return
	 */
	public String getString(String userPrompt) {
		
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
