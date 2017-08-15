
public class RPNOutput {// gives output

	/**
	 * performs calculations and returns the output, showing state of stack at
	 * each stage
	 */
	public static String Output(String input) {

		String ops = "+-*/";
		String nums = "0123456789";// string of digits

		ArrayBasedStack stack = new ArrayBasedStack();

		// split string input using delimiter whitespace
		for (String token : input.split(" ")) {

			// if token is a number
			if (token.matches(".*\\d.*")) {
				stack.push(token);//push to stack
				
			}//end if

			if (ops.contains(token)) {//if token is an operator
                //pop two numbers from stack and convert from string to int
				int temp1 = Integer.parseInt(stack.pop());
				int temp2 = Integer.parseInt(stack.pop());

				int newValue = 0;//for calculation
				switch (token) {//for different cases of operators
				case "+":
					newValue = temp2 + temp1;
					
					break;
				case "-":
					newValue = temp2 - temp1;
					
					break;
				case "*":
					newValue = temp2 * temp1;
				
					break;
				case "/":
					newValue = temp2 / temp1;
				
					break;

				}//end switch
				//convert calculated int to string
				String stringValue = Integer.toString(newValue);
				stack.push(stringValue);//push to stack
				

			}//end if
		}//end for loop
		return "output: " + stack.top();
	}//end Output
}//end class RPNOutput