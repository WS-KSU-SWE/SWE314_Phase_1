package swe314_project_phase1;

public interface Stack<T> {

	boolean empty();
	boolean full();
	
	void push(T e);
	T pop();
	
}
