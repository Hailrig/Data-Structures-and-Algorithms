import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
	
	private E[] stack;
	final int DEFAULT_MAX = 10;
	int max;
	int noOfValues = 0;
	
	public static void main(String[] args) {
		Integer[] array1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		String[] array2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		ArrayStack<Integer> stack1 = new ArrayStack<Integer>(5);
		ArrayStack<String> stack2 = new ArrayStack<String>(5);
		for (int i = 0; i < array1.length; i++) {
			stack1.push(array1[i]);
		}
		for (int i = 0; i < array2.length; i++) {
			stack2.push(array2[i]);
		}
		System.out.println("Base arrays: ");
		System.out.println(stack1.toString());
		System.out.println(stack2.toString());
		stack1.reverse();
		stack2.reverse();
		System.out.println("Flipped arrays: ");
		System.out.println(stack1.toString());
		System.out.println(stack2.toString());
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		max = DEFAULT_MAX;
		stack = (E[]) new Object[this.max];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int max) {
		this.max = max;
		stack = (E[]) new Object[this.max];
	}

	@Override
	public int size() {
		return noOfValues;
	}

	@Override
	public boolean isEmpty() {
		if (noOfValues <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public E top() throws EmptyStackException {
		return stack[noOfValues - 1];
	}

	@Override
	public void push(E element) {
		if (noOfValues < max) {
			stack[noOfValues] = element;
			noOfValues++;
		} else {
			grow();
			stack[noOfValues] = element;
			noOfValues++;
		}
	}

	@Override
	public E pop() throws EmptyStackException {
		E returnValue = stack[noOfValues-1];
		noOfValues--;
		return returnValue;
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < noOfValues; i++) {
			output += stack[i]+",";
		}
		return output;
	}
	
	@SuppressWarnings("unchecked")
	private void grow() {
		E holdingStack[] = (E[]) new Object[max];
		for (int i = 0; i < max; i++) {
			holdingStack[i] = stack[i];
		}
		max = max*2;
		stack = (E[]) new Object[max];
		for (int i = 0; i < holdingStack.length; i++) {
			stack[i] = holdingStack[i];
		}
	}
	
	@SuppressWarnings("unchecked")
	public void reverse() {
		E[] tempArray = (E[]) new Object[noOfValues];
		for (int i = noOfValues-1; i >= 0; i--) {
			tempArray[i] = stack[i];
			pop();
		}
		for (int i = tempArray.length-1; i >= 0; i--) {
			push(tempArray[i]);
		}
		
	}

}
