/**
 * This Class creates and updates a 10x10 used by the gameInterface to create the battleship grids
 * 
 * @author Jamie Fergus
 *
 */
public class Grid {
	// initialise array to set grid box values
	private char[][] grid = new char[10][10];
	
	/**
	 * Default Constructor for grid
	 */
	public Grid() {
		// for each grid box set value to .
		for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                 grid[row][col] = '.';
            }
		}
	}

	/**
	 * This method displays the gird object in a readable format along with row numbers and column letters
	 */
	public void displayGrid() {
		// initialise variable
		String gap = "  ";
		
		// display column letters
		System.out.format("%5s%3s%3s%3s%3s%3s%3s%3s%3s%3s%n", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
		
		// For each row
		for (int row = 0; row < grid.length; row++) {
			// initialise variable
			String gridRow = "";
			// for each column in row
            for (int col = 0; col < grid[row].length; col++) {
            	// add grid box to row
            	gridRow += gap + grid[row][col];
            }
            // initialise value for row numbers
            int rowNum = row + 1;
            
            // display row
            System.out.format("%2d", rowNum);
            System.out.println(gridRow);
		}
	}
	
	/**
	 * this method updates a specific grid box with a hit or a miss value
	 * 
	 * @param row - integer used to define what row of grid box is to be edited
	 * @param col - integer used to define what column of grid box is to be edited
	 * @param hit - boolean to indicate if the shot was a hit or a miss
	 */
	public void updateGrid(int row, int col, boolean hit) {
		// If shot was a hit
		if (hit == true) {
			// update grid box with hit value
			grid[row][col] = 'X';
		}
		// else if shot was a miss
		else {
			// update grid box with miss value
			grid[row][col] = '#';
		}
	}

	/**
	 * this method is used to position the specific grid boxes with part of a ship
	 * 
	 * @param row - integer used to define what row of grid box is to be edited
	 * @param col - integer used to define what column of grid box is to be edited
	 * @param shipId - integer used to identify which ship is being placed
	 */
	public void updateShipGrid(int row, int col, int shipId) {
		// update grid box value with shipId
		grid[row][col] = String.valueOf(shipId).charAt(0);
	}

	/**
	 * this method is used to return the value of a specific grid box
	 * 
	 * @param row - integer used to define what row of grid box is to be edited
	 * @param col - integer used to define what column of grid box is to be edited
	 * @return grid[row][col] - returns the value of that specific grid box
	 */
	public char getGridValue(int row, int col) {
		return grid[row][col];
	}
	
	/**
	 * this method is used to count and return the number of grid boxes equal to the parameter c
	 * 
	 * @param c - character indicating what value of grid box is to be counted
	 * @return count - returns integer of the number grid boxes counted
	 */
	public int countGridValues(char c) {
		// initialise variable
		int count = 0;
		// for each grid box
		for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
            	// if grid box value = c then add 1 to count
                if (grid[row][col] == c) {
                	 count += 1;
                }
            }
		}
		return count;
	}
	
	/**
	 * method used to set specific value of a specific grid box to the parameter c
	 * 
	 * @param row - integer used to define what row of grid box is to be edited
	 * @param col - integer used to define what column of grid box is to be edited
	 * @param c - character indicating what value of grid box is to be set to
	 */
	public void setGridValue(int row, int col, char c) {
		grid[row][col] = c;
	}
}
