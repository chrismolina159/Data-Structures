public class SlowMaxStack<E extends Comparable<E>> implements MaxStack<E> {

	private Node<E> top;

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public void push(E element) {
		top = new Node<E>(element, top);
	}

	@Override
	public E pop() {
		E element = top.getElement();
		top = top.getNext();
		return element;
	}

	@Override
	public E getMaxSoFar() {
		E currentMax = null;
		
		for(Node<E> node = top; node != null; node = node.getNext()) {
			currentMax = getMax(currentMax, node.getElement());
		}
		
		return currentMax;
	}

	//nolan added helper function
	private E getMax(E element1, E element2) {
		if(element1 == null)
			return element2;
		
		if(element2 == null)
			return element1;
		
		if(element1.compareTo(element2)>0)
			return element1;
		
		return element2;
	}
}