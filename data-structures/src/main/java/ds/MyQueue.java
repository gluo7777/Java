package ds;

public interface MyQueue<T> {
	void add(T item);
	T remove();
	T peek();
	boolean isEmpty();
}
