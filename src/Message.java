
import java.io.*;
import java.util.*;

public class Message {
	private static int num_made = 0;//number of msgs made

	private String content;
	private String subject;
	private int id; // message id
	private Gnome from_person, to_person;
	private boolean open;//to check if the msg has been read
	private Date sent_date, date;
	private boolean isSpam;//to check for spam
	private boolean addTo;

	public String getContent() {//getter
		return this.content;
	}//end

	public String getString_Date() {//getter
		return this.date.toString();
	}//end

	public int getID() {//getter
		return this.id;
	}//end
	
	public boolean getAddTo(){
		return this.addTo;
	}
	


	public void receipt() {//to check if the sent msg was sent
		//msg is recd if either it was opened or the alert for receiver is true
		if (this.open == true || this.to_person.alert == true) {
			System.out.println("Message was sent to " + this.to_person.getName());
		} else {
			System.out.println("Message not sent");
		}//end else
	}//end receipt

	public void spam() {// Alerts the user of possible spam messages

		String[] spam_list = spam_words.split(","); // converting to array 
		//loop through all the spam words to check if the msg has any of them
		for (int i = 0; i < spam_list.length; i++) {
			if (this.getContent().contains(spam_list[i]))
				this.isSpam=true;
		}//end for loop
	}//end spam

	/**
	 * asks the sender to enter his/her password to send the msg
	 * encrypts the msg
	 * alerts the receiver of a new msg
	 */
	public void send() {// enable user to send messages after verifying account

		this.from_person.add_sent_id(this.id);
		this.to_person.add_recd_id(this.id); // assign send and recd messages
												// the same mess id
		this.sent_date = new Date();// sets the date of msg sent
		this.encrypt();// encrypts the msg for security
		this.to_person.messageAlert();
		
		if(this.getAddTo()){
			this.getFromPerson().getAllFriends().append(this.getToPerson());
		}

	}//end send
	
	public void send(GnomeGraph g) {// enable user to send messages after verifying account

		this.from_person.add_sent_id(this.id);
		this.to_person.add_recd_id(this.id); // assign send and recd messages
												// the same mess id
		this.sent_date = new Date();// sets the date of msg sent
		this.encrypt();// encrypts the msg for security
		this.to_person.messageAlert();
		
		if(this.getAddTo()){
			g.addFriendship(this.getFromPerson(), this.getToPerson());
		}

	}//end send
	
	public Gnome getFromPerson(){
		return this.from_person;
	}
	
	public Gnome getToPerson(){
		return this.to_person;
	}

	public int from_person_getID() { // getter method for from_person
		return this.from_person.getID();
	}// end from_person_getID 

	public int to_person_getID() {// getter method for to_person
		return this.to_person.getID();
	}// end to_person_getID 
    
	public boolean checkSpam(){//to check whether msg is spam
		return this.isSpam;
	}//end checkSpam
	
	public void open() throws BadMessageException { // open procedure which sets the field open to true if
							// the user opens the message

		this.encrypt();// decrypt message by using the encrypt
						// function

		this.open = true; // allow user to open/read message
		this.to_person.alert = false;

		if (this.getContent().equals(" ")) {
			// throws a BadMessageException if message content is blank
			throw new BadMessageException();
		} // end if statement
		this.spam();
		if (this.checkSpam()) {
			throw new BadMessageException(this.from_person.getName());
		} // end if statement

		System.out.println(this.toString());

	}// end open

	public String getSubject() { // returns the subject of the message
		return this.subject;
	}//end 

	public String toString() { // ovewrites object's toString method
		return "Subject: "+this.getSubject() +"\n"+ this.content;
	}//end

	/**
	 * returns the reverse of a given string
	 * using recursion
	 */
	public String reverse(String content) {
		if (content.length() <= 1) {//base case
			return content;
		}//end if statement
		return reverse(content.substring(1)) + content.charAt(0);//recursion
	}//end reverse

	/**
	 * encrypts the msg using the function reverse
	 * breaks string into 2 halves
	 * reverse each half and then add
	 */
	public void encrypt() {

		//don't break into halves when length =2
		// because doing so would return the string as it is
		if (this.content.length() == 2) {
			this.content = reverse(this.content);
		}//end if statement
		
		content = this.content;//content of the msg
		int length = content.length();//length of msg
		//divide content into 2 halves
		String contentFirstHalf = content.substring(0, length / 2);
		String contentSecondHalf = content.substring(length / 2);
		//reverse each half
		String reverseFirstHalf = reverse(contentFirstHalf);
		String reverseSecondHalf = reverse(contentSecondHalf);
		this.content = reverseFirstHalf + reverseSecondHalf;//adding the halves
	}

	// A string containing as many possible spam words we could find
	public String spam_words = "4U,Claims you are a winner,For instant access,"
			+ "Accept credit cards,	Claims you registered with Some "
			+ "Kind of Partner,For just $,Act now! Don’t hesitate!,Click below,"
			+ "Free access,Additional income,Click here link,Free cell phone,"
			+ "Addresses on CD,Click to remove,Free consultation,All natural,"
			+ "Click to remove mailto,Free DVD,Amazing,Compare rates,"
			+ "Free grant money,Apply Online,Compete for your business,"
			+ "Free hosting,As seen on,Confidentially on all orders,"
			+ "Free installation,Auto email removal,Free investment,"
			+ "Avoid bankruptcy,Consolidate debt and credit,Free leads,"
			+ "Be amazed,Copy accurately,Free membership,Be your own boss,"
			+ "Copy DVDs,Free money,Being a member,Credit bureaus Free offer,"
			+ "Big bucks Credit card offers,Free preview,Bill 1618,"
			+ "Cures baldness,Free priority mail,Billing address Dear email,"
			+ "Free quote,Billion dollars,Free sample,Brand new page,"
			+ "Free trial,Bulk email,Free website,Buy direct,Dig up dirt on "
			+ "friends,Full refund,Buying judgments,Direct email,Get It Now,"
			+ "Cable converter,Direct marketing,Get paid,Call free Discusses "
			+ "search engine listings,Get started now,Call now	Do it today,"
			+ "Gift certificate,Calling creditors,Don’t delete,	Great offer,"
			+ "Can’t live without,Drastically reduced Guarantee,Cancel at "
			+ "any time,Have you been turned down?,Cannot be combined with"
			+ "any other offer,Easy terms Hidden assets,Cash bonus,"
			+ "Eliminate bad credit,Home employment,Cashcashcash,Email harvest,"
			+ "Human growth hormone,Casino,Email marketing,If only it were that"
			+ " easy,Expect to earn	In accordance with laws,Cents on the dollar"
			+ ",Fantastic deal,Increase sales,Check or money order,Fast Viagra,"
			+ "delivery,Increase traffic,Financial freedom Insurance,Join"
			+ " millions of Americans,No questions asked Reverses aging,"
			+ "No selling Risk free,Limited time only,No strings attached"
			+ " Round the world,Long distance phone offer,Lose weight spam,"
			+ "Off shore Safeguard notice,Lower interest rates,Offer expires,"
			+ "Satisfaction guaranteed,Lower monthly payment,Offers coupon "
			+ "Save $,Lowest price,Offers extra cash,Save big money,Luxury car"
			+ ",Offers free (often stolen) passwords,Save up to,Mail in order "
			+ "form,Once in lifetime,Score with babes,Marketing solutions One"
			+ " hundred percent free,Mass email,One hundred percent guaranteed"
			+ " See for yourself,Meet singles,One time mailing,Sent in "
			+ "compliance,Member stuff,Online biz opportunity,Serious cash,"
			+ "Message contains disclaimer,Online pharmacy,MLM Only $,Money "
			+ "back Opportunity,Sign up free today,Money making Opt in,"
			+ "Month trial offer,Special promotion,More Internet traffic,"
			+ "Mortgage rates,Orders shipped by priority mail,Stock alert,"
			+ "Multi level marketing,Outstanding values	Stock disclaimer "
			+ "statement,Name brand,Pennies a day Stock pick,New customers"
			+ " only,People just leave money laying around,New domain "
			+ "extensions,Strong buy,Potential earnings,Stuff on sale,"
			+ "No age restrictions,Print form signature,Subject to credit,"
			+ "No catch Print out and fax,Supplies are limited,"
			+ "	No claim forms,	Produced and sent out,Take action now,"
			+ "No cost Profits,Talks about hidden charges,No credit check,"
			+ "Promise you …!,Talks about prizes,No disappointment	Pure profit"
			+ ",Tells you it’s an ad,No experience	Real thing,Terms and "
			+ "conditions,No fees,Refinance home,The best rates,No gimmick,"
			+ "Removal instructions,The following form,No inventory,Remove "
			+ "in quotes,They keep your money — no refund!,No investment "
			+ "Remove subject,They’re just giving it away,No medical exams,"
			+ "Removes wrinkles,This isn’t junk,No middleman,Reply remove"
			+ " subject,This isn’t spam,No obligation,Requires initial "
			+ "investment,University diplomas,No purchase necessary,Reserves"
			+ " the right,Unsecured credit/debt,We honor all,Will not "
			+ "believe your eyes,Urgent	,Weekend getaway,What are you"
			+ " waiting for?,Vacation offers,While supplies last,"
			+ "Work at home,Viagra and other drugs,While you sleep,"
			+ "You have been selected,Wants credit card,Who really wins?,"
			+ "Your income,We hate spam,Why pay more?";// end of possible string words

	public Message(Message_Centre ctr, Gnome from_p, Gnome to_p, String subject, String content) {// constructor

		this(ctr, from_p, to_p, subject, content, false);
	}// end Message constructor
	
	public Message(Message_Centre ctr, Gnome from_p, Gnome to_p, String subject, String content, boolean addTo){
		this.from_person = from_p; // assigns variables to each field in Message
		this.to_person = to_p;
		this.subject = subject;
		this.content = content;
		ctr.storeMessage(this);
		this.id = num_made++;  
		this.open = false;
		this.isSpam=false;
		this.date = new Date();
		this.addTo = addTo;
	}

}// end of class Message