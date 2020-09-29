import java.util.Scanner;
public class Sieve {

	private ArrayQueue<Integer> numbers = new ArrayQueue<>();
	private ArrayQueue<Integer> primes = new ArrayQueue<>();
	
	public Sieve() {}
	
	public void primesTo(int upperBound) throws IllegalArgumentException{
		for (int i = 2; i <= upperBound; i++){
			numbers.enqueue(i);
		}
		
		double maxLength = Math.sqrt(upperBound);
		int p = 2;
		if (upperBound < 2) {
			throw new IllegalArgumentException("Error: Upper Bound must be greater than or equal to 2.");
		}
		else {
		while (p <= maxLength) {
			ArrayQueue<Integer> numbersSaved = new ArrayQueue<>();
			while(numbers.size() > 0) {
				int dequeued = numbers.dequeue();
				if (dequeued%p != 0 || dequeued == p)
					numbersSaved.enqueue(dequeued);
			}
			numbers = numbersSaved;
			p++;
		}
		transferToPrime();
		System.out.println("Here are all the primes: "+primes.toString());
		}
	}
	
	private void transferToPrime() {
		for (int i = 0; i < numbers.size();) {
			int temp = numbers.dequeue();
			primes.enqueue(temp);
		}
	}
	public static void main(String[] args){
		Sieve newSieve = new Sieve();
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter an upper bound: ");
		int userNum = input.nextInt();
		newSieve.primesTo(userNum);
	}
}
