public class Converter {

	private String infixNotation;
	
	public Converter (){}
	
	public Converter (String infixNotation){
		this.infixNotation = infixNotation;
	}
	/**
	* Creates and returns the postfix expression from the user's inputted infix expression.
	* @return the String postfix representation of the input string.
	*/
	public String toPostFix() {
		String postFix="";
		String infixToken = tokenize();
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		
		for (int i = 0; i < infixToken.length(); i++) {
			int space = infixToken.indexOf(" ", i);
			String operatorOrOperand = infixToken.substring(i, space);
			// If the current substring is a number, append it to the postfix string
			if (isNum(operatorOrOperand)){
				postFix += operatorOrOperand+" ";
				i += operatorOrOperand.length();
			}
			/*
			* If the current substring is an operator, then begin the following logic:
			* If the ArrayStack is empty, push the operator onto the stack
			* Else
			* 	Check the top of the stack. Using PEMDAS check if the current operator
			* 	has higher precedence or lower precedence.
			* 	If higher, push onto the stack.
			* 	If lower, pop operators out of the stack, append them to the output string, and
			* 	keep popping until the current operator has a higher precedence or stack becomes empty
			*/
			else {
				if (operatorStack.size() > 0) {
					char cmTest = operatorStack.pop();
					if (pemdasCheck(cmTest,operatorOrOperand.charAt(0)) || operatorOrOperand.charAt(0) == '(') {
						operatorStack.push(cmTest);
						operatorStack.push(operatorOrOperand.charAt(0));
					}
					else {
						// If the operator == ")", continue popping operators until you reach a "("
						if (popAllOperators(operatorOrOperand.charAt(0))) {
							while (true) {
								if (cmTest != '(') {
									postFix += cmTest + " ";
									cmTest = operatorStack.pop();
								}
								else
									break;
							}
						}
						else {
							// Keep popping from the stack until the current operator has a higher precedence.
							while (true){
								if (pemdasCheck(cmTest,operatorOrOperand.charAt(0))) {
									postFix += cmTest + " ";
									if (operatorStack.size() <=0 ) {
										operatorStack.push(operatorOrOperand.charAt(0));
										break;
									}
									cmTest = operatorStack.pop();
								}
								else {
									operatorStack.push(cmTest);
									operatorStack.push(operatorOrOperand.charAt(0));
									break;
								}
							}
						}
					}
					i++;
				}
				else{
					operatorStack.push(operatorOrOperand.charAt(0));
					i++;
				}
			}
		}
		while (operatorStack.size() > 0) {
			char cmTest = operatorStack.pop();
			postFix += cmTest + " ";
		}
		return postFix;
	}
	
	/**
	* Parses the infix expression to create a list of tokens representing
	* operators and operands. Each operator/operand is separated by a space
	* @return the input string parsed to infix notation
	*/
	private String tokenize() {
		String parsed = "";
		for (int i = 0; i < infixNotation.length();i++){
			char c1 = infixNotation.charAt(i);
			if (Character.isDigit(c1)) {
				String testIfNum = c1 + "";
				for(int j = i+1;j<infixNotation.length();j++){
					if(Character.isDigit(infixNotation.charAt(j))) {
						testIfNum += infixNotation.charAt(j);
						i = j;
					}
					else 
						break;
				}
				parsed += testIfNum + " ";
			}
			else if (c1 == '*' || c1 == '/' ||c1 == '+' ||c1 == '-' 
					||c1 == '(' ||c1 == ')' ||c1 == '^') {
				parsed += c1 + " ";
			}
		}
		return parsed;
	}
	
	/**
	 * Checks if the passed in string is a number
	 * @return true/false if NumberFormatExcept is caught
	 */
	public static boolean isNum(String str) {
		try {
			int numTest = Integer.parseInt(str);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	/**
	 * Uses PEMDAS to compare the precedences of two operators
	 * @return true/false if operator1 occurs earlier in PEMDAS than operator2
	 */
	private boolean pemdasCheck(char operator1, char operator2) {
		int opValue1;
		int opValue2;
		switch (operator1){
			case '+': opValue1 = 1;
			break;
			case '-': opValue1 = 1;
			break;
			case '*': opValue1 = 2;
			break;
			case '/': opValue1 = 2;
			break;
			case '^': opValue1 = 3;
			break;
			case '(': opValue1 = 4;
			break;
			case ')': opValue1 = 5;
			break;
			default : opValue1 = -1;
			break;
		}
		switch (operator2){
			case '+': opValue2 = 1;
			break;
			case '-': opValue2 = 1;
			break;
			case '*': opValue2 = 2;
			break;
			case '/': opValue2 = 2;
			break;
			case '^': opValue2 = 3;
			break;
			case '(': opValue2 = 4;
			break;
			case ')': opValue2 = 5;
			break;
			default : opValue2 = -1;
			break;
		}
		return opValue1 > opValue2;
	}
	/**
	 * Checks if the program needs to pop all the operators in the stack
	 * @return true/false if c==')'
	 */
	private boolean popAllOperators (char c){
		return c == ')';
	}
}
