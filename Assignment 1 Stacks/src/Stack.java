/**
 * Creates a Stack using the list class
 * 
 * @author Jamie Fergus 190018054
 */
public class Stack 
{
	// declare field
	private List aStack;
	
	/**
	 * initialise new stack
	 */
	public void initialiseStack()
	{
		aStack = new List();
	}
	
	/**
	 * pushes number onto top of stack
	 * 
	 * @param number to be pushed
	 */
	public void push(int number)
	{
		aStack.addToList(number);
	}
	
	/**
	 * pops of top of stack
	 * 
	 * @return node popped of top of stack
	 */
	public ListNode pop()
	{
		return aStack.deleteFromStart();
	}
	
	/**
	 * checks to see if stack is empty
	 * 
	 * @return true if stack is empty
	 */
	public boolean isStackEmpty()
	{
		return aStack.isListEmpty();
	}
	
	/**
	 * prints the entire stack starting from the top
	 */
	public void printStack()
	{
		aStack.printList();
	}
}
