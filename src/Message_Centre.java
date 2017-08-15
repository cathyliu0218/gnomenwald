

public class Message_Centre {

	private static final int DEFAULT_SIZE = 10000;//size of array to hold msgs
	private int num_mess_stored;
	private Message[] store;
	
	public void storeMessage(Message m) {//stores messages in the array

		this.store[num_mess_stored % DEFAULT_SIZE] = m;
		num_mess_stored++;//incrementing number of msgs
		if (this.num_mess_stored > store.length)//if capacity exceeded
			System.out.println("exceeded message capacity, "
					+ "so overwriting old messages");
	}//end storeMessage

	/**
	 * finds messages by ID
	 */
	public Message findByMessageID(int mess_id) { // to return the message
													// with this id

		int location = 0;
		boolean foundIt = false;
		while (!foundIt && location < this.num_mess_stored)
			if (this.store[location].getID() == mess_id)//if IDs match
				foundIt = true;//update foundIt

		location++;//increment location

		if (foundIt)
			location--;//have to decrement location
		               //because incremented location in the loop

		return this.store[location];//return the message
	}//end findByMessageID

	
	/**
	 * returns a list of messages
	 * sent by a given person
	 */
	public Message[] findBySender(Gnome sender) {

		//set length to number of msgs sent
		Message[] found = new Message[sender.getNum_mess_sent()];
		System.out.println(sender.getNum_mess_sent());
		int count = 0;
		int location = 0;

		while (location < this.num_mess_stored) {
			if (this.store[location].from_person_getID() == sender.getID()) {
				found[count] = (this.store[location]);
				count++;
			}//end if statement
			location++;
		}//end while loop
		return found;//list of required msgs
	}//end findBySender

	/**
	 * returns a list of messages
	 * received by a given person
	 */
	public Message[] findByReceiver(Gnome receiver) {

		//set length to number of msgs recd
		Message[] found = new Message[receiver.getNum_mess_recd()];
		int count = 0;
		int location = 0;

		while (location < this.num_mess_stored) {
			if (this.store[location].to_person_getID() == receiver.getID()) {
				found[count] = (this.store[location]);
				count++;
			}//end if statement
			location++;
		}//end while loop
		return found;
	}//end findByReceiver

	/**
	 * returns a list of messages
	 * by searching for a given subject
	 */
	public Message[] findBySubject(String subject) {

		int counter = 0;//to get the length of required array
		int location = 0;
		while (location < this.num_mess_stored) {
			if (subject.equals(this.store[location].getSubject())) {
				counter++;
			}//end if statement
			location++;
		}//end while loop
		Message[] found = new Message[counter];
		location = 0;//set location back to 0
		int count = 0;
		while (location < this.num_mess_stored) {
			if (subject.equals(this.store[location].getSubject())) {
				found[count] = this.store[location];
				count++;
			}//end if statement
			location++;
		}//end while loop
		return found;
	}//end findBySubject

	/**
	 * returns a list of messages
	 * by searching for given content
	 */
	public Message[] findByContent(String keyword) {
		int counter = 0;//to get length of required array
		int location = 0;

		while (location < this.num_mess_stored) {
			if (this.store[location].getContent().contains(keyword)) {
				counter++;
			}//end if statement
			location++;
		}//end while loop
		Message[] found = new Message[counter];
		location = 0;//change location to 0
		int count = 0;
		while (location < this.num_mess_stored) {
			if (this.store[location].getContent().contains(keyword)) {
				found[count] = this.store[location];
				count++;
			}//end if statement
			location++;
		}//end while loop
		return found;
	}//end findByContent

	/**
	 * returns a list of messages
	 * by searching for a given date that the msg was sent on
	 * the given has to be of the form:
	 * "Tue Jul 11"
	 */
	public Message[] findByDate(String date) {
		int counter = 0;//to get length of required array
		int location = 0;
		while (location < this.num_mess_stored) {
			if (this.store[location].getString_Date().contains(date)) {
				counter++;
			}//end if statement
			location++;
		}//end while loop
		Message[] found = new Message[counter];
		location = 0;
		int count = 0;
		while (location < this.num_mess_stored) {
			if (this.store[location].getString_Date().contains(date)) {
				found[count] = this.store[location];
				count++;
			}//end if statement
			location++;
		}//end while loop
		return found;
	}//end findByDate

	/**
	 * returns msg id and content of
	 * each msg in message centre
	 */
	public String toString() {

		String temp = "Message_centre_content = \n\t";
		for (int i = 0; i < this.num_mess_stored; i++)
			temp += this.store[i] + ", ";// prints message id and content
		return temp;
	}//end toString

	public Message_Centre(int capacity) {// constructor
		if (capacity <= 0) {
			capacity = DEFAULT_SIZE;
			System.out.println("You gave me an improper capacity. So I fixe it for you :)");
		}//end if statement
		this.store = new Message[capacity];
	}//end constructor

	public Message_Centre() {// default constructor

		this(DEFAULT_SIZE);
	}//end Message_Centre

}//end of class Message_Centre