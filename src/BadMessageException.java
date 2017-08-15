

public class BadMessageException extends Exception {//custom exception

	public BadMessageException() {//exception for a blank msg
		super("Sorry, something went wrong. You received a blank message");
	}//end
	
	public BadMessageException (String spammerName ) {//exception for spam
		 super ("Alert!Alert!Alert! You have received spam from "+spammerName
				                                     + ". Be careful!");
		} // end
}//end BadMessageException
																																// constructor
