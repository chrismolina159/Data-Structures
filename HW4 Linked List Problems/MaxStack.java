public interface MaxStack<E> {
	boolean isEmpty();
	
	void push(E element);
	
	E pop();
	
	E getMaxSoFar();
}