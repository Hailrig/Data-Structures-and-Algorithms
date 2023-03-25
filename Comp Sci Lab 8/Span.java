
public class Span {

	public static void main(String[] args) {
		int n = 9;
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println("Max of array 1: " + maxSpan(n, array));
		
		int n2 = 9;
		int[] array2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
		System.out.println("Max of array 2: " + maxSpan(n2, array2));
		
		int n3 = 9;
		int[] array3 = {1, 2, 3, 4, 5, 4, 3, 2, 1};
		System.out.println("Max of array 3: " + maxSpan(n3, array3));
		
		int n4 = 9;
		int[] array4 = {5, 4, 3, 2, 1, 2, 3, 4, 5};
		System.out.println("Max of array 4: " + maxSpan(n4, array4));
		
		int n5 = 9;
		int[] array5 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
		System.out.println("Max of array 5: " + maxSpan(n5, array5));
		
		int n6 = 0;
		int[] array6 = {};
		System.out.println("Max of array 6: " + maxSpan(n6, array6));
	}
	
	public static int maxSpan(int n, int[] x){
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		int s[] = new int[n];
		int highestSpan = 0;
		
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && x[stack.top()] <= x[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				s[i] = i+1;
			} else {
				s[i] = i - stack.top();
			}
			
			if (s[i] > highestSpan) {
				highestSpan = s[i];
			}
			stack.push(i);
		}
		return highestSpan;
	}

}
