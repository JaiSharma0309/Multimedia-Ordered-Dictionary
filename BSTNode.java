// This class will serve as the foundation of the BST implementing the ordered dictionary 
public class BSTNode {
	
	// Instance variable that will reference a parent of the node;
	private BSTNode parent;
	
	// Instance variable that will reference a left child of the node;
	private BSTNode leftChild;
	
	// Instance variable that will reference the right of a node
	private BSTNode rightChild;
	
	// Instance variable that will reference the data information stored in the node.
	private NodeData data;
	
	
	// Constructor that initializes the instance variables as null initially.
	public BSTNode() {

		parent = leftChild = rightChild = null;
		data = null;
		
	}
	
	// Second constructor that initializes the instance variables with the method parameters.
	public BSTNode(BSTNode newParent, BSTNode newLeftChild, BSTNode newRightChild, NodeData newData) {
		
		parent = newParent;
		leftChild = newLeftChild;
		rightChild = newRightChild;
		data = newData;
		
	}
	
	
	// Simple getter method that will return the parent of the node.
	public BSTNode getParent() {
		
		return parent;
		
	}
	
	// Simple getter method that will return the left child of the node.
	public BSTNode getLeftChild() {
		
		return leftChild;
		
	}
	
	// Simple getter method that will return the right child of the node.
	public BSTNode getRightChild() {
		
		return rightChild;
		
	}
	
	// Simple getter method that will return the data stored in the node.
	public NodeData getData() {
		
		return data;
		
	}
	
	// Simple setter method that will set the parent of this node as the value given.
	public void setParent (BSTNode newParent) {
		
		parent = newParent;
		
	}
	
	// Simple setter method that will set the left child of this node as the value given.
	public void setLeftChild (BSTNode newLeftChild) {
		
		leftChild = newLeftChild;
		
	}
	
	// Simple setter method that will set the right child of this node as the value given.
	public void setRightChild(BSTNode newRightChild) {
		
		rightChild = newRightChild;
		
	}
	
	// Simple setter method that will set the data of this node as the value given.
	public void setData(NodeData newData) {
		
		data = newData;
		
	}
	
	// Method that will check if the node is a leaf or not and return true if it is, and false otherwise.
	public boolean isLeaf() {
		
		if ((leftChild == null) && (rightChild == null)) {
			return true;
		} else {
			return false;
		}
		
	}

}





