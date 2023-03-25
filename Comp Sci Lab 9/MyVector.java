
public class MyVector<E> {
	
	private E[] array;
	private int storedValues = 0;
	private int START_SIZE = 10;

	public static void main(String[] args) {
		System.out.println("Creating array...");
		MyVector<Integer> vector = new MyVector<Integer>();
		
		System.out.println("Inserting values into array...");
		vector.insertAtRank(0, 3);
		vector.insertAtRank(1, 4);
		vector.insertAtRank(0, 2);
		vector.insertAtRank(0, 1);
		vector.insertAtRank(4, 5);
		vector.insertAtRank(5, 6);
		vector.insertAtRank(6, 7);
		vector.insertAtRank(7, 8);
		vector.insertAtRank(8, 9);
		vector.insertAtRank(9, 10);
		vector.insertAtRank(10, 11);
		
		System.out.println(vector.toString());
		
		System.out.println("Getting element at rank two...");
		System.out.println(vector.elemAtRank(2));
		
		System.out.println("Replacing element 0 with 5...");
		vector.replaceAtRank(0, 5);
		System.out.println(vector.toString());
		
		System.out.println("Removing rank 2...");
		vector.removeAtRank(2);
		System.out.println(vector.toString());
		
		System.out.println("Getting size...");
		System.out.println(vector.size());
		
		System.out.println("Getting whether the array is empty...");
		System.out.println(vector.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	public MyVector() {
		array = (E[]) new Object[START_SIZE];
	}
	
	public E elemAtRank(int r) {
		return array[r];
	}
	
	public E replaceAtRank(int r, E o) {
		E toReturn = array[r];
		array[r] = o;
		return toReturn;
	}
	
	public void insertAtRank(int r, E o) {
		if ((storedValues+1) > array.length) {
			grow();
		}
		for (int i = storedValues; i > r; i--) {
			array[i] = array[i-1];
		}
		storedValues++;
		array[r] = o;
	}
	
	public E removeAtRank(int r) {
		E toReturn = array[r];
		for (int i = r; i < storedValues-1; i++) {
			array[i] = array[i+1];
		}
		storedValues--;
		return toReturn;
	}
	
	public int size() {
		return storedValues;
	}
	
	public boolean isEmpty() {
		if (storedValues == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < storedValues; i++) {
			output += array[i]+",";
		}
		return output;
	}
	
	@SuppressWarnings("unchecked")
	private void grow() { //I decided to use a double n based strategy as it would require running this piece of computation less frequently 
		E[] holdingArray = (E[]) new Object[storedValues];			//if you had a large amount of objects to feed in and needed it to grow substantially
		for (int i = 0; i < array.length; i++) {					//(Similar argument to the one Stuart raised in class)
			holdingArray[i] = array[i];
		}
		array = (E[]) new Object[storedValues*2];
		for (int i = 0; i < holdingArray.length; i++) {
			array[i] = holdingArray[i];
		}
	}
}
