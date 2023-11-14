// import scanner
import java.util.Scanner;

/**
 * A Reverse Polish Notation calculator which uses a stack to push and pop numbers to be used in the calculations.
 * 
 * @author Jamie Fergus 190018054
 *
 */
public class RPNCalculator {
	
	// declare field
	private Stack rpn;
	
	/**
	 * default constructor
	 */
	public RPNCalculator()
	{
		// initialise rpn
		rpn = new Stack();
	}
	
	/**
	 *  prompts user to enter postfix notation calculation and delimiting factor then calculates and displays the answer.
	 */
	public void evaluatePostfix()
	{
		// initialise rpn
		rpn.initialiseStack();
		
		// local variables
		String input, delimit;
		String[] split;
		boolean isInt, isOp, retry;
		int intCount, int1, int2, int3, answer;
		
		// do until error handling is not triggered
		do
		{
			// initialise variables
			
			retry = false;
			intCount = 0;
			
			// do until valid Postfix notation length is entered
			do
			{
				input = getString("\nPlease input valid postfix string, separated with a delimiting character, (e.g '3,4,*'). \nOr enter 'Q' to quit.");
				
				// if quit is selected return to menu
				if (input.equals("q") || input.equals("Q"))
				{
					return;
				}
				delimit = getString("Please input the delimiting character used.");
				
				split = input.split(delimit); 
				
				if (split.length < 3)
				{
					System.out.println("Error - Invalid input, Postfix notation too short, must have at least 2 delimiting characters.");
				}
				
			} while (split.length < 3);
			
			// for each token
			for (int i=0; i < split.length; i++)
			{
				// check if token numerical or operator 
				isInt = checkInt(split[i]);
				isOp = checkOperator(split[i]);
				
				// if numerical push
				if (isInt == true)
				{
					rpn.push(Integer.parseInt(split[i]));
					intCount++;
				}
				// if operator and 2 numbers are on stack pop twice and apply operator and push
				else if (isOp == true && intCount >= 2)
				{
					int1 = rpn.pop().getNumber();
					intCount--;
					int2 = rpn.pop().getNumber();
					intCount--;
					
					if (split[i].equals("+"))
					{
						int3 = int1 + int2;
						rpn.push(int3);
						intCount++;
					}
					else if (split[i].equals("-"))
					{
						int3 = int1 - int2;
						rpn.push(int3);
						intCount++;
					}
					else if (split[i].equals("*"))
					{
						int3 = int1 * int2;
						rpn.push(int3);
						intCount++;
					}
					else if (split[i].equals("/"))
					{
						int3 = int1 / int2;
						rpn.push(int3);
						intCount++;
					}
					
				}
				// a series of error handling checking if correct Postfix notation is used
				else if (isInt == false &&  isOp == false)
				{
					System.out.println("Error - Invalid input, make sure only numerical data is entered separated with a delimiting character that is not an operator or a number. Also make sure delimiting factor is specified correctly.");
					retry = true;
					break;
				}
				else if (isInt == false && intCount < 2)
				{
					System.out.println("Error - Invalid input, wrong order of postfix notation or too many operators, make sure correct postfix notation is entered with the correct amount of numbers for the operators. (e.g '2,3,+' two numbers for 1 operator)");
					retry = true;
					break;
				}
				else {
					System.out.println("Error - Unknown error occured.");
					retry = true;
					break;
				}
			}
			// more error handling
			if (intCount > 1)
			{
				retry = true;
				System.out.println("Error - Invalid input, too many numbers, make sure correct postfix notation is entered with the correct amount of numbers for the operators. (e.g '2,3,+' two numbers for 1 operator)");
			}
			
		} while (retry == true);
		
		// pop and display answer
		answer = rpn.pop().getNumber();
		System.out.println("\nAnswer = " + answer);
	}
	
	/**
     * Uses Scanner to get a new String from the user
     */
    public String getString(String userPrompt)
    {
    	Scanner s = new Scanner(System.in);
		System.out.print(userPrompt);
		while (!s.hasNext())
		{
			s.next();
			System.out.print(userPrompt);
		}
		
		return s.next();
    }
    
    /**
     * check if string contains a numeric value
     * 
     * @param s String containing split from user input
     * @return boolean for numeric true or false
     */
    public boolean checkInt(String s)
    {
    	try 
        { 
            // checking valid integer using parseInt() method 
            Integer.parseInt(s);
            return true;
        }  
    	
        catch (NumberFormatException e)  
        { 
            return false;
        } 
    }
    
    /**
     * check if string contains an operator value (+,-,*,/)
     * 
     * @param s String containing split from user input
     * @return boolean for operator true or false
     */
    public boolean checkOperator(String s)
    {
    	// if operator return true
    	if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
    	{
            return true;
        }  
    	
    	// else return false
    	else
        { 
            return false;
        } 
    }
}
