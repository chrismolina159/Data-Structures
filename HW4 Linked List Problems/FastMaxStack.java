public class FastMaxStack<E extends Comparable<E>> implements MaxStack<E> {

	private Node<E> top;
	private Node<E> currentMax;

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public void push(E element) {
		top = new Node<E>(element, top);
		if (currentMax == null || currentMax.getElement().compareTo(element) <= 0){
			currentMax = new Node<E>(element, currentMax);
		}
		else
			currentMax = new Node<E>(currentMax.getElement(), currentMax);
	}

	@Override
	public E pop() {
		E element = top.getElement();
		top = top.getNext();
		currentMax = currentMax.getNext();
		return element;
	}

	@Override
	public E getMaxSoFar() {
		if (isEmpty())
			return null;
		return currentMax.getElement();
	}
}