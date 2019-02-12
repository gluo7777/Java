package ds;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Queue implemented using a linked list
 * 
 * @author gluo7
 *
 */
public class ListQueue<T> implements MyQueue<T>{
	
	// keep track of first,last nodes
	private ListNode<T> first, last;

	@Override
	public void add(T item) {
		ListNode<T> node = new ListNode<>(item);
		if(this.first == null) {
			this.first = node;
			this.last = node;
		}else {
			this.last.next = node;
			this.last = node;
		}
	}

	@Override
	public T remove() {
		if(this.isEmpty()) throw new NoSuchElementException("Nothing to remove.");
		T value = this.first.value;
		this.first = this.first.next;
		if(this.first == null) this.last = null; // first and last were same elements before removal
		return value;
	}

	@Override
	public T peek() {
		if(this.isEmpty()) throw new NoSuchElementException("Nothing to peek.");
		return this.first.value;
	}

	@Override
	public boolean isEmpty() {
		return this.first == null;
	}
	
	public List<T> asList(){
		List<T> lst = new LinkedList<>();
		ListNode<T> next = this.first;
		while(next != null) { lst.add(next.value); next = next.next; }
		return lst;
	}
	
	public static void main(String[] args) {
		ListQueue<Integer> queue = new ListQueue<>();
		queue.add(5);
		queue.add(9);
		queue.add(14);
		System.out.printf("At top = %d.\n", queue.peek());
		System.out.printf("Removed %d.\n", queue.remove());
		System.out.printf("At top = %d.\n", queue.peek());
		System.out.printf("Queue: %s\n",queue.asList());
	}
}
