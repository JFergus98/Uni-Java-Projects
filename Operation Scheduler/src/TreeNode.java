/**
 * creates a binary tree node
 * 
 * @author Jamie Fergus 190018054
 *
 */
public class TreeNode {
	// declare global variables
	private boolean isFull;
	private int startTime, endTime;
	private String treatmentType;
	private TreeNode left, right, parent;
	
	/**
	 * Default constructor
	 * 
	 * @param startTime - start time of the appointment
	 * @param endTime - end time of the appointment
	 */
	public TreeNode(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		isFull = false;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 * alternative constructor
	 * 
	 * @param startTime - start time of the appointment
	 * @param endTime - end time of the appointment
	 * @param parent - TreeNode parent of TreeNode
	 */
	public TreeNode(int startTime, int endTime, TreeNode parent) {
		this.startTime = startTime;
		this.endTime = endTime;
		isFull = false;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	/**
	 * Alternative constructor
	 * 
	 * @param startTime - start time of the appointment
	 * @param endTime - end time of the appointment
	 * @param treatmentType - the type of treatment for the patient
	 * @param parent - TreeNode parent of TreeNode
	 */
	public TreeNode(int startTime, int endTime, String treatmentType, boolean isFull, TreeNode parent) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.treatmentType = treatmentType;
		this.isFull = isFull;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	/**
	 * gets isFull
	 * 
	 * @return isFull
	 */
	public boolean getIsFull() {
		return isFull;
	}

	/**
	 * set isFull
	 * 
	 * @param isFull
	 */
	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}
	

	/**
	 * gets the startTime
	 * 
	 * @return the start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * set the startTime
	 * 
	 * @param start time
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * gets the endTime
	 * 
	 * @return the endtime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * set the endTime
	 * 
	 * @param end Time
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * gets the type of treatment

	 * 
	 * @return the treatment type
	 */
	public String getTreatmentType() {
		return treatmentType;
	}

	/**
	 * sets the type of treatment
	 * 
	 * @param the treatment type
	 */
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
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
