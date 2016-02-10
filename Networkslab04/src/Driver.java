/**
 * This program creates a separate thread by implementing the Runnable interface.
 *
 * Figure 4.11
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

/**
 * The instance of the shared object
 */

class Sum
{
	private int sum;

	public int get() {
		return sum;
	}

	public void set(int sum) {
		this.sum = sum;
	}
}

/**
 * This runs as a separate Java thread.
 *
 * This performs a summation from 1 .. upper 
 */
class Summation2 implements Runnable
{
	private int upper;
	private Sum sumValue;

	public Summation2(int upper, Sum sumValue) {
		this.upper = upper;
		this.sumValue = sumValue;
	}

	public void run() {
		int sum = 0;

		for (int i = 1; i <= upper; i++)
			sum += i;

		sumValue.set(sum);
	}
}

public class Driver
{
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage java Driver <integer>");
			System.exit(0);
		}
		
		if (Integer.parseInt(args[0]) < 0) {
			System.err.println(args[0] + " must be >= 0");
			System.exit(0);
		}

		// Create the shared object
		Sum sumObject = new Sum();
		int upper = Integer.parseInt(args[0]);
		
		Thread worker = new Thread(new Summation2(upper, sumObject));
		worker.start();
			
		try {
			worker.join();
		} catch (InterruptedException ie) { System.err.println(ie); }
		System.out.println("The sum of " + upper + " is " + sumObject.get());
	}
}
