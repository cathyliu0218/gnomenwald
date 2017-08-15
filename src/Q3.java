public class Q3 implements Queue {
	private final static int DEFAULT_SIZE = 100;
	private Village[] store;// array to store people

	private int length, back, front;// length is number of people in queue

	public boolean isFull() {// returns true if queue is full
		return back - front == length;
	}// end

	public boolean isEmpty() {// returns true if queue is empty
		return length == 0;
	}// end

	public Q3(int size) {// constructor
		this.store = new Village[size];
		this.length = 0;
		this.front = 0;
		this.back = -1; // initialising the back location
	}// end

	public Q3() {// default constructor
		this(DEFAULT_SIZE);
	}// end

	/**
	 * add a person to queue person is added to back of queue
	 */
	public void join(Village p) {
		if (isFull()) {
			System.out.println("Sorry, the queue is full...");
			return;// better to have a QueueFullException
		} // end if
		this.store[++back] = p; // only get here if the queue isn't full
		this.length++;// increment length

	}// end

	/**
	 * removes a person from queue person is removed front the front of queue
	 */
	public Village leave() {
		if (isEmpty()) {
			System.out.println("Sorry, the queue is empty...no one to leave");
			return null;
		} // end if
		Village temp = this.store[front++];
		this.length--;// decrement length
		return temp;
	}// end

	public int length() {// getter
		return this.length;
	}// end

	/**
	 * prints the queue as a string
	 */
	public String toString() {
		if (isEmpty())
			return "The queue is empty";
		String temp = "The queue holds " + this.length + " people with names:\n\t";
		for (int i = front; i <= this.back; i++) {
			temp += this.store[i].getName() + ", ";

		} // end for loop
		return temp;
	}// end toString

}// end Q3