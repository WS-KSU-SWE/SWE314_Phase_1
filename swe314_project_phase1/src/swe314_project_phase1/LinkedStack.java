package swe314_project_phase1;

// The implementation for the linked stack was taken from the CSC 212 data structure course. We could have used the java collection but we had no idea what that
// Was when we implemented the system.
public class LinkedStack<T> implements Stack<T> {

	private Node<T> top;
	
	
	public LinkedStack() {
		
	}
	
	
	public boolean empty() {
		return top == null;
	}
	
	
	public boolean full() {
		return false;
	}
	
	
	public void push(T e) {
		
		Node<T> newTop = new Node<T>(e);
		
		newTop.next = top;
		
		top = newTop;
	}
	
	
	public T pop() {
		
		T e = top.data;
		
		top = top.next;
		
		return e;
	}
	
	
	public void printStack() {
		
		Node<T> current = top;
		
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
		
	}
	
}
