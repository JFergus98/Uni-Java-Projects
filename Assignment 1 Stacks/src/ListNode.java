
/**
 * Performs actions on the list's nodes
 * 
 * @author Jamie Fergus 190018054
 */
public class ListNode    
{
    // declare variables
    private int number;
    private ListNode next;

    /**
     * Constructor for objects of class List
     */
    public ListNode(int number)
    {
        // initialise instance variables
        this.number = number;
        this.next = null;
    }
    
     /**
     * Get the next node
     * 
     * @return the next node
     */
    public ListNode getNext()
    {
        return next;
    }
    
     /**
     * Get the number
	 *
     * @return number at this node
     */
    public int getNumber()
    {
        return number;
    }
  
     /**
     * Set the next node
     * 
     * @param  the node to be added at this node's next
     */
    public void setNext(ListNode next)
    {
        this.next = next;
    }
}
