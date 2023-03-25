/** Gcd takes two ints and returns the gcd plus whether they are co-prime
 * @author Lucas Harvey
 * @author Student number: 192742
 */

import java.lang.Math;

public class Gcd {

	public static void main(String[] args) {
		int x = 7;
		int y = -20;
		int gcd = gcd(x, y); //return gcd
		boolean coPrime = coPrime(gcd); //return whether it is coprime
		System.out.println("gcd (" + x + ", " + y + ") = " + gcd + ".");
		System.out.println("co-prime? : " + coPrime + ".");
	}
	
	/** calculates and returns gcd
	 * @param x int value one
	 * @param y int value two
	 * @return int the gcd
	 */
	public static int gcd(int x, int y) {
		x = Math.abs(x); //change to absolute numbers
		y = Math.abs(y);
		
		int countNumber;
		int gcd = 0; //get lowest absolute number
		if (x - y >= 0) {
			countNumber = y;
		} else {
			countNumber = x;
		}
		
		for (int i = 1; i <= countNumber; i++) { //itterate through all potential values, dividing by the incrementing value 
			if ((x % i == 0) && y % i == 0) { //if the current value is cleanly divisible with both numbers, save it
				gcd = i;
			}
		}
		return gcd; 
	}
	
	/**
	 * @param int x the number to check if it indicates co-primeness
	 * @return boolean dependant on whether it is coprime or not 
	 */
	public static boolean coPrime(int x) {
		if (x == 1) { //indicates it is coprime
			return true;
		}
		else {
			return false;
		}
	}

}
