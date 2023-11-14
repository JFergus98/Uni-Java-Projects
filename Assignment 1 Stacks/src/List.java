/**
 * creates a Linked list
 *
 * @author Jamie Fergus 190018054
 */
public class List
{
	// declare fields
    private ListNode top;
    
    /**
     * Constructor for objects of class List
     */
    public List()
    {
        top = null;
    }
    
    /**
     * Returns true if the list is empty
     */
    public boolean isListEmpty()
    {
    	return (top == null);
    }

     /**
     * Add a new node at the start of a list
     * 
     * @param number to add
     */
    public void addToList(int number)
    {
        ListNode  newOne;
        
        newOne = new ListNode(number);        
        newOne.setNext(top);
        
        top = newOne;
    }
 
	/**
     * Print the list, starting at top
     */
     public void printList()
     {
        ListNode marker = top;
        
        // prompt user if list is empty
        if (isListEmpty())
        {
        	System.out.println("The list is empty.");  
        	return;
        }
        
    	// print each item in list
        System.out.println("The list contains:");    
        while (marker != null)
        {
        	System.out.println(marker.getNumber());  
	        marker=marker.getNext();
        }
    }
    
     /**
      * delete first node
      * 
      * @return deleted node of top of list
      */
    public ListNode deleteFromStart() 
    {
    	ListNode nodeToDelete;
    	
    	// return null if list is empty
    	if (isListEmpty()) 
    	{
    		System.out.println("List is already empty");
    		return null;
    	}
    	
    	// return top node
    	else
    	{
    		nodeToDelete = top;
    		top = top.getNext();
    		
    		return nodeToDelete;
    	}
    }
}
