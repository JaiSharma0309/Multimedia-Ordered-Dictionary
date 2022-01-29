import java.util.ArrayList;

// This class will represent the items that will be stored in the internal nodes of the BST that implements the ordered dictionary.
public class NodeData {
	
	// Instance variable that serves as the name of the data in each node.
	private String name;
	
	// This list will hold the multimedia objects associated with each key in each node.
	private ArrayList<MultimediaItem> media;
	
	// Constructor that initializes name and creates an empty list called media.
	public NodeData (String newName) {
		
		name = newName;
		media = new ArrayList<MultimediaItem>();
		
	}
	
	// Method will add an item to the media list.
	public void add (MultimediaItem newItem) {
		
		media.add(newItem);
		
	}
	
	// Getter method that returns the name of the data stored in each node.
	String getName() {
		
		return name;
		
	}
	
	
	// Getter method that returns the media list.
	public ArrayList<MultimediaItem> getMedia() {
		
		return media;
	}
	

}
