// This class serves to represent the individual multimedia objects stored in the list of each node.
public class MultimediaItem {
	
	// Instance variable that represents the descriptor of type of multimedia content being stored.
	private String content;
	
	// Instance variable that represents the type of multimedia content being stored.
	private int type;
	
	// Constructor that creates an object of type MultimediaItem and initializes the instance variables with the specified parameters.
	public MultimediaItem(String newContent, int newType) {
		
		content = newContent;
		type = newType;
		
	}
	
	// Getter method that returns the content of multimedia stored in that node.
	public String getContent() {
		
		return content;
		
	}
	
	// Getter method that returns the type of multimedia stored in that node.
	public int getType() {
		
		return type;
		
	}
	

}
