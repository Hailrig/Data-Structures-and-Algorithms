import java.util.Random;

/**Sorts values using binary search, can be returned by getting it's iterator
 * @author Lucas Harvey 192742
 * @param <E> type of values
 */
public class BinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
	/**Adds an element to the tree to the correct spot
	 * @param element element to add
	 */
	public void insert(E element) {
		if (size == 0) { //Adds the root if it's missing
			addRoot(element);
		} else {
			Position<E> cursor = root; //sets the cursor to the root
			while (true) {
				if(element.compareTo(cursor.element()) <= 0){ //Goes down left path
					if (hasLeft(cursor)) {
						cursor = left(cursor);
					} else {
						insertLeft(cursor, element);
						break;
					}
				} else { //Goes down right path
					if (hasRight(cursor)) {
						cursor = right(cursor);
					} else {
						insertRight(cursor, element);
						break;
					}
				}
			}
		}
	}
	
	/** Inner class for entries
	 * @author Lucas Harvey 192742
	 *
	 * @param <K> Key value for comparing
	 * @param <V> Value of element
	 */
	protected static class BSTEntry<K extends Comparable<K>,V> implements Comparable<BSTEntry<K,V>>{
		K key;
		V value;
		public BSTEntry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		public String toString() {
			return "[K: " + key + ", V: " + value + "]";
		}

		@Override
		public int compareTo(BSTEntry<K,V> other) {
			return key.compareTo(other.key);
		}
		
	}
	
	public static void main(String[] args) {
		BinarySearchTree<BSTEntry<Integer, Integer>> tree = new BinarySearchTree<BSTEntry<Integer, Integer>>(); 
		Random rand = new Random();
		System.out.println("Unsorted key and value pairs:");
		for (int i = 0; i < 10; i++) { //loop through adding values to tree
			int key = rand.nextInt(100);
			int value = rand.nextInt(100);
			System.out.print("[K: " + key + ", V: " + value + "], ");
			tree.insert(new BSTEntry<Integer, Integer>(key, value));
		}
		System.out.println(""); //Output sorted values
		System.out.println("Sorted key and value pairs:");
		for (BSTEntry<Integer,Integer> i : tree.iterator()) {
			System.out.print(i + ", ");
		}
	}
}
