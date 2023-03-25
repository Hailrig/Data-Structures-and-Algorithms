/** Primes lists all prime numbers from 1 to an entered number
 * @author Lucas Harvey
 * @author Student number: 192742
 */

import java.util.Scanner;

public class Primes {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the number create the list of primes from"); //gather user input
		int number = keyboard.nextInt();
		
		primeList(number); //Print list of primes

	}
	
	/** primeList lists all primes from 1 to the int inputed
	 * @param number int to list up to
	 */
	public static void primeList(int number){
		boolean isPrime;
		for (int i = 2; i < number; i++) { //loop through every number up to user's input
			isPrime = true;
			for (int x = 2; x < i; x++) { //Loop through every number between 1 and the current number
				if (i % x == 0) { //If it can be evenly divided it's not prime, set bool false
					isPrime = false;
				}
			}
			if (isPrime) //if bool isn't after loop, print that number as a prime
				System.out.println(i + " is prime");
		}
	}
}
