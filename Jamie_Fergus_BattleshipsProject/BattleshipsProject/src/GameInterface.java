// Import io classes
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is where the game plays out. This class runs through an entire game of battleships and loaded games of battleships. 
 * This class also allow the user to save the current state of the game and also return to the menu
 * 
 * @author Jamie
 */
public class GameInterface {
	
	/**
	 * Declare fields for GameInterface class
	 */
	private Grid player1Grid;
	private Grid player1ShipGrid;
	private Grid player2Grid;
	private Grid player2ShipGrid;
	private Grid computerGrid;
	private Grid computerShipGrid;
	
	/**
	 * initialise global variables
	 */
	private final int[] shipLength = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};
	private final String[] shipName = {"Battleship", "1st Cruiser", "2nd Cruiser", "1st Destroyer", "2nd Destroyer", "3rd Destroyer", "1st Submarine", "2nd Submarine", "3rd Submarine", "4th Submarine"};
	private boolean pvp;
	
	/**
	 * Default constructor initialising fields to their default value
	 */
	public GameInterface() {
		player1Grid = new Grid();
		player1ShipGrid = new Grid();
		player2Grid = new Grid();
		player2ShipGrid = new Grid();
		computerGrid = new Grid();
		computerShipGrid = new Grid();
	}
	
	/**
	 * This method starts a game, choosing which game mode and if it is a loaded game from input parameters.
	 * 
	 * @param pvp - boolean used to see which game mode was selected
	 * @param loadGame - boolean used to see if game needs loaded
	 */
	public void startGame(boolean pvp, boolean loadGame) {
		// if pvp == false
		if (pvp == false) {
			// run player vs computer game
			playerVsComputer(loadGame);
		}
		// if pvp == true
		else if (pvp == true) {
			// run player vs player game
			playerVsPlayer(loadGame);
		}
	}
	
	/**
	 * this method is used to load a previously saved game, by reading save game data from a text file
	 */
	public void loadGame() {
		// initialise default values for readers
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        // initialise variable for file name
        String fileName = "save.txt";
        
        // call checkIfFileExists() method to check if file exists and is readable
     	boolean checkFile = Menu.checkIfFileExists(fileName);
     	
     	// if file does not exist
     	if (checkFile == false) {
     		// display error and return to main menu
         	System.out.println("Error - Save file not found");
         	System.out.println("Returning to Main Menu");
         	return;
     	}
     	
        try
        {
        	// read file
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        
            // read game mode from 1st line
            pvp = Boolean.parseBoolean(bufferedReader.readLine());
            
            // create array of grids
            Grid grid[] = new Grid[6];
            
            grid[0] = player1Grid;
            grid[1] = player1ShipGrid;
            grid[2] = player2Grid;
            grid[3] = player2ShipGrid;
            grid[4] = computerGrid;
            grid[5] = computerShipGrid;
            
            // read each grid 
            for (int i=0; i<grid.length; i++) {
            	for (int row = 0; row < 10; row++) {
                    for (int col = 0; col < 10; col++) {
                    	grid[i].setGridValue(row,col, bufferedReader.readLine().charAt(0));
                    }
        		}
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
		
        // call start game method with loadGame parameter true
        startGame(pvp, true);
	}

	/**
	 * A method that runs a player vs computer version of battleship, either stating a new game or loading a previous game
	 * Player and computer takes turns independently until 1 sides ships are all destroyed or until player quits the game
	 * 
	 * @param loadGame - boolean used to see if game is being loaded or starting new game.
	 */
	public void playerVsComputer(boolean loadGame){
		// Initialise variables
		boolean gameOver = false;
		boolean player1Win = false;
		boolean quit = false;
		pvp = false;
				
		// if starting new game		
		if (loadGame == false) {
			// Tell players to place Ships
			System.out.println("Please place ships.");
						
			// Player place ships
			playerPlaceShips(player1ShipGrid);
					
			// Computer place ships
			computerPlaceShips();
		}
				
		// Do until game over
		do {
			// prompt player 1 to start there turn
			System.out.print("\n" + "It is now your turn press any key to continue.");
					
			// press any key to continue
			Menu.anyKeyToContinue();
					
			// Players turn
			quit = playerTurn(player1Grid, player1ShipGrid, computerShipGrid, "Computer", computerGrid.countGridValues('X'), computerGrid.countGridValues('#'));
				
			// if quit game option selected return to main menu
			if (quit == true) {
				return;
			}
			
			// if all computer ships are destroyed gameOver = true, player1Wins = true and end loop
			if (player1Grid.countGridValues('X') == 30) {
				gameOver = true;
				player1Win = true;
				break;
			}		
					
			// Computers turn
			System.out.println();
			System.out.println("Computers turn");
			computerTakeShot();
					
			// if all player ships are destroyed gameOver = true and player1Wins = false	
			if (gameOver == false && computerGrid.countGridValues('X') == 30) {
				gameOver = true;
				player1Win = false;
			}
					
					
		// End loop
		} while (gameOver == false);
		
		// if player wins display win message
		if (gameOver == true && player1Win == true) {
			System.out.println("Congratulations! You Win!");
		}
		// if computer wins display lose message
		else if (gameOver == true && player1Win == false) {
			System.out.println("Gameover!, You Lose!");
		}
		// display returning to menu
		System.out.println("Returning to menu.");		
	}
	
	/**
	 * A method that runs a player vs player version of battleship, either stating a new game or loading a previous game.
	 * Each player takes turns independently until 1 players ships are all destroyed or until 1 player quits the game
	 * 
	 * @param loadGame - boolean used to see if game is being loaded or starting new game.
	 */
	public void playerVsPlayer(boolean loadGame){
		// Initialise variables
		boolean gameOver = false;
		boolean player1Win = false;
		boolean quit = false;
		pvp = true;
		
		// if starting new game	
		if (loadGame == false) {
			// Tell player 1 to place Ships
			System.out.println("Player 1 please place ships.");
			// Player 1 place ships
			playerPlaceShips(player1ShipGrid);
			// Tell player 2 to place Ships
			System.out.println("Player 2 please place ships.");
			// Player 2 place ships
			playerPlaceShips(player2ShipGrid);
		}
		
		// Do until game over
		do {
			// prompt player 1 to start there turn
			System.out.print("\n" + "Player 1, it is now your turn press any key to continue.");
			
			// press any key to continue
			Menu.anyKeyToContinue();
			
			// Player 1 turn
			quit = playerTurn(player1Grid, player1ShipGrid, player2ShipGrid, "player 2", player2Grid.countGridValues('X'), player2Grid.countGridValues('#'));
			
			// if quit game option selected return to main menu
			if (quit == true) {
				return;
			}
			
			// if all player 2 ships are destroyed gameOver = true, player1Wins = true and end loop	
			if (player1Grid.countGridValues('X') == 30) {
				gameOver = true;
				player1Win = true;
				break;
			}	
			
			// prompt player 1 to start there turn
			System.out.print("\n" + "Player 2, it is now your turn press any key to continue.");
						
			// press any key to continue
			Menu.anyKeyToContinue();

			// Player 2 turn
			playerTurn(player2Grid, player2ShipGrid, player1ShipGrid, "player 2", player2Grid.countGridValues('X'), player2Grid.countGridValues('#'));
			
			// if quit game option selected return to main menu
			if (quit == true) {
				return;
			}
			
			// if all player 1 ships are destroyed gameOver = true and player1Wins = false
			if (gameOver == false && player2Grid.countGridValues('X') == 30) {
				gameOver = true;
				player1Win = false;
			}
			
		// End loop
		} while (gameOver == false);
		
		// if player 1 wins, display win message
		if (gameOver == true && player1Win == true) {
			System.out.println("Player 1 Wins!");
		}
		// if player 2 wins, display win message
		else if (gameOver == true && player1Win == false) {
			System.out.println("Player 2 Wins!");
		}
		// display returning to menu
		System.out.println("Returning to menu.");
	}
	
	/**
	 * this method is for either players turn. It allows the player the choice to fire on the grid or access an options menu
	 * 
	 * @param grid - this turns players grid
	 * @param shipGrid - this turns players ship grid
	 * @param opponentShipGrid - the opponents ship grid
	 * @param opponent - String holding the opponents name(player2 or computer)
	 * @param oppHits - integer holding the opponents number of hits
	 * @param oppMisses - integer holding the opponents number of misses
	 * @return - a boolean to see if quit game option has been selected
	 */
	public boolean playerTurn(Grid grid, Grid shipGrid, Grid opponentShipGrid, String opponent, int oppHits, int oppMisses) {
		// A local variable to hold a choice entered by the user
		int choice;
		
		// Allows the program to keep running until the option 1 is selected
		do {
			// Prompt user for an integer by calling the checkInt() method
			choice = Menu.checkInt("Enter 1 to fire upon the enemy or 2 to see further options.");
			
			// if invalid choice prompt user to try again
			if (choice != 1 && choice != 2) {
				System.out.println("Please enter only 1 or 2.");
			}
			
			// if choice 2 selected call optionsMenu() method
			if (choice == 2) {
				boolean quitGame = optionsMenu(shipGrid, opponentShipGrid, opponent, oppHits, oppMisses);
				// if quit game selected return true
				if (quitGame == true) {
					return true;
				}
			}	
		} while (choice != 1);
		
		// player takes shot
		playerTakeShot(grid, opponentShipGrid);
		
		// return false for quitGame
		return false;
	}

	/**
	 * This method gives the players a choice of options. to return to game, display scoreboard, save their game, show their ships, show enemy ships.
	 * 
	 * @param playerShipGrid - this turns players ship grid
	 * @param opponentShipGrid - the opponents ship grid
	 * @param opponent - String holding the opponents name(player2 or computer)
	 * @param oppHits - integer holding the opponents number of hits
	 * @param oppMisses - integer holding the opponents number of misses
	 * @return - a boolean to see if quit game option has been selected
	 */
	public boolean optionsMenu(Grid playerShipGrid, Grid opponentShipGrid, String opponent, int oppHits, int oppMisses) {
		int c = 0;
		
		// Allows the program to keep running until the return to game option is selected
		do {
			// display options
			System.out.println("0. Return to game.");
			System.out.println("1. Display Scoreboard.");
			System.out.println("2. Save Game.");
			System.out.println("3. Display your ship positions.");
			System.out.println("4. Show enemy ship positions(for testing purposes).");
			System.out.println("5. Return to Main Menu.");
			
			// Call method to check user input is an integer
			c = Menu.checkInt("Please select one of the options above, by entering one of the numbers listed.");
				
			// case selected by the users choice
			switch (c) {
				case 0:
					// return to game
					break;
				case 1:
					// Display scoreboard
					System.out.format("%8s%7s%9s%n", "Player", "hits", "misses");
					System.out.format("%8s%7d%9d%n", "Player_1", player1Grid.countGridValues('X'), player1Grid.countGridValues('#'));
					System.out.format("%8s%7d%9d%n", opponent, oppHits, oppMisses);
					break;
				case 2:
					// Save the current state of the game
					saveGame();
					break;
				case 3:
					// Display player ship grid
					playerShipGrid.displayGrid();
					break;
				case 4:
					// Display opponent ship grid(for testing purposes)
					opponentShipGrid.displayGrid();
					break;
				case 5:
					// initialise variable for choice of options
					int leave = 0;
					// do while choice is not 2
					do {
						// Call method to check user input is an integer
						leave = Menu.checkInt("Are you sure you wish to leave the game, all unsaved progress will be lost. Press 1 to leave or press 2 to cancel.");
						// case selected by the users choice
						switch (leave) {
							case 1:
								// quit game
								return true;
							case 2:
								// cancel
								break;
							default:
								// display error message
								System.out.println("Sorry, your choice wasn’t recognised, please try again.");
								break;
						}
					}
					// end loop
					while(leave != 2);
					break;
				 default:		
				 	// display error message
				 	System.out.println("Sorry, your choice wasn’t recognised, please try again.");
				 	break;
			}
		}
		// end loop
		while(c != 0);
		// return to game
		return false;
	}
	
	/**
	 * this method saves the current state of the game in a text file that can be loaded from the main menu
	 */
	private void saveGame() {
		// initialise default values for writers
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
		try {
			// open file to be read too
			outputStream = new FileOutputStream("save.txt");
	        printWriter = new PrintWriter(outputStream); 
	         
	         // add game mode to file
	         printWriter.println(pvp);
	         
	         // add each grid to file
	         writeGrid(player1Grid, printWriter);
	         writeGrid(player1ShipGrid, printWriter);
	         writeGrid(player2Grid, printWriter);
	         writeGrid(player2ShipGrid, printWriter);
	         writeGrid(computerGrid, printWriter);
	         writeGrid(computerShipGrid, printWriter);
		}
		// error handling for read and write
        catch (IOException e)
        {
        	// display error message
            System.out.println("IO Error in write to file: " + e);
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
	 * this method is used to write values of a grid Object into a file
	 * 
	 * @param grid - the grid that is being written into the file
	 * @param printWriter - the PrintWriter used to write to file
	 */
	public void writeGrid(Grid grid, PrintWriter printWriter) {
		// for each grid box
		for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
            	// write to file
           	 	printWriter.println(grid.getGridValue(row, col));
            }
        }
	}
	
	/**
	 * this method allow a player to choose the grid position the wish to fire at
	 * 
	 * @param playerGrid - the players grid
	 * @param opponentShipGrid - the opponents ship grid
	 */
	public void playerTakeShot(Grid playerGrid, Grid opponentShipGrid) {
		// Declare local variables
		int row, col;
		char colLetter;
		
		// do loop until new coordinate is entered
		do {
			// do loop until a letter from A-J is entered
			do {
				// Prompt user for a character by calling the checkChar() method
				colLetter = Menu.checkChar("Please enter a column leter from A-J");
				// convert character to integer
				col = colLetter - 'a';
			} while (col < 0 || col > 9);
			
			// do loop until integer form 1-10 is entered
			do {
				// Prompt user for an integer by calling the checkInt() method
				row = Menu.checkInt("Please enter a row number from 1-10") - 1;
			} while (row < 0 || row > 9);
			
			// If player has all ready fired on grid box entered entered, display message to try again
			if (playerGrid.getGridValue(row, col) == '#' || playerGrid.getGridValue(row, col) == 'X') {
				System.out.println("You have already fired on this coordinate, please enter a new coordinate.");
			}
		} while (playerGrid.getGridValue(row, col) == '#' || playerGrid.getGridValue(row, col) == 'X');
		
		// fire on grid
		takeshot(row, col, playerGrid, opponentShipGrid);
		
		// Display players grid
		playerGrid.displayGrid();
	}
	
	/**
	 * this method generates a random grid position for the computer to fire at
	 */
	public void computerTakeShot() {
		// Declare local variables
		int row, col;
		
		// do loop until empty coordinate is generated 
		do {
			// get random coordinates
			row = getRandomNumber(0, 9);
			col = getRandomNumber(0, 9);	
		} while (computerGrid.getGridValue(row, col) == '#' || computerGrid.getGridValue(row, col) == 'X');
		
		// take shot on player grid
		takeshot(row, col, computerGrid, player1ShipGrid);
	}

	/**
	 * this method is used to determine if a player or computers shot was a hit or a miss
	 * 
	 * @param row - integer for the row coordinate
	 * @param col - integer for the column coordinate
	 * @param grid - the grid of the player or computer that fired the shot
	 * @param opponentShipGrid - the ship grid being fired on
	 */
	public void takeshot(int row, int col, Grid grid, Grid opponentShipGrid) {
		// declare variables
		boolean hit;
		
		// if shot hit then, hit = true and display hit message
		if (opponentShipGrid.getGridValue(row, col) != '.' && opponentShipGrid.getGridValue(row, col) != '#' && opponentShipGrid.getGridValue(row, col) != 'X') {
			hit = true;
			System.out.println("Hit!");
		}
		// if shot missed then, hit = false and display miss message
		else {
			hit = false;
			System.out.println("Miss!");
		}
		// Update player grid
		grid.updateGrid(row, col, hit);
		// Update computer ship grid
		opponentShipGrid.updateGrid(row, col, hit);
	}
	
	/**
	 * this method allows a player to choose if they want to place ships randomly or manually
	 * 
	 * @param playerShipGrid - the ship grid of the player
	 */
	public void playerPlaceShips(Grid playerShipGrid) {
		// declare variable
		int choice;
		// do loop until integer form 1 or 2 is entered
		do {
			// Prompt user for an choice by calling the checkInt() method
			choice = Menu.checkInt("If you would like to manually choose the positions of your fleet press 1." + "\n" + "Or if you would rather randomally generate the fleet positions press 2.");
		} while (choice < 0 || choice > 2);
		
		// if choice 1 then, player manually place ships
		if (choice == 1) {
			playerPlaceShipsManually(playerShipGrid);
		}
		// if choice 2 then, randomly place ships
		else if (choice == 2) {
			placeShipsRandomly(playerShipGrid);
		}
	}
	
	/**
	 * this method allows a player to choose the positions of their ships on the grid
	 * 
	 * @param playerShipGrid - the ship grid of the player
	 */
	public void playerPlaceShipsManually(Grid playerShipGrid) {
		// Declare local variables
		boolean shipCanBePlaced;
		int row, col, direction;
		char colLetter;
		
		// For each ship
		for (int eachShip = 0; eachShip < shipLength.length; eachShip++) {
			// Do
			do {
				// set shipCanBePlaced to true
				shipCanBePlaced = true;
				// do loop until new coordinate is entered
				do {
					// do loop until a letter from A-J is entered
					do {
						// Prompt user for a character by calling the checkChar() method
						colLetter = Menu.checkChar("Please enter a column leter from A-J, for the starting position of your " + shipName[eachShip] + ", length " + shipLength[eachShip] + ".");
						// convert character to integer
						col = colLetter - 'a';
					} while (col < 0 || col > 9);
					
					// do loop until integer form 1-10 is entered
					do {
						// Prompt user for an integer by calling the checkInt() method
						row = Menu.checkInt("Please enter a row number from 1-10, for the starting position of your " + shipName[eachShip] + ", length " + shipLength[eachShip] + ".") - 1;
					} while (row < 0 || row > 9);
					// If player has all ready fired on grid box entered entered, display message to try again
					if (playerShipGrid.getGridValue(row, col) != '.') {
						System.out.println("There is already a ship on this coordinate, please try again.");
					}
				} while (playerShipGrid.getGridValue(row, col) != '.');
				
				// if grid box is empty
				if (playerShipGrid.getGridValue(row, col) == '.') {
					// do loop until integer form 1-4 is entered
					do {
						// Prompt user for an integer by calling the checkInt() method
						direction = Menu.checkInt("Please choose a direction to place your ship along: 1-up 2-right 3-down 4-left") - 1;
					} while (direction < 0 || direction > 9);
					
					// call placeShips() method
					shipCanBePlaced = placeShips(shipCanBePlaced, row, col, eachShip, direction, playerShipGrid);
					
					// if ships where placed display grid and message
					if (shipCanBePlaced == true) {
						playerShipGrid.displayGrid();
						System.out.println("Ship " + eachShip + " has been succesfully placed.");
					}
					// else, prompt user to try again
					else {
						System.out.println("The direction choosen already had a ship on that path or is out of bounds please try again.");
					}
				}
				// if grid box is not empty
				else {
					// set shipsCanBePlaced to false
					shipCanBePlaced = false;
				}
				
			// while shipsCanBePlaced is false
			} while (shipCanBePlaced == false);
		}
	}
	
	/**
	 * this method randomly places ships for the computer ship grid
	 */
	public void computerPlaceShips() {
		placeShipsRandomly(computerShipGrid);
	}
	
	/**
	 * this method randomly places the ships on the grid for a player or for the computer 
	 * 
	 * @param shipGrid - the ship grid to place ships on
	 */
	public void placeShipsRandomly(Grid shipGrid) {
		// Declare local variables
		boolean shipCanBePlaced;
		int row, col, direction;
		
		// For each ship
		for (int eachShip = 0; eachShip < shipLength.length; eachShip++) {
			// Do
			do {
				// set shipCanBePlaced to true
				shipCanBePlaced = true;
				
				// set row and columns to random values from 0-9
				row = getRandomNumber(0, 9);
				col = getRandomNumber(0, 9);
				
				// if grid box is empty
				if (shipGrid.getGridValue(row, col) == '.') {
					
					// set direction to a random value for 0-3
					direction = getRandomNumber(0, 3);
					
					// call placeShips() method
					shipCanBePlaced = placeShips(shipCanBePlaced, row, col, eachShip, direction, shipGrid);
				}
				// if grid box is not empty
				else {
					// set shipsCanBePlaced to false
					shipCanBePlaced = false;
				}
				
			// while shipsCanBePlaced is false
			} while (shipCanBePlaced == false);
		}
	}
	
	/**
	 * this method is used to check if a ship can be place and if it can it place the ship on the grid
	 * 
	 * @param shipCanBePlaced - boolean to store if a ship can be placed 
	 * @param row - integer for the row coordinate of a ship starting position
	 * @param col - integer for the column coordinate of a ship starting position
	 * @param eachShip - integer holding the index number of the ship to be placed
	 * @param direction - integer holding the a number to signify the direction a ship will be drawn onto the grid
	 * @param shipGrid - the grid that the ship will be placed on
	 * @return - boolean if ship was placed or not
	 */
	public boolean placeShips(boolean shipCanBePlaced, int row, int col, int eachShip, int direction, Grid shipGrid) {
		// Declare local variables
		int rowCheck, colCheck, rowDif = 0, colDif = 0;
		
		// set row and column check equal to row and column
		rowCheck = row;
		colCheck = col;
		
		// if direction = 0 (up)
		if (direction == 0) {
			// set row difference to -1 column difference to 0
			rowDif = -1;
			colDif = 0;
		}
		// if direction = 1 (right)
		else if (direction == 1) {
			// set row difference to 0 column difference to 1
			rowDif = 0;
			colDif = 1;
		}
		// if direction = 2 (down)
		else if (direction == 2) {
			// set row difference to 1 column difference to 0
			rowDif = 1;
			colDif = 0;
		}
		// if direction = 3 (left)
		else if (direction == 3) {
			// set row difference to 0 column difference to -1
			rowDif = 0;
			colDif = -1;
		}
		
		// for each grid box ship will fill
		for (int i = 1; i < shipLength[eachShip]; i++) {
			// move to next grid box in direction
			rowCheck += rowDif;
			colCheck += colDif;
			
			// check if the grid box is not out of bounds or if grid box is empty
			if (rowCheck < 0 || rowCheck > 9 || colCheck < 0 || colCheck > 9 || shipGrid.getGridValue(rowCheck, colCheck) != '.') {
				// set shipsCanBePlaced to false
				shipCanBePlaced = false;
			}
		}
		// if shipsCanBePlaced is true
		if (shipCanBePlaced == true) {
			// fill first grid box
			shipGrid.updateShipGrid(row, col, eachShip);
			
			// for ship length
			for (int i = 1; i < shipLength[eachShip]; i++) {
				// move to next grid box in direction
				row += rowDif;
				col += colDif;
				
				// fill current grid box
				shipGrid.updateShipGrid(row, col, eachShip);
			}
		}
		return shipCanBePlaced;
	}
	
	/**
	 * this method returns a random integer from a range from parameters
	 * 
	 * @param min - integer holding the minimum value that could be returned
	 * @param max - integer holding the maximum value that could be returned
	 * @return - integer of result
	 */
	public static int getRandomNumber(int min, int max){
		// get random number
	    int randomInt = (int)(Math.random()*((max-min)+1))+min;
	    // return random number
	    return randomInt;
	}

	
}
