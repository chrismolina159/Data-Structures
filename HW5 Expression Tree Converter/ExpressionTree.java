public class ExpressionTree {
    
    public ExpressionTree (){}

    private Node root;
    
    public void prefix(){
    	prefix(root);
    	System.out.println();
    }
    public void prefix(Node r) {
    	if (r != null){
    		System.out.print(r+" ");
    		prefix(r.leftChild);
    		prefix(r.rightChild);
    	}
    }
    public void infix() {
    	infix(root);
    	System.out.println();
    }
    public void infix(Node r){
    	if (r != null) {
    		if (isNum(r.element+""))
    			System.out.print(r.element+" ");
    		else {
    			System.out.print("(");
    			infix(r.leftChild);
    			System.out.print(r.element+" ");
    			infix(r.rightChild);
    			System.out.print(")");
    		}
    	}
    }
    public void postfix() {
    	postfix(root);
    	System.out.println();
    }
    public void postfix(Node r){
    	if (r != null){
    		postfix(r.leftChild);
    		postfix(r.rightChild);
    		System.out.print(r+ " ");
    	}
    }
    public Node convert(String infixNotation) {
    	ArrayStack<Node> test = new ArrayStack<Node>();
    	String postFix = toPostFix(infixNotation);

    	for (int i=0; i < postFix.length();i++){
    		int space = postFix.indexOf(" ",i);
    		String str = postFix.substring(i, space);
    		if (isNum(str)){
    			Node newNode = new Node(str);
    			test.push(newNode);
    			i += str.length();
    		}
    		else{
    			Node rightChild = test.pop();
    			Node leftChild = test.pop();
    			if (isNum(rightChild.element + "") && !isNum(leftChild.element + "")){
    				Node temp = rightChild;
    				rightChild = leftChild;
    				leftChild = temp;
    			}
    			Node newNode = new Node(str,leftChild,rightChild);
    			test.push(newNode);
    			i++;
    		}
    	}
    	root = test.pop();
    	return root;
    }
    
    public String toPostFix(String infixNotation) {
		String postFix="";
		String infixToken = tokenize(infixNotation);
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		
		for (int i = 0; i < infixToken.length(); i++) {
			int space = infixToken.indexOf(" ", i);
			String operatorOrOperand = infixToken.substring(i, space);
			if (isNum(operatorOrOperand)){
				postFix += operatorOrOperand+" ";
				i += operatorOrOperand.length();
			}
			else {
				if (operatorStack.size() > 0) {
					char cmTest = operatorStack.pop();

					if ((pemdasCheck(cmTest,operatorOrOperand.charAt(0)) || operatorOrOperand.charAt(0) == '(') && operatorOrOperand.charAt(0) != ')') {
						operatorStack.push(cmTest);
						operatorStack.push(operatorOrOperand.charAt(0));
					}
					else {
						if (popAllOperators(operatorOrOperand.charAt(0))) {
							while (true) {
								if (cmTest != '(') {
									postFix += cmTest + " ";
									cmTest = operatorStack.pop();
								}
								else {
									break;
									}
							}
						}
						else {
							while (true){
								if (!pemdasCheck(cmTest,operatorOrOperand.charAt(0)) && cmTest != '(') {
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
				else {operatorStack.push(operatorOrOperand.charAt(0)); i++;}
			}
		}

		while (operatorStack.size() > 0) {
			char cmTest = operatorStack.pop();
			postFix += cmTest + " ";
		}
		return postFix;
	}
	
	private String tokenize(String infixNotation) {
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
	
	public static boolean isNum (String str) {
		try {
			int numTest = Integer.parseInt(str);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}

	// pemdasCheck returns true if the token didn't have higher precedence than what was on the stack
	// and false if the token does have a higher precedence.
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
		return opValue1 < opValue2;
	}
	
	private boolean popAllOperators (char c){
		return c == ')';
	}
}