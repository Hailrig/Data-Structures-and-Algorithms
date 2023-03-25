/** Implementation stack using vector
 * @author Lucas Harvey
 * @author Student number: 192742
 */

import java.util.EmptyStackException;

public class VectorStack<E> {
	
	Vector <E> stack = new Vector<E>();;
	int max = 10;
	int noOfValues = 0;
	

	public int size() {
		return noOfValues;
	}

	/** Returns whether the array is empty
	 * @return whether the array is empty
	 */
	public boolean isEmpty() {
		if (noOfValues <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/** gets top element
	 * @return top element of the stack
	 * @throws EmptyStackException
	 */
	public E top() throws EmptyStackException {
		return (E) stack.elemAtRank(noOfValues - 1);
	}

	/**Adds element
	 * @param element to add 
	 */
	public void push(E element) {
		if (noOfValues < max) {
			stack.insertAtRank(noOfValues, element);
			noOfValues++;
		} else {
			grow();
			stack.insertAtRank(noOfValues, element);
			noOfValues++;
		}
	}

	/**Removes top element
	 * @return element removed
	 * @throws EmptyStackException
	 */
	public E pop() throws EmptyStackException {
		E returnValue = (E) stack.elemAtRank(noOfValues-1);
		stack.removeAtRank(noOfValues-1);
		noOfValues--;
		return returnValue;
	}
	
	/**Sets up stack to be read
	 * @return string
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i < noOfValues; i++) {
			output += stack.elemAtRank(i)+",";
		}
		return output;
	}
	
	/**Calls grow of the Vector
	 */
	private void grow() {
		stack.grow();
		max = max*2;
	}

}
