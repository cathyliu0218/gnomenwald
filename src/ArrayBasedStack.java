
public class ArrayBasedStack implements Stack {

	private static final int capacity = 100;
	private String[] s;//array of strings
	private final int N;// capacity
	private int top = -1;
	private int length;

	public ArrayBasedStack() {//constructor
		this(capacity);
	}//end constructor

	public ArrayBasedStack(int capacity) {//constructor
		N = capacity;
		s = new String[N];
	}//end constructor

	public int size() {
		return top + 1;
	}//end

	public int getLength() {//getter for length
		return this.length;
	}//end

	public boolean isEmpty() {//returns true if stack is empty
		return top < 0;
	}//end

	/**
	 * returns the top element of stack
	 */
	public String top() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack is empty");
		return s[top];
	}//end

	/**
	 * removes the top element from the stack
	 * and returns it
	 */
	public String pop() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack is Empty");
		length--;//decrementing length
		return s[top--];
	}//end

	/**
	 * append element to top of stack
	 */
	public void push(String token) {
		if (size() == N)
			throw new StackOverflowException("Stack is Full");
		s[++top] = token;
		length++;//increment length
	}//end

	/**
	 * prints the state of the stack
	 */
	public void printStack() {
		if (isEmpty())
			throw new StackEmptyException("Stack is Empty");

		String printS = "";
		for (int i = 0; i < length; i++) {
			printS = s[i] + "," + printS;
		}//end for loop
		System.out.println("stack: " + printS);

	}//end printStack

}//end ArrayBasedStack
