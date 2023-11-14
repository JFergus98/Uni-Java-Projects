/**
 * creates a binary tree node
 * 
 * @author Jamie Fergus 190018054
 *
 */
public class TreeNode {
	// declare global variables
	private int itemID, stock;
	private String name;
	private float cost;
	private TreeNode left, right, parent;
	
	/**
	 * Default constructor
	 * 
	 * @param itemID integer index
	 */
	public TreeNode(int itemID) {
		this.itemID = itemID;
		this.stock = 1;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 * Alternative constructor
	 * 
	 * @param itemID - integer id of item
	 * @param name - String name of item
	 * @param cost - float cost of item
	 * @param parent - TreeNode parent of TreeNode
	 */
	public TreeNode(int itemID, String name, Float cost, TreeNode parent) {
		this.itemID = itemID;
		this.name = name;
		this.cost = cost;
		this.stock = 1;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	/**
	 * Alternative constructor
	 * 
	 * @param itemID - integer id of item
	 * @param name - String name of item
	 * @param cost - float cost of item
	 * @param stock - integer number of items in stock
	 * @param parent - TreeNode parent of TreeNode
	 */
	public TreeNode(int itemID, String name, Float cost, int stock, TreeNode parent) {
		this.itemID = itemID;
		this.name = name;
		this.cost = cost;
		this.stock = stock;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	/**
	 * adds 1 to stock
	 */
	public void increaseStock() {
		stock++;
	}
	/**
	 * Subtracts 1 from stock
	 */
	public void decreaseStock() {
		stock--;
	}
	
	/**
	 * gets the stock
	 * 
	 * @return integer - number of items in stock
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * set the stock
	 * 
	 * @param number of items in stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * gets the item ID
	 * 
	 * @return the item ID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * set the itemId
	 * 
	 * @param item ID
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	/**
	 * gets the name of item
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of item
	 * 
	 * @param the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the cost of item
	 * 
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}
	
	/**
	 * sets the cost of item
	 * 
	 * @param the cost
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**
	 * gets the nodes left node
	 * 
	 * @return the left node
	 */
	public TreeNode getLeft() {
		return left;
	}

	/**
	 * sets the nodes left node
	 * 
	 * @param the left node
	 */
	public void setLeft(TreeNode left) {
		this.left = left;
	}

	/**
	 * gets the nodes right node
	 * 
	 * @return the right node
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * sets the nodes right node
	 * 
	 * @param the right node
	 */
	public void setRight(TreeNode right) {
		this.right = right;
	}

	/**
	 * gets the nodes parent node
	 * 
	 * @return the parent node
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * sets the nodes parent node
	 * 
	 * @param the parent node
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
}
