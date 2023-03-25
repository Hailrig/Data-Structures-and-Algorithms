/** Implementation of Vector using array
 * @author Lucas Harvey
 * @author Student number: 192742
 */

public class Vector<E> {
	
	private E[] array;
	private int storedValues = 0;
	private int START_SIZE = 10;
	
	@SuppressWarnings("unchecked")
	public Vector() {
		array = (E[]) new Object[START_SIZE];
	}
	
	public E elemAtRank(int r) {
		return array[r];
	}
	
	/** Replaces specific rank with specified element
	 * @param r rank to replace
	 * @param o element to replace with
	 * @return replaced element
	 */
	public E replaceAtRank(int r, E o) {
		E toReturn = array[r];
		array[r] = o;
		return toReturn;
	}
	
	/** Inserts specified element at specified rank
	 * @param r rank to insert at
	 * @param o element to insert
	 */
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
	
	/**Removes element at specific rank
	 * @param r rank to delete
	 * @return deleted element
	 */
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
	
	/**Gets whether the array is empty
	 * @return true or false depending on if it is empty
	 */
	public boolean isEmpty() {
		if (storedValues == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**Converts the vector to readable values
	 * @return the string
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i < storedValues; i++) {
			output += array[i]+",";
		}
		return output;
	}
	
	/**Doubles the size of the array
	 */
	@SuppressWarnings("unchecked")
	public void grow() { 
		E[] holdingArray = (E[]) new Object[storedValues]; //Temporary array to store values
		for (int i = 0; i < array.length; i++) {	//pushes values to temp array
			holdingArray[i] = array[i];
		}
		array = (E[]) new Object[storedValues*2]; //Doubles array size
		for (int i = 0; i < holdingArray.length; i++) { //Pushes values back
			array[i] = holdingArray[i];
		}
	}
}
