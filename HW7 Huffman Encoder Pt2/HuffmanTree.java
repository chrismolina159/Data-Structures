import java.util.*;

public class HuffmanTree {

	HuffmanNode root;
	
	public HuffmanTree(HuffmanNode huff){
		this.root = huff;
	}
	
	public void printLegend(){
		printLegend(root, "");
	}
	
	private void printLegend(HuffmanNode t, String s){
		if (t.letter.length() > 1 ) {
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		else if (t.letter.length() == 1)
			System.out.print(t.letter + "="+s+" ");
	}
	
	public static BinaryHeap legendToHeap(String legend){
		ArrayList<HuffmanNode> arr = new ArrayList<HuffmanNode>();
		int index = 0;
		
		for (int i=0; i < legend.length();){
			String tempWord="";
			Double tempDouble = 0.0;
			index = legend.indexOf(" ", index+1);
			tempWord = legend.substring(i,index);
			i = index + 1;
			index = legend.indexOf(" ",index+1);
			if (index == -1) {
				tempDouble = Double.parseDouble(legend.substring(i));
				HuffmanNode temp = new HuffmanNode(tempWord,tempDouble);
				arr.add(temp);
				break;
			}
			else
				tempDouble = Double.parseDouble(legend.substring(i,index));
			HuffmanNode temp = new HuffmanNode(tempWord,tempDouble);
			arr.add(temp);
			i = index + 1;
		}
		
		//Need to cast an arrayList into an array
		HuffmanNode[] arr2 = new HuffmanNode[arr.size()];
		for (int i = 0; i < arr.size();i++){
			arr2[i] = arr.get(i);
		}
		
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(arr2);
		return heap;
	}
	
	public static HuffmanTree createFromHeap(BinaryHeap b){
		while (b.getSize() > 1) {
			HuffmanNode min1 =(HuffmanNode) b.deleteMin();
			HuffmanNode min2 =(HuffmanNode) b.deleteMin();
			HuffmanNode newTemp = new HuffmanNode(min1, min2);
			
			b.insert(newTemp);
		}
		HuffmanTree rtrnTree = new HuffmanTree((HuffmanNode) b.findMin());
		return rtrnTree;
	}
}
