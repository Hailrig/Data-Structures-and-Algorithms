import java.util.Comparator;

public class IntegerComparator<E> implements Comparator<E> {
	private int bitCount (E e) {
		if (e == null) {
			throw new RuntimeException("null");
		}
		try {
			int numberOfOnes = 0;
			int temp = ((Integer) e).intValue();
			while (temp != 0) {
				int bit = temp & 1;
				if (bit == 1)
					numberOfOnes++;
				temp = temp >> 1;
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
