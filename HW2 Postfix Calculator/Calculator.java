public class Calculator {

	public static void main(String[] args) {
		Converter x = new Converter("408+(6+2^(5-1))");
		String converted = x.toPostFix();
		ArrayStack<Double> stack = new ArrayStack<>();
		
		for (int i = 0; i < converted.length(); i++) {
			int space = converted.indexOf(" ",i);
			String str = converted.substring(i, space);
			if(Converter.isNum(str)) {
				stack.push(Double.parseDouble(str));
				i += str.length();
			}
			else {
				double num1 = stack.pop();
				double num2 = stack.pop();
				double newNum = evaluate(num1,num2,str.charAt(0));
				stack.push(newNum);
				i++;
			}
		}
		
		double answer = stack.pop();
		System.out.println("This is the answer to the postFix expression: " + answer);
	}
	private static double evaluate(double num1, double num2, char operator) {
		double solution = 0.0;
		switch (operator) {
			case '+': solution = num2 + num1;
				break;
			case '-': solution = num2 - num1;
				break;
			case '*': solution = num2 * num1;
				break;
			case '/': solution = num2 / num1;
				break;
			case '^': solution = Math.pow(num2, num1);
				break;
			default: System.out.println("Invalid operator");
				break;
		}
		return solution;
	}
}
