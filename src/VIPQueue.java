
public class VIPQueue  {
	private final static int DEFAULT_SIZE = 100;
	private Gnome[] store;// array to store people

	private int length, back, front;// length is number of people in queue

	public boolean isFull() {// returns true if queue is full
		return back - front == length;
	}// end

	public boolean isEmpty() {// returns true if queue is empty
		return length == 0;
	}// end

	public VIPQueue(int size) {// constructor
		this.store = new Gnome[size];
		this.length = 0;
		this.front = 0;
		this.back = -1; // initialising the back location
	}// end

	public VIPQueue() {// default constructor
		this(DEFAULT_SIZE);
	}// end

	/**
	 * add a person to queue person is added to back of queue
	 */
	public void join(Gnome p) {
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
	public Gnome leave() {
		if (isEmpty()) {
			System.out.println("Sorry, the queue is empty...no one to leave");
			return null;
		} // end if
		Gnome temp = this.store[front++];
		this.length--;// decrement length
		return temp;
	}// end

	public void leave(Gnome p) {
		if (isEmpty()) {
			System.out.println("Sorry, the queue is empty...no one to leave");

		} // end if
			// this.length--;
		int index = this.findNameIndex(p);

		if (index == this.back) {
			this.back--;
			this.length--;
		} else {
			int counter = index;
			while (counter < this.length - 1) {
				this.store[counter] = this.store[counter + 1];
				counter++;
			} // end while loop
			this.back--;
			this.length--;
		}

	}// end leave

	public void VIPJoin(Gnome p) {
		if (isFull()) {
			System.out.println("Sorry, the queue is full...");
			return;
		} // end of if
		if (isEmpty()) {
			this.store[++back] = p;
		} // end of if
			// if it's not empty, vip joins the second position
		else if (p.getVIP() > 3) {
			// this.length++;

			this.back++;
			int counter = this.back;
			while (counter > this.front + 2) {

				this.store[counter] = this.store[counter - 1];
				counter--;
			}
			this.store[this.front + 2] = p;
			this.length++;

		} // end of else if
		else {
			System.out.println("Sorry "+p.getName()+", "+" your VIP status is too low to enjoy this privilege");
		} // end of else
	}// end of VIPJoin

	public int length() {// getter
		return this.length;
	}// end

	public int findNameIndex(Gnome p) {
		if (isEmpty()) {
			// System.out.println("Sorry bro, there is nothing to find");
			return -1;
		} // end of if
		int find = 0;
		try {
			while (!(store[find].getName().equals(p.getName()))) {// continue
																	// looking
																	// through
				// store until p is found.
				find++;
			} // end of while
			if (find == this.length) {// if p is not in store, return -1
				return -1;
			}

		} // end of if
		catch (NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
		return find;
	}// end of findNameIndex

	public int findIDIndex(int id) {
		if (isEmpty()) {
			// System.out.println("Sorry bro, there is nothing to find");
			return -1;
		} // end of if
		int find = 0;
		while (store[find].getID() != id) {// continue looking through
			// store until p is found.
			find++;
		} // end of while
		if (find == this.length) {// if p is not in store, return -1
			return -1;
		} // end of if
		return find;
	}// end of findIDIndex

	/**
	 * prints the queue as a string
	 */
	public String toString() {
		if (isEmpty())
			return "The queue is empty";
		String temp = "";
		for (int i = front; i <= this.back; i++) {

			temp += this.store[i].getName() + ", ";

		} // end for loop
		return temp;
	}// end toStrings

}// end Q3