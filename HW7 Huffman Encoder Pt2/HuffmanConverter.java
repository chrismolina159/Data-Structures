import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HuffmanConverter {

	// The # of chars in the ASCII table dictates
	// the size of the count[] & code[] arrays.
	public static final int NUMBER_OF_CHARACTERS = 256;
	
	// the contents of our message...
	private String contents;
	
	// the tree created from the msg
	private HuffmanTree huffmanTree;
	
	// tracks how often each character occurs
	private int count[];
	
	// the huffman code for each character
	private String code[];
	
	// stores the # of unique chars in contents
	private int uniqueChars = 0; //(optional)
	
	/** Constructor taking input String to be converted */
	
	public HuffmanConverter(String input){
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}
	/**
	* Records the frequencies that each character of our
	* message occurs...
	* I.e., we use 'contents' to fill up the count[] list...
	*/
	public void recordFrequencies() {
		for(int i = 0; i < contents.length();i++){
			this.count[(int)contents.charAt(i)]++;
		}
		count[(int)'\n']++;
		//System.out.println((char)count[84]);
		frequenciesToTree();
	}
	/**
	* Converts our frequency list into a Huffman Tree. We do this by
	* taking our count[] list of frequencies, and creating a binary
	* heap in a manner similar to how a heap was made in HuffmanTree's
	* fileToHeap method. Then, we print the heap, and make a call to
	* HuffmanTree.heapToTree() method to get our much desired
	* HuffmanTree object, which we store as huffmanTree.
	*/
	public void frequenciesToTree() {
		ArrayList<HuffmanNode> arr = new ArrayList<HuffmanNode>();
		
		for(int i = 0; i < count.length;i++){
			if(count[i] == 0);
			else {
				//System.out.print("Testing: "+(char)i+",");
				HuffmanNode temp = new HuffmanNode(""+(char)i,count[i]+0.0);
				arr.add(temp);
			}
		}
//			Need to cast an arrayList into an array
		HuffmanNode[] arr2 = new HuffmanNode[arr.size()];
		for (int i = 0; i < arr.size();i++){
			arr2[i] = arr.get(i);
		}
			
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(arr2);
//		System.out.println();
		heap.printHeap();
		huffmanTree = HuffmanTree.createFromHeap(heap);
		System.out.println();
		treeToCode();
	}
		
	/**
	* Iterates over the huffmanTree to get the code for each letter.
	* The code for letter i gets stored as code[i]... This method
	* behaves similarly to HuffmanTree's printLegend() method...
	* Warning: Don't forget to initialize each code[i] to ""
	* BEFORE calling the recursive version of treeToCode...
	*/
	public void treeToCode() {
		treeToCode(huffmanTree.root,"");
	}
	/*
	* A private method to iterate over a HuffmanNode t using s, which
	* contains what we know of the HuffmanCode up to node t. This is
	* called by treeToCode(), and resembles the recursive printLegend
	* method in the HuffmanTree class. Note that when t is a leaf node,
	* t's letter tells us which index i to access in code[], and tells
	* us what to set code[i] to...
	*/
	private void treeToCode(HuffmanNode t, String s) {
		if (t.letter.length() > 1 ) {
			treeToCode(t.left, s + "0");
			treeToCode(t.right, s + "1");
		}
		else if (t.letter.length() == 1) {
//			System.out.println(t.letter + "="+s);
			code[(int)t.letter.charAt(0)] = s;
		}
	}
	/**
	* Using the message stored in contents, and the huffman conversions
	* stored in code[], we create the Huffman encoding for our message
	* (a String of 0's and 1's), and return it...
	*/
	public String encodeMessage()  {
		String str="";
		for(int i = 0; i < contents.length();i++){
			str += code[(int)contents.charAt(i)];
		}
		huffmanTree.printLegend();
		System.out.println();
		System.out.println();
		System.out.println("Huffman Encoding:");
		System.out.println(str);
		return str;
	}
	/**
	* Reads in the contents of the file named filename and returns
	* it as a String. The main method calls this method on args[0]...
	*/
	public static String readContents(String filename) throws IOException {
		File myFile = new File(filename);
		Scanner input = new Scanner(myFile);
		String forConverter = input.useDelimiter("\\Z").next();
		return forConverter;
	}
	/**
	* Using the encoded String argument, and the huffman codings,
	* re-create the original message from our
	* huffman encoding and return it...
	*/
	public String decodeMessage(String encodedStr) {
		String str = "";
		int index = 0;
		for(int i = 1; i < encodedStr.length();i++){
			for(int j =0; j<code.length; j++){
				if (i == encodedStr.length()-1){
					if(encodedStr.substring(index).equals(code[j])){
						str += (char) j;
						str += "\n";
						break;
					}
				}
				else {
					if(encodedStr.substring(index, i).equals(code[j])){
						str += (char) j;
						index = i;
						//System.out.println("The String looks like this so far: "+str);
						break;
					}
				}
			}
		}
		return str;
	}
	/**
	* Uses args[0] as the filename, and reads in its contents. Then
	* instantiates a HuffmanConverter object, using its methods to
	* obtain our results and print the necessary output. Finally,
	* decode the message and compare it to the input file.<p>
	* NOTE: Example method provided below...
	* ANOTHER NOTE: The code doesn't print out \n or \t for special characters but instead
	* will have a tab or skip a line.  For example:
	* t=11001
	* 
	* =11010 means that 11010 is the coded String for \n.
	*/
	public static void main(String args[]) throws IOException {
		HuffmanConverter convert = new HuffmanConverter(readContents(args[0]));
		convert.recordFrequencies();
		String encodedMessage = convert.encodeMessage();
		System.out.println();
		System.out.println("Message size in ASCII encoding:"+(convert.contents.length()*8+8));
		System.out.println("Message size in Huffman encoding:"+(encodedMessage.length()+convert.code[(int)'\n'].length()));
		System.out.println();
		System.out.println();
		String decodedMessage = convert.decodeMessage(encodedMessage);
		System.out.println(decodedMessage);
	}

}
