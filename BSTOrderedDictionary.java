import java.util.ArrayList;

// This class will implement the BST that uses the ordered dictionary.
public class BSTOrderedDictionary implements BSTOrderedDictionaryADT {
	
	// Variable that serves as the root of the tree.
	private BSTNode root;
	
	// Variable that gets the number of internal nodes of the tree.
	private int numInternalNodes;
	
	// Constructor that sets the root to null, making it a leaf and makes the number of internal nodes 0, creating an empty BSTOrderedDictionary.
	public BSTOrderedDictionary() {
		root = null;
		numInternalNodes = 0;
	}
	
	// Getter method that returns the root of the tree.
	public BSTNode getRoot() {
		return root;
	}
	
	// Simple getter method that returns the number of internal nodes of the tree.
	public int getNumInternalNodes() {
		return numInternalNodes;
	}
	
	
	// Returns the list of mulitmedia objects stored in the specified key of the tree.
	public ArrayList<MultimediaItem> get (BSTNode r, String key) {
		
		// Creating a temporary list to iterate through
		ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
		
		// Doing an inorder of the tree so it will go through the tree in order.
		inorder(r, tempList);
	
		// Iterating through the entire tree and checking if a node in the tree stores the given key.
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getData().getName().equals(key)) {
				
				// Returning the list of multimedia objects if the node was found.
				return tempList.get(i).getData().getMedia();
			} 
		}
		
		
		return null;
		
	}
	
	// Will add the information stored in the parameters of the method into the tree.
	public void put (BSTNode r, String keyInput, String content, int type) {
		String key = keyInput.toLowerCase();
		
		
		// Makes a new MultimediaItem based on the value of content and type.
		MultimediaItem itemMedia = new MultimediaItem(content, type);
		
		//Makes another object of NodeData that contains key.
		NodeData dataNode = new NodeData(key);
		
		//Deals with adding the item to the arrayList inside NodeData.
		dataNode.add(itemMedia);
		BSTNode nodeBST = new BSTNode(null, null, null, dataNode);
		
		// If the tree is empty.
		if (root == null) {
			numInternalNodes = 1;
			root = nodeBST;
			
		} else {
			
			// If the tree is not empty.
			while(true) {
				BSTNode parent = r;
				
				// If the given key is less than the node it's being compared to, the tree will be traversed on the left, according to the properties of a BST.
				if (key.compareTo(r.getData().getName()) < 0) {				
					
					r = r.getLeftChild();
					if ( r == null) {
						
						//Inserts the new node right here.
						parent.setLeftChild(nodeBST);
						nodeBST.setParent(parent);
						numInternalNodes++;
						break;
					}
				}
				// If the given key is greater than the node it's being compared to, the tree will be traversed on the right, according to the properties of a BST.
				else if (key.compareTo(r.getData().getName()) > 0) {
					
					r = r.getRightChild();
					if ( r == null) {
						
						//Inserts the new node right here.
						parent.setRightChild(nodeBST);
						nodeBST.setParent(parent);
						numInternalNodes++;
						//insert the new node right here too
						break;
					}
				}	
				else {
					// If trying to insert a key that is already there it will add a new multimedia object storing the given content and type.
					MultimediaItem duplicateItem = new MultimediaItem(content, type);
					r.getData().getMedia().add(duplicateItem);
					break;
				}
			
			}
			
			
			
			
		}
		
	}
	
	// Removes a node from the binary search tree.  Method throws an exception if no node stores the given key.
	public void remove (BSTNode r, String key) throws DictionaryException {
		
		//Traversing through the tree to see if the key is stored in the tree.
		ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
		inorder(root, tempList);
		
		if ( get(r, key) == null ) {
			throw new DictionaryException("invalid input key, this key + <" + key + "> is not in the tree dictionary");
		}
		
		// Have to decrement number of internal nodes when removing a node.
		numInternalNodes--;
		r = searchNode(r, key);
		
		
		
		if (!r.getData().getName().equals(key)) {
			
			// Remove a node based on 3 conditions.  Condition 1 - if the node being removed is a leaf.  Modify the removing node's (r) parent to replace r with null as its child.
		} else if ((r.getRightChild() == null) && (r.getLeftChild() == null)) {
			
			
			
			BSTNode parent = r.getParent();
			
			// If the node being removed is a left child it will set the parent of the node being removed to make its left child null.
			if (r == r.getParent().getLeftChild()) {
				parent.setLeftChild(null);
				r = null;
				
				// If the node being removed is a right child it will set the parent of the node being removed to make its right child null.
			} else if (r == r.getParent().getRightChild()) {
				parent.setRightChild(null);
				r = null;
			}
			
			
			
			// Condition 2 - If the node being removed is an internal node with one child.  Elevate r's child to take r's position in the tree and have r's parent point to r's child.
			// Checking if that one child is the right child or left child, starting with right child.
		} else if ((r.getLeftChild() == null) && (r.getRightChild() != null)) {
				
				// If r is a right child.
				if (r == r.getParent().getRightChild()) {
					
					// Set the parent of r to have it's right child be the child of r.
					r.getParent().setRightChild(r.getRightChild());
					
					// Doing it the other way to establish link.
					r.getRightChild().setParent(r.getParent());
					
					// Setting r to null ensuring it has been deleted.
					r = null;
					
					// if r is a left child.
				} else if (r == r.getParent().getLeftChild()) {
					
					// Set the parent of r to have it's left child be the child of r.
					r.getParent().setLeftChild(r.getRightChild());
					
					// Doing it the other way to establish link.
					r.getRightChild().setParent(r.getParent());
					
					// Setting r to null ensuring it has been deleted.
					r = null;
				}
				
				
				// Continuing condition 2 but this time r has a left child instead of right.
		} else if ((r.getLeftChild() != null) && (r.getRightChild() == null)) {
				
				
				// If r is a left child.
				if (r == r.getParent().getLeftChild()) {
				
					// Set the parent of r to have it's left child be the child of r.
					r.getParent().setLeftChild(r.getLeftChild());
					
					// Doing it the other way to establish link.
					r.getLeftChild().setParent(r.getParent());
					
					// Setting r to null ensuring it has been deleted.
					r = null;
					
					// If r is a right child.
				} else if (r == r.getParent().getRightChild()) {
					
					// Set the parent of r to have it's right child be the child of r.
					r.getParent().setRightChild(r.getLeftChild());
					
					// Doing it the other way to establish link.
					r.getLeftChild().setParent(r.getParent());
					
					// Setting r to null ensuring it has been deleted.
					r = null;
				}

				
			
			
			
		// Condition 3 - If the node being removed is an internal node with 2 children.  Replace r with it's successor and fix the links accordingly.
		} else if ((r.getRightChild() != null) && (r.getLeftChild() != null)) {
			
			// Creating a new node thats the successor of the one being removed by calling the successor function created.
			BSTNode successor = bstNodeSuccessor(r, key);
			

			
			// Ensuring that successor is a leaf node.
			if ((successor.getLeftChild() == null) && (successor.getRightChild() == null)) {
				
			
				// If the node being removed is the root.
				if (r == root) {
					
					
					// Will set the data in r to be the successor's data, doing a clean swap.
					r.setData(successor.getData());
					
					// Having a way to access the successor's parent.
					BSTNode successorParent = successor.getParent();
					
					// Setting the successor's parent's left child to null.
					successorParent.setLeftChild(null);
					
					// Since we swapped successor and the root's contents, setting successor to null ensuring it has been deleted.
					successor = null;
					
					
				// If r is a left child and r's left child of it's left child is a leaf.
			    } else if ((r == r.getParent().getLeftChild()) && (r.getLeftChild().getLeftChild() == null)) {
					
					
			    	// Setting r's parent's left child to be the successor of r.
					r.getParent().setLeftChild(successor);
					
					// Setting the successor's left child to be r's left child.
					successor.setLeftChild(r.getLeftChild());
					
					// Putting the successor in place of r.
					r.getLeftChild().setParent(successor);
					
					// Making sure r is deleted.
					r = null;
					
					
					// If r a is a right child and r's right child of it's right child is a leaf.
				} else if ((r == r.getParent().getRightChild()) && (r.getRightChild().getRightChild() == null)) {
					
					// Setting r's parent's right child to be the successor of r.
					r.getParent().setRightChild(successor);
					
					// Setting the successor's right child to be r's right child.
					successor.setLeftChild(r.getLeftChild());
					
					// Putting the successor in place of r.
					r.getLeftChild().setParent(successor);
					
					// Making sure r is deleted.
					r = null;
					
					// If the successor is not a direct right child of the node being deleted.
				} else if ((successor != r.getRightChild()) && (successor.isLeaf() == true)) {
					
					
					BSTNode successorParent = successor.getParent();
					
					// if r is a left child.
					if (r == r.getParent().getLeftChild()) {
						
						// Setting r's parent's left child to be the successor of r.
						r.getParent().setLeftChild(successor);
						
						// Setting successor's right child to be r's right child.
						successor.setRightChild(r.getRightChild());
						
						// Setting successor's left child to be r's left child.
						successor.setLeftChild(r.getLeftChild());
						
						// Putting the successor in place of r. 
						r.getRightChild().setParent(successor);
						
						// Making sure r is deleted.
						r = null;
						
						// Making sure successor is no longer where it used to be but is now in place of r.
						successorParent.setLeftChild(null);
						
						
						// If r is a right child.
					} else if (r == r.getParent().getRightChild()) {
						
						// Setting r's parent's right child to be the successor of r.
						r.getParent().setRightChild(successor);
						
						// Setting successor's right child to be r's right child.
						successor.setRightChild(r.getRightChild());
						
						// Setting successor's left child to be r's left child.
						successor.setLeftChild(r.getLeftChild());
						
						// Putting the successor in place of r. 
						r.getRightChild().setParent(successor);
						
						// Making sure r is deleted.
						r = null;
						
						// Making sure successor is no longer where it used to be but is now in place of r.
						successorParent.setLeftChild(null);
					}
					
					
				} 
				
				// If successor's right child is not null.
			} else if ((successor.getLeftChild() == null) && (successor.getRightChild() != null)) {
				
				
				// If deleting a root node.
				if (r == root) {
					
					
					// Swapping the data stored in r and in successor.
					r.setData(successor.getData());
					
					
					BSTNode successorParent = successor.getParent();
					
					// Setting successor's children to have successor's parent as a parent instead of successor.
					successor.getRightChild().setParent(successorParent);
					successorParent.setLeftChild(successor.getRightChild());
					
					
					// Making sure successor is no longer where it used to be but is now in place of r.
					successor = null;
	
					
					// if r is not a root.
			    } else if (r != root) {
			    	
			    	// Swapping the data stored in r and in successor.
					r.setData(successor.getData());
					
					BSTNode successorParent = successor.getParent();
					
					// Setting successor's children to have successor's parent as a parent instead of successor.
					successor.getRightChild().setParent(successorParent);
					
					// Setting successor's children to have successor's parent as a parent instead of successor.
					successorParent.setLeftChild(successor.getRightChild());
					
					// Making sure successor is no longer where it used to be but is now in place of r.
					successor = null;
					
			    }
	
			}
			

		}
		
		
		
	}
	
	
	// Private method that helps in iterating the tree.
	private BSTNode searchNode (BSTNode r, String key) {
		
		
		while( r != null) {
			
			// If the given key is less than the node it's being compared to, the tree will be traversed on the left, according to the properties of a BST.
			if ( key.compareTo(r.getData().getName()) < 0 ) { 
				r = r.getLeftChild();
				
				// If the given key is greater than the node it's being compared to, the tree will be traversed on the right according to the properties of a BST.
			} else if (key.compareTo(r.getData().getName()) > 0) {
				r = r.getRightChild();
				
			} else {
				// If it's the same just return r.
				return r;
			}
		}
		return null;
	}
	
	
	// Removes the multimedia objects within each node.
	public void remove (BSTNode r, String key, int type) throws DictionaryException {
		
		// Creating a temp list and running the get function on it.
		ArrayList<MultimediaItem> tempList = new ArrayList<MultimediaItem>();
		tempList = get(r, key);
		
		// Throws an exception if null.
		if ( tempList == null ) {
			throw new DictionaryException("invalid input key, this key + <" + key + "> is not in the tree dictionary");
			
		} else {
			
			int startSize = tempList.size();
			
			// iterating through the temp list and removing the multimedia object.
			try {
				for (int i = 0; i < startSize; i++) {			
					if (type == tempList.get(i).getType()) {
						tempList.remove(i);
						i = i - 1;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				
			}
		}
	
		// If the list has no more multimedia objects it will delete the node.
		if ( tempList.size() == 0) {
			remove(r, key);
			
		}		
	}
	
	
	// Returns the node object that stores the smallest key.
	public NodeData smallest(BSTNode r) {
		
		// Returns null if the tree is empty.
		if (r == null) {
			return null;
			
		} else {
			ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
			
			// Doing an inorder traversal of the tree to search for the smallest node.
			inorder(r, tempList);
			
			// Smallest node will be the first element in an inorder traversal, so by returning the list at index 0 we have returned the smallest.
			return (tempList.get(0).getData());
		}
		
	}
	
	// Returns the node object that stores the largest key.
	public NodeData largest(BSTNode r) {
		
		// Returns null if the tree is empty.
		if (r == null) {
			return null;
		} else {
			ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
			
			// Doing an inorder traversal of the tree to search for the largest node.
			inorder(r, tempList);
			int largest = tempList.size() - 1;
			
			// Smallest node will be the first element in an inorder traversal, so by returning the list at it's last index we have returned the largest.
			return tempList.get(largest).getData();
		}
	}
	
	// Returns the successor of the node storing the key attribute key.
	public NodeData successor (BSTNode r, String key) {
		ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
		
		// Traversing the tree.
		inorder(r, tempList);
		
		
		for (int i = 0; i < tempList.size(); i++) {
			
			
			try {
				
				// When node has been found
				if (tempList.get(i).getData().getName().equals(key)) {
					
					// Return the data stored at the next index, because successor is next largest.
					return tempList.get(i + 1).getData();
					
				} 
			} catch(IndexOutOfBoundsException e) {
				
				// Return null if successor does not exist.
				return null;
			}
			
			
		}
		
		return null;
		
	}
	
	// Helper method that helps with getting the successor.  Need a helper method because of differing types (BSTNode and NodeData).
	private BSTNode bstNodeSuccessor (BSTNode r, String key) {
		ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
		
		// Traversing the tree.
		inorder(root, tempList);
		
		for (int i = 0; i < tempList.size(); i++) {
			
			
			try {
				
				// When node has been found
				if (tempList.get(i).getData().getName().equals(key)) {
					
					// Return what's stored at the next index, because successor is next largest.
					return tempList.get(i + 1);
				} 
			} catch(IndexOutOfBoundsException e) {
				return null;
			}
			
			
		}
		
		return null;
		
	}

	// Returns the node data object stored in the predecessor of the node storing key attribute key.
	public NodeData predecessor (BSTNode r, String key) {
		
		// Creating a temp list.
		ArrayList<BSTNode> tempList = new ArrayList<BSTNode>();
		
		// Traversing the tree.
		inorder(r, tempList);
		
		for (int i = 0; i < tempList.size(); i++) {
			
			try {
				
				// When node has been found
				if (tempList.get(i).getData().getName().equals(key)) {
					
					// Return the data stored at the previous index, because predecessor is largest element that is smaller.
					return tempList.get(i - 1).getData();
					
				} 
			} catch(IndexOutOfBoundsException e) {
				
				// Return null if predecessor does not exist.
				return null;
			}
			
			
		}
		
		return null;
	}
	

	// Private recursive helper method that will use inorder to traverse and add to the tree.
	private void inorder (BSTNode node, ArrayList<BSTNode> tempList) {
		
		if (node != null) {
			
			// Do inorder on the left child.
			inorder (node.getLeftChild(), tempList);
			
			// Add node here.
			tempList.add(node);
			
			// Do inorder on the right child.
			inorder(node.getRightChild(), tempList);
		}
	}
	


}
