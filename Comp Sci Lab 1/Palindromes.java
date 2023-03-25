/** Palindromes takes an int and determines if it is a palindrome
 * @author Lucas Harvey
 * @author Student number: 192742
 */

import java.util.Scanner;

public class Palindromes {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the number to test:"); //Gather user data for the int to check
		int number = keyboard.nextInt();
		
		if (isPalindrome(number)) { //check if it is a palindrome, output results
			System.out.println(number + " is a palindrome.");
		} else {
			System.out.println(number + " isn't a palindrome.");
		}

	}
	
	/** isPalindrome checks an int and returns true or false depending on whether 
	 * @param number int to check for being a palindrome
	 * @return boolean depending on whether it is a palindrome
	 */
	public static boolean isPalindrome(int number) {
		int remainder = 0;
		int backwardsNumber = 0;
		int tempNumber = number;
		while (tempNumber != 0) { //Loop through each digit of the original number and load it backwards into a variable
			remainder = tempNumber % 10;
			backwardsNumber = backwardsNumber * 10 + remainder;
			tempNumber /= 10;
		}
		
		if (backwardsNumber == number) { //If the backwards number equals the forwards number return true, else return false
			return true;
		} else {
			return false;
		}
	}
}