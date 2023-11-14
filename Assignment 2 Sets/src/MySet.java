// import required set classes
import java.util.Set;
import java.util.HashSet;

/**
 * creates a wrapper around set class for java's built in set class
 * 
 * @author Jamie Fergus 190018054 
 */
public class MySet 
{
	//declare field
	private Set<Integer> set;
	
	/**
	 * Default constructor
	 */
	public MySet()
	{
		//initialise field
		set = new HashSet<Integer>();
	}
	
	/**
	 * alternative constructor
	 * 
	 * @param set - a set used to construct MySet
	 */
	public MySet(Set<Integer> set)
	{
		this.set = new HashSet<Integer>(set);
	}
	
	/**
	 * adds an integer to the set if it is not already present.
	 * @param value - int to be added to set
	 * @return a boolean be false if the Integer already exists in the Set
	 */
	public boolean addToSet(int value)
	{
		return set.add(value);
	}
	
	/**
	 * check if the set is empty
	 * 
	 * @return true if set is empty
	 */
	public boolean isSetEmpty()
	{
		return set.isEmpty();
	}
	
	/**
	 * calculates the cardinality of the set
	 * 
	 * @return an integer containing the cardinality
	 */
	public int getCardinality()
	{
		return set.size();
	}
	
	/**
	 * prints MySet
	 */
	public void printSet()
	{
		System.out.println(set);
	}
	
	/**
	 * used to get the set from MySet
	 * 
	 * @return the reference to the set
	 */
	public Set<Integer> getSet()
	{
		return set;
	}
	
	/**
	 * works out the intersections of two sets
	 * 
	 * @param otherSet - another set which will be intersected with
	 * @return a new MySet containing only the intersections of the two sets
	 */
	public MySet intersection(MySet otherSet)
	{
		Set<Integer> setCopy = new HashSet<Integer>(set);
		setCopy.retainAll(otherSet.getSet());
		return new MySet(setCopy);
	}
	
	/**
	 * removes all items from the set
	 */
	public void clearSet()
	{
		set.clear();
	}
}
