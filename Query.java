import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// This class allows the user to access the various functions implemented in BSTOrderedDictionary.
public class Query {
	
	// Main method that serves as input for user and tells the users if they inputted an incorrect input.
	public static void main(String[] args) throws Exception {		
		if (args.length != 1) {
			System.out.println("invalid number of arguments input.");
			System.out.println("Sample usage: java Query small.txt");
			
			return;
		}
		

		// Creating an object of type BSTOrderedDictionary.
		BSTOrderedDictionary dictionary = new BSTOrderedDictionary();
		
		// Getting the root.
		BSTNode root = dictionary.getRoot();
		
		// Making a list.
		ArrayList<MultimediaItem> list;
		NodeData data;
		
		// Passing the input file.
		BufferedReader in = new BufferedReader(new FileReader(args[0])); 
		
		// Reads one line of input from the keyboard which is returned as a string to the invoking method.
		String lineInput = in.readLine();
		String definition = in.readLine();
		
		
		if (lineInput != null) {
			
			// If it's an audio file
			if ( definition.endsWith(".wav") || definition.endsWith(".mid") ) {
				
				// Calling the put method from BSTOrderedDictionary and putting a node with 2 as the type.
				dictionary.put(root, lineInput, definition, 2);

				
				// If it's an image file.
			} else if ( definition.endsWith(".jpg") || definition.endsWith(".gif") ) {
				
				// Calling the put method from BSTOrderedDictionary and putting a node with 3 as the type.
				dictionary.put(root, lineInput, definition, 3);
				
				
				// If it's an html file.
			} else if ( definition.endsWith(".html")) {
				
				// Calling the put method from BSTOrderedDictionary and putting a node with 4 as the type.
				dictionary.put(root, lineInput, definition, 4);
				
				// If it's just text.
			} else {
				
				// Calling the put method from BSTOrderedDictionary and putting a node with 1 as the type.
				dictionary.put(root, lineInput, definition, 1);

			}
			
			root = dictionary.getRoot();
			
			//Reading the input.
			lineInput = in.readLine();
		}
		while (lineInput != null) {
			try {
				
				
				definition = in.readLine();
				
				
				// If it's an audio file it's calling the put method from BSTOrderedDictionary and putting a node with 2 as the type.
				if ( definition.endsWith(".wav") || definition.endsWith(".mid") ) {
					dictionary.put(root, lineInput, definition, 2);
			
					
					// If it's an image file it's calling the put method from BSTOrderedDictionary and putting a node with 3 as the type.	
				} else if ( definition.endsWith(".jpg") || definition.endsWith(".gif") ) {
					dictionary.put(root, lineInput, definition, 3);

					
					// If it's an html file it's calling the put method from BSTOrderedDictionary and putting a node with 4 as the type.
				} else if ( definition.endsWith(".html")) {
					dictionary.put(root, lineInput, definition, 4);
					//System.out.println("******* FOUND AN HTML file right here, its name is: " + definition);
				
					// If it's text it's calling the put method from BSTOrderedDictionary and putting a node with 1 as the type.
				} else {
					dictionary.put(root, lineInput, definition, 1);
			
				}
				
				// Reading input.
				lineInput = in.readLine();
				

			} catch (Exception e) {
				
				// Catching any exceptions that may be caused.
				System.out.println("An exception is thrown of type : " + e);
			}
		}
		


		StringReader keyboard = new StringReader();
		String line = read(dictionary, root);

		// While user has not typed "end".
		while (!line.equals("end")) {
			
				// Continue gather input and reading.
				line = read(dictionary, root);
		
		}
				
	}
	
	// Private helper method that helps with displaying the various media types.
	private static void playMedia(BSTOrderedDictionary dictionary, BSTNode root, String key) throws Exception {
		
		try {
			
			// Going through the list.
			ArrayList<MultimediaItem>list = dictionary.get(root, key);
			for (int i = 0; i < list.size(); i++) {

				// Getting the type.
				int type = list.get(i).getType();
				
				// If it is just text.
				if (type == 1) {
					
					// Printing out content.
					System.out.println(list.get(i).getContent());

					// If the type is an audio file.
				} else if (type == 2) {
					
					File filename = new File(list.get(i).getContent());
					if ( filename.exists() == true) {
						
						// Using the soundplayer class to play audio.
						SoundPlayer player = new SoundPlayer();
					    player.play(list.get(i).getContent());
					    
					} else {
						
						// If the named files cannot be found or possessed a multimedia exception is thrown.
						throw new MultimediaException("");
					}
					
				    // If the type is an image file.
				} else if (type == 3) {
					
					File filename = new File(list.get(i).getContent());
					if ( filename.exists() == true) {
						
						// Using the pictureviewer class to display gifs or images.
						PictureViewer player = new PictureViewer();
					    player.show(list.get(i).getContent());
					    
					} else {
						
						// If the named files cannot be found or possessed a multimedia exception is thrown.
						throw new MultimediaException("");
					}
					
				    
					// If the type is an html file.
				} else if (type == 4) {
					
					File filename = new File(list.get(i).getContent());
					if ( filename.exists() == true) {
						
						// Using the showHtml class to display an HTML web page.
						ShowHTML player = new ShowHTML();
					    player.show(list.get(i).getContent());
					    
					} else {
						
						// If the named files cannot be found or possessed a multimedia exception is thrown.
						throw new MultimediaException("");
					}
				}
				
			} 
			return; 
			
		} catch (MultimediaException e) {
			System.out.println("There was a multimedia exception thrown and we were not able to access this multimedia file");
		} catch (NullPointerException e) {
			System.out.println("The word " + key + " is not in the ordered dictionary.");
			
			
		}
		
		
		// To help with various methods defining some Strings.
		String previous="", next ="";
		
		try {
			// Calling the previous helper method for the prev command.
			previous = helperPrevious(dictionary, root, key, 1, false);
			
		} catch (NullPointerException e) {
			previous = "";
		}
		
		try {
			//Calling the next helper method for the next command.
			next = helperNext(dictionary, root, key, 1, false);
		} catch (NullPointerException e) {
			next = "";
		}

		// Formatting output.
		System.out.println("Preceding word:  " + previous);
		System.out.println("Following word:  " + next);
	}
	
	
	// This read method will help read the various commands and print out the correct output.
	private static String read (BSTOrderedDictionary dictionary, BSTNode root) throws Exception {
		
		// Reading input and prompting user.
		StringReader keyboard = new StringReader();
		String line = keyboard.read("Enter next command: ");
		
		// If users hits enter without typing anything it will reprompt the user.
		if (line.equals("")) {
			return line = "";
		}
		
		
		// Using StringTokenizer to access different words of the input.
		StringTokenizer tokens = new StringTokenizer(line);
		
		// Way to access the input's first word.
		String firstWord = tokens.nextToken();

		
		// get command.
		if ( firstWord.equals("get")) {
			
			// Getting the second word and calling the playMedia method which deals with playing the various types of media.
			String secondWord = tokens.nextToken();
			
			playMedia(dictionary, root, secondWord);
			
			
			// remove command.
		} else if (firstWord.equals("remove")) {
			
			String secondWord="";
			try {
				
				// Getting the second word and calling the remove method on it from BSTOrderedDictionary to remove the node holding that word from the tree
				secondWord = tokens.nextToken();
				
				dictionary.remove(root, secondWord);
			
				
			} catch (DictionaryException e) {
				
				// If that node is not in the tree.
				System.out.println("No record in the ordered dictionary has key " +  secondWord + ".");
			}
			
			// delete command.
		} else if (firstWord.equals("delete")) {
			
			String secondWord="";
			
			try {
				
				// Getting the second word and calling the remove method that deals with deleting multimedia objects.
				secondWord = tokens.nextToken();
				dictionary.remove(root,secondWord,Integer.parseInt(tokens.nextToken()));
				
			} catch (Exception e) {
				
				// If that node is not in the tree.
				System.out.println("No record in the ordered dictionary has key " + secondWord  + ".");
			}
			
			
			// add command.
		} else if (firstWord.equals("add")) {
			
			try {
				
				// Calling the add method based on the various words from the input, and adding that node into the tree.
				dictionary.put(root, tokens.nextToken(), tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
				
			} catch( Exception e) {
				
				// If user input is not valid.
				System.out.println("invalid command argument");
			}
			
			
			// next command.
		} else if (firstWord.equals("next")) {
			String key = tokens.nextToken();
			
			try {		
				
				// Calling the next helper method that deals with the next command.
				helperNext(dictionary, root, key , Integer.parseInt(tokens.nextToken()), true);
				
			} catch(Exception e) {
				
				// If the input asks for the next keys of a key that is too large within the scope of the tree.
				System.out.println("There are no keys larger than or equal to " + key);
			}
			
			
			// prev command.
		} else if (firstWord.equals("prev")) {
			String key = tokens.nextToken();
			
			try {	
				
				// Calling the previous helper method that deals with the previous command.
				helperPrevious(dictionary, root, key , Integer.parseInt(tokens.nextToken()), true);
				
			} catch(Exception e) {
				
				// If the input asks for the previous keys of a key that is too small within the scope of the tree.
				System.out.println("There are no keys smaller than or equal to " + key);
			}
			
			// first command.
		} else if (firstWord.equals("first")) {
			
			// if empty return a string notifying user.
			if (dictionary.getNumInternalNodes() == 0) {
				System.out.println("The ordered dictionary is empty.");
				
				// Calling smallest function on given parameters to get the output of the first command.
			} else {
				System.out.println(dictionary.smallest(root).getName());
			}
			
			
			// last command.
		} else if (firstWord.equals("last")) {
			
			// if empty return a string notifying user.
			if (dictionary.getNumInternalNodes() == 0) {
				System.out.println("The ordered dictionary is empty.");
				
			} else {
				// Calling largest function on given parameters to get the output of the last command.
				System.out.println(dictionary.largest(root).getName());
			}
			
			// size command.
		} else if (firstWord.equals("size")) {
			
			// Calling the getNumInternalNodes method to get the size of the tree.
			System.out.println(dictionary.getNumInternalNodes());
			
			
			// end command.
		} else if (firstWord.equals("end")) {
			
			// Will return a string notifying the end of the program.
			return "end of program";
			
			// If user types in any other command it would be invalid since everything has been accounted for.
		} else {
			
			// Notifying user that they have typed an invalid command.
			System.out.println("Invalid command");
		}
		
		
		return line;
		
	}
	
	
	// Helper method that deals with the various conditions of the next command.
	private static String helperNext(BSTOrderedDictionary dictionary, BSTNode root, String key, int moveAmount, boolean flag) throws DictionaryException {
		String s = "";

		// Getting successor of data.
		NodeData n = dictionary.successor(root, key);
		
		// if successor exists.
		if ( n != null ) {
			
			// Printing name of key.
			System.out.print(key + " " + n.getName() );
			
			int i = 1;
			
			while (i < moveAmount) {
				
				// Getting successors.
				NodeData next = dictionary.successor(root, n.getName());
				
				// if successor does not exist.
				if ( next == null)
					break;
				System.out.print(" " + next.getName());
				n = next;
				i++;
			}	
			System.out.println();
			
			
			// If node does not exist in the tree.
		} else {
			
			// Insert this node into the tree.
			dictionary.put(root, key, "", moveAmount);
			NodeData node = dictionary.successor(root, key);
			
			// If within conditions print the name.
			if (flag == true)
				System.out.print(node.getName());
			
			int i = 1;
			while (i < moveAmount) {
				node = dictionary.successor(root,node.getName());
				
				// if node is null it means the successor does not exist.
				if (node == null)
					break;
				System.out.print(" " + node.getName() );
				i++;
			}
			if (flag == true)
				System.out.println();
			
			// Since we added the node into the tree we have to remove it as well, because we just want to display the successors.
			s = node.getName();
			dictionary.remove(root, key);
		}
		
		return s;
	}
	
	// Helper method that deals with the various conditions of the prev command.
	private static String helperPrevious(BSTOrderedDictionary dictionary, BSTNode root, String key, int moveAmount, boolean flag ) throws DictionaryException {
		String s = "";
		
		// Getting predecessor of data.
		NodeData n = dictionary.predecessor(root, key);
		
		// if predecessor exists.
		if ( n != null ) {
			
			// Printing name of key.
			System.out.print(key + " " + n.getName() );
		
			int i = 1;
			while (i < moveAmount) {
				
				// Getting successors.
				NodeData prev = dictionary.predecessor(root, n.getName());
				
				// if Predecessor does not exist.
				if ( prev == null)
					break;
				System.out.print(" " + prev.getName());
				n = prev;
				i++;
			}	
			System.out.println();
			
			// If node does not exist in the tree.
		} else {
			
			
			// Insert this node into the tree
			dictionary.put(root, key, "", moveAmount);
			NodeData node = dictionary.predecessor(root, key);
			
			// If within conditions print the name.
			if (flag == true)
				System.out.print(node.getName());
			int i = 1;
			while (i < moveAmount) {
				
				node = dictionary.predecessor(root,node.getName());
				
				// if node is null it means the predecessor does not exist.
				if (node == null)
					break;
				System.out.print(" " + node.getName() );
				i++;
			}
			
			if (flag == true)
				System.out.println();
			s = node.getName();
			
			// Since we added the node into the tree we have to remove it as well, because we just want to display the predecessors.
			dictionary.remove(root, key);
		}
		return s;
	}

	
}
