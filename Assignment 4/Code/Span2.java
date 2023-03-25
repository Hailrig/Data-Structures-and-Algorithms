/** Implementation of Span2 using Stack, includes test array
 * @author Lucas Harvey
 * @author Student number: 192742
 */

import java.util.Random;

public class Span2 {

	public static void main(String[] args) {
		Random rand = new Random();
		int n = 50;
		int[] array = new int[50];
		for (int i = 0; i < array.length; i++) //Sets up array to test
			array[i] = rand.nextInt(100);
		
		System.out.println("Array..."); //Prints array
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i]+",");
		System.out.println("");
		
		System.out.println("Time..."); //Checks time
		long startTime = System.nanoTime();
		int[] answerArray = span(n, array);
		System.out.println(System.nanoTime()-startTime);
		
		System.out.println("Span..."); //Prints result
		for (int i = 0; i < answerArray.length; i++) 
			System.out.print(answerArray[i]+",");
		
		System.out.println("");

	}
	
	/**Calculates span for the given array
	 * @param n length of the array
	 * @param x the array to check
	 * @return the array of spans
	 */
	public static int[] span(int n, int[] x){
		VectorStack<Integer> stack = new VectorStack<Integer>();
		int s[] = new int[n];
		
		for (int i = 0; i < n; i++) { //loop through the array
			while (!stack.isEmpty() && x[stack.top()] <= x[i]) { //if the stack isn't empty and the current value of x is bigger or equal remove top
				stack.pop();
			}
			if (stack.isEmpty()) { //if the stack is empty set next s to be i+1
				s[i] = i+1;
			} else { //Otherwise set it to be i-the top of the stack
				s[i] = i - stack.top();
			}
			
			stack.push(i);
		}
		return s;
	}

}