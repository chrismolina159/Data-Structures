import java.util.*;

public class InputTest {

	public static void main(String[] args) {
		ExpressionTree testing = new ExpressionTree();
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("Type your expression:");
			String userEquation = input.nextLine();
		
			Node root = testing.convert(userEquation);
		
			System.out.print("This is the prefix notation: ");
			testing.prefix();
			System.out.print("This is the infix notation: ");
			testing.infix();
			System.out.print("This is the postfix notation: ");
			testing.postfix();
			System.out.println("Do you want to type in another expression? 'Yes' to continue 'No' to exit");
			String contin = input.nextLine();
			if (contin.equals("No"))
				break;
		}
		input.close();
	}

}
