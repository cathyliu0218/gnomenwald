
public interface Stack {

	public int size();

	public boolean isEmpty();

	public String top() throws StackEmptyException;

	public String pop() throws StackEmptyException;

	public void push(String c) throws StackOverflowException;

	public void printStack() throws StackEmptyException;

}//end interface Stack
