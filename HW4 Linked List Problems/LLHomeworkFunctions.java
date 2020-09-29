
public class LLHomeworkFunctions {

	public static <E> void main(String[] args) {
		// TODO Auto-generated method stub
		Node<Integer> test1 = new Node<Integer>(5,null);
		Node<Integer> test2 = new Node<Integer>(10,null);
		Node<Integer> test3 = new Node<Integer>(15,null);
		Node<Integer> test4 = new Node<Integer>(20,null);
		Node<Integer> test5 = new Node<Integer>(6,null);
		Node<Integer> test6 = new Node<Integer>(12,null);
		Node<Integer> test7 = new Node<Integer>(18,null);
		Node<Integer> test8 = new Node<Integer>(24,null);
//		test1.setNext(test2);
//		test2.setNext(test3);
//		test3.setNext(test4);
//		test4.setNext(test1);
//		test5.setNext(test6);
//		test6.setNext(test7);
//		test7.setNext(test8);
//		System.out.println(terminates(test1));
//		SlowMaxStack<Integer> testSlow = new SlowMaxStack<Integer>();
//		FastMaxStack<Integer> testFast = new FastMaxStack<Integer>();
//		System.out.println("Current max for testSlow is:" + testSlow.getMaxSoFar());
//		System.out.println("Current max for testFast is:" + testFast.getMaxSoFar());
	}

	public static <E> Node<E> getIntersection(Node<E> list1, Node<E> list2) {
		SinglyLinkedList<Node> interList = new SinglyLinkedList<Node>();
		Node<E> walk = list1;
		while (walk.getNext() != null) {
			Node<E> walkL2 = list2;
			while (walkL2.getNext() != null){
				if (walk.getElement().equals(walkL2.getElement())) {
					Node<E> tempNode = new Node<E>(walkL2.getElement(),interList.first());
					interList.addFirst(tempNode);
					break;
				}
				else
					walkL2 = walkL2.getNext();
			}
			if (walk.getElement().equals(walkL2.getElement()) && walkL2.getNext() == null) {
				Node<E> tempNode = new Node<E>(walkL2.getElement(),interList.first());
				interList.addFirst(walkL2);
			}
			walk = walk.getNext();
			if(walk.getNext() == null) {
				Node<E> walkL2x = list2;
				while (walkL2x.getNext() != null){
					if (walk.getElement().equals(walkL2x.getElement())) {
						Node<E> tempNode = new Node<E>(walkL2x.getElement(),interList.first());
						interList.addFirst(tempNode);
						break;
					}
					else
						walkL2x = walkL2x.getNext();
				}
				if (walk.getElement().equals(walkL2x.getElement()) && walkL2x.getNext() == null){
					Node<E> tempNode = new Node<E>(walkL2x.getElement(),interList.first());
					interList.addFirst(tempNode);
				}
			}
		}
		return interList.first();
	}
	
	public static <E> boolean terminates(Node<E> list1){
		Node<E> walk = list1;
		Node<E> walk2 = list1;
		while(walk.getNext() != null && walk2.getNext().getNext() != null) {
			walk = walk.getNext();
			walk2 = walk2.getNext().getNext();
			if (walk == walk2)
				return false;
		}
		return true;
	}
}
