
public class Convert {// convert infix to RPN

	public static String ops = "+-*/";// string of operators
	public static String nums = "0123456789";

	static int precedenceLevel(char op) {// precedence level of operators
		switch (op) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;// * and / have greater precedence

		default:
			throw new IllegalArgumentException("Operator unknown: " + op);
		}// end switch
	}// end precedenceLevel

	/**
	 * converts infix to RPN any negative numbers have to be in parenthesis
	 * example: ((-34) + 5)
	 */
	public static String infixToRPN(String infix) {

		String rpn = "";// initiliase the required RPN string

		ArrayBasedStack stack = new ArrayBasedStack();

		// looping through each char of string infix
		for (int i = 0; i < infix.length(); i++) {
			char token = infix.charAt(i);
			// if token is a number and the char before it is not a negative
			// sign
			if (nums.contains(Character.toString(token)) 
					&& infix.charAt(i - 1) != '-') {
				// to check for ints >9, for example:
				// for 27: this if statement prevents 7 from being pushed to rpn
				// as 27 has already been pushed to rpn
				if (!nums.contains(Character.toString(infix.charAt(i - 1)))) {
					int counter = i;// for ints > 9
					if (nums.contains(Character.toString(infix.charAt(i + 1)))) {
						do {// this do-while loop allows the pushing of ints>9
							// such as: 45
							rpn += infix.charAt(counter);
							counter++;// increment counter
						} while (nums.contains(Character.toString(infix.charAt(counter))));
						rpn += " ";// after do-while loop, add whitespace to the
									// int
					} else {// if the int is in range of 0-9
						rpn += token + " ";
					} // end else
				} // end if statement
			} // end outer if statement

			// if token is an operator and the char before it
			// is not a negative sign
			// this is for the case when - is used to indicate a negative number
			if (ops.contains(Character.toString(token)) 
					&& infix.charAt(i - 1) != '(') {
				// while there is an operator at top of stack
				while (!stack.isEmpty() && ops.contains(stack.top())) {
					char charTop = stack.top().charAt(0);
					// if precedence level of operator at top of stack
					// is >= precedence level of operator, pop from stack
					if (precedenceLevel(charTop) > precedenceLevel(token)
						|| precedenceLevel(charTop) == precedenceLevel(token)) {
						rpn += stack.pop() + " ";
					} // end if statement

				} // end while loop
				// push operator to stack
				stack.push(Character.toString(token));

			} // end outer if (for operator)

			if (token == '(') {// if token is a left bracket
				//if char after the left bracket is a negative sign
				//for negative numbers
				if (infix.charAt(i + 1) == '-') {
					int counter = i + 1;// for ints > 9
					//until the char is a right bracket
					while (infix.charAt(counter) != ')') {
						rpn += infix.charAt(counter);
						counter++;//incrementing counter
					}//end while loop
					rpn += " ";//to make a new number in rpn
				} else {//if char after the left bracket is not a negative sign
					stack.push(Character.toString(token));
				}//end else
			} // end outer if

			// if token is a right bracket
			if (token == ')') {
				// while operator at the top is not a left bracket
				while (!stack.isEmpty() && !stack.top().equals("(")) {
					rpn += stack.pop() + " ";// pop and add to rpn

				} // end while loop
				if (!stack.isEmpty()) {//to check the case for negative number
					stack.pop();// pop left bracket from stack
				}//end if
			} // end outer if

		} // end for loop looping through the string infix
		while (!stack.isEmpty()) {// while there are more operators
			rpn += stack.pop() + " ";// pop and add to rpn
		} // end while loop
		return rpn;

	}// end InfixToRPN

	/**
	 * Converts RPN to infix
	 */
	public static String RPNToInfix(String rpn) {

		String infix = "";// initialise the required infix string

		ArrayBasedStack stack = new ArrayBasedStack();

		// split string rpn using delimiter whitespace
		for (String token : rpn.split(" ")) {

			if (token.matches(".*\\d.*")) {// if token is a number

				stack.push(token);// push to stack

			} // end if
			if (ops.contains(token)) {// if token is an operator
				// pop two numbers from stack
				String temp1 = stack.pop();
				String temp2 = stack.pop();
				infix = "(" + temp2 + token + temp1 + ")";// show operation

				stack.push(infix);// push to stack

			} // end if
		} // end for loop

		return stack.pop();

	}// end RPNToInfix

}// end class Convert
