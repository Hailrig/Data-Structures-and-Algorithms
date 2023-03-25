
public class PrefixAverages {
	public static void main(String[] args) {
		System.out.println("Array size of five");
		int[] array = {1,2,3,4,5};
		int[] averagedArrayOne = prefixAveragesSlow(array, 5);
		int[] averagedArrayTwo = prefixAverages(array, 5);
		
		System.out.println("Array 1");
		for (int i = 0; i < array.length; i++) {
			System.out.println(averagedArrayOne[i]);
		}
		
		System.out.println("Array 2");
		for (int i = 0; i < array.length; i++) {
			System.out.println(averagedArrayTwo[i]);
		}
		
		System.out.println("Array size of twenty");
		int[] array2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		averagedArrayOne = prefixAveragesSlow(array2, 20);
		averagedArrayTwo = prefixAverages(array2, 20);
		
		System.out.println("Array 1");
		for (int i = 0; i < array2.length; i++) {
			System.out.println(averagedArrayOne[i]);
		}
		
		System.out.println("Array 2");
		for (int i = 0; i < array2.length; i++) {
			System.out.println(averagedArrayTwo[i]);
		}


	}

	public static int[] prefixAveragesSlow(int[] x, int n) {
		long time = System.nanoTime();
		int [] a = new int [n];
		for (int i = 0; i < n; i++) {
			int s = x[0];
			for (int j = 1; j <= i; j++) {
				s = s + x[j];
			}
			a[i] = s/(i+1);
		}
		long time2 = System.nanoTime();
		System.out.println("Time for array 1, N^2: " + (time2 - time));
		return a;
	}
	
	public static int[] prefixAverages(int[] x, int n){
		long time = System.nanoTime();
		int [] a = new int[n];
		int runningTotal = 0;
		for (int i = 0; i < n; i++) {
			runningTotal = runningTotal += x[i];
			a[i] = runningTotal/(i+1);
		}
		long time2 = System.nanoTime();
		System.out.println("Time for array 2, N: " + (time2 - time));
		return a;
	}
}
