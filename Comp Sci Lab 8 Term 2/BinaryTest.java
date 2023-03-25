import java.util.Comparator;

public class BinaryTest<E> implements Comparator<E> {
	private int bitCount (E e) {
		if (e == null) {
			throw new RuntimeException("null");
		}
		try {
			int numberOfOnes = 0;
			String temp = Integer.toBinaryString((Integer) e);
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) == '1')
					numberOfOnes++;
			}
			return numberOfOnes;
		}
		catch(ClassCastException err) {
			throw new RuntimeException ("Not an int");
		}
	}
	
	public int compare (E a, E b) {
		int valueA = bitCount(a);
		int valueB = bitCount(b);
		return (valueA - valueB);
	}
}
