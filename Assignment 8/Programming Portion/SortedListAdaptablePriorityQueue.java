import java.util.Comparator;
import java.util.Random;

/**Backpointer based priority queue 
 * @author Lucas Harvey 192742
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class SortedListAdaptablePriorityQueue<K,V> extends SortedListPriorityQueue<K,V> implements AdaptablePriorityQueue<K,V> {
	
	public SortedListAdaptablePriorityQueue(){
		super();
	}
	
	public SortedListAdaptablePriorityQueue(Comparator < K > comp){
		super(comp);
	}
	
	/** Inner class to implement backpointers, extends other inner class with the rest of the inner class deets
	 * @author Lucas Harvey 192742
	 *
	 * @param <K> Key type
	 * @param <V> Value type
	 */
	protected static class BackEntry < K, V > extends MyEntry < K, V > {
		protected Position<Entry<K, V>> pos;
        public BackEntry(K key, V value) {
			super(key, value);
		}
        public void setPos(Position<Entry<K, V>> pos) {
        	this.pos = pos;
        }
        
        public Position<Entry<K, V>> getPos(){
        	return pos;
        }
    }
	
	/**Verifies entry is backpointer compatible
	 * @param Entry to check
	 * @return casted version of the entry
	 * @throws InvalidPositionException if it's not a valid backpointer subclass
	 */
	public BackEntry<K, V> checkBack(Entry<K, V> entry) throws InvalidPositionException{
		if (entry != null && entry instanceof BackEntry) {
			return (BackEntry<K, V>) entry;
		} else
			throw new InvalidPositionException("Not a backentry");
	}
	
	/**new insert to set up the backpointers
	 *@param k key to insert
	 *@param v value to insert
	 *@return returns entry inserted
	 */
	public Entry < K, V > insert(K k, V v) throws InvalidKeyException {
    	try {
    		BackEntry<K, V> newEntry = new BackEntry<K, V>(k, v);
    		insertEntry(new MyEntry<K, V>(k, v));
    		newEntry.setPos(actionPos);
    		return newEntry;
    	} catch(InvalidKeyException e) {
    		throw new InvalidKeyException("key is invalid");
    	}
    }

	/**Removes entry from ze tree
	 *@param Entry entry to delete
	 *@return deleted entry
	 */
	@Override
	public Entry<K, V> remove(Entry<K, V> entry) {
		BackEntry<K, V> backEntry = checkBack(entry);
		return entries.remove(backEntry.getPos());
	}

	/**Replaces value of a specifc entry
	 *@param e entry to replace value on
	 *@param value value to replace
	 *@return old value
	 */
	@Override
	public V replaceValue(Entry<K, V> e, V value) {
		BackEntry<K, V> backEntry = checkBack(e);
		V returnVal = backEntry.getValue();
		backEntry.setValue(value);
		return returnVal;
	}

	/**Replaces key of a specific entry
	 *@param e entry to replace key on
	 *@param k key to replace
	 *@return old key
	 */
	@Override
	public K replaceKey(Entry<K, V> entry, K k) {
		BackEntry<K, V> backEntry = checkBack(entry);
		K returnK = backEntry.getKey();
		insert(k, backEntry.getValue()); //reinserts new entry
		entries.remove(backEntry.getPos()); //removes old entry
		return returnK;
	}
	
    public static void main(String[] args) {
    	SortedListAdaptablePriorityQueue<Integer, Integer> SLAPQD = new SortedListAdaptablePriorityQueue<Integer, Integer>();
    	SortedListAdaptablePriorityQueue<Integer, Integer> SLAPQB = new SortedListAdaptablePriorityQueue<Integer, Integer>(new IntegerComparator<Integer>());
    	Random rand = new Random();
    	
    	System.out.println("Unsorted:   (K, V)");
    	for (int i = 0; i < 10; i++) {
    		int randV = rand.nextInt(10);
    		int randK = rand.nextInt(10);
    		SLAPQD.insert(randK, randV);
    		SLAPQB.insert(randK, randV);
    		System.out.println("[" + randK + "," + randV + "]");
    	}
    	
    	System.out.println("\r\nSorted by integer value:   (K, V)");
    	for (int i = 0; i < 10; i++) {
            System.out.println(SLAPQD.removeMin());
        }
    	
    	System.out.println("\r\nSorted by number of binary ones:   (K, V)");
    	for (int i = 0; i < 10; i++) {
            System.out.println(SLAPQB.removeMin());
        }
    	
    	System.out.println("\r\nAdding fixed values to the queue");
    	Entry<Integer, Integer> change = null;
    	for (int i = 1; i <= 10; i++) {
    		change = SLAPQD.insert(i, i);
    		System.out.println("[" + i + "," + i + "]");
    	}
    	
    	System.out.println("\r\nReplacing value of last entry...");
    	SLAPQD.replaceValue(change, 100);
    	System.out.println("Replacing key of last entry...");
    	SLAPQD.replaceKey(change, 0);
    	
    	System.out.println("\r\nOutput:   (K, V)");
    	for (int i = 0; i < 10; i++) {
            System.out.println(SLAPQD.removeMin());
        }
    	
    	System.out.println("\r\nAdding 0,0 1,1 and 2,2 to the list...");
    	System.out.println("Deleting second value...");
    	Entry<Integer, Integer> first = SLAPQD.insert(0, 0);
    	Entry<Integer, Integer> second = SLAPQD.insert(1, 1);
    	Entry<Integer, Integer> third = SLAPQD.insert(2, 2);
    	SLAPQD.remove(second);
    	System.out.println("\r\nOutput:");
    	for (int i = 0; i < 2; i++) {
            System.out.println(SLAPQD.removeMin());
        }
    }

}





























