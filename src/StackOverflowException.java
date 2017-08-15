
public class StackOverflowException extends RuntimeException {

	public StackOverflowException() {// constructor
		super();
	}// end

	public StackOverflowException(String message) {// constructor
		super(message);
	}// end

	public StackOverflowException(String message, Throwable cause) {
		super(message, cause);
	}// end
}// end StackOverflowException