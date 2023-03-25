import java.util.Comparator;
/** Implementation of a priority queue by means of a sorted node list. */
public class SortedListPriorityQueue < K, V > implements PriorityQueue < K, V > {
    protected PositionList < Entry < K, V >> entries;
    protected Comparator < K > c;
    protected Position < Entry < K, V >> actionPos; // used by subclass
    /** Inner class for entries */
    protected static class MyEntry < K, V > implements Entry < K, V > {
        protected K k; // key
        protected V v; // value
        public MyEntry(K key, V value) {
            k = key;
            v = value;
        }
        // methods of the Entry interface
        public K getKey() {
            return k;
        }
        public V getValue() {
            return v;
        }
        public String toString() {
            return "[" + k + "," + v + "]";
        }
    }
    /** Creates the priority queue with the default comparator. */
    public SortedListPriorityQueue() {
    	entries = new NodePositionList < Entry < K, V >> ();
        c = new DefaultComparator < K > ();
    }
    /** Creates the priority queue with the given comparator. */
    public SortedListPriorityQueue(Comparator < K > comp) {
    	entries = new NodePositionList < Entry < K, V >> ();
        c = comp;
    }

    public int size() {
        return entries.size();
    }
    public boolean isEmpty() {
        return entries.isEmpty();
    }
    /** Returns but does not remove an entry with minimum key. */
    public Entry < K, V > min() throws EmptyPriorityQueueException {
    	try {
        return entries.first().element();
    	} catch(EmptyPriorityQueueException e) {
    		throw new EmptyPriorityQueueException("empty queue");
    	}
    }
    /** Inserts a key-value pair and return the entry created. */
    public Entry < K, V > insert(K k, V v) throws InvalidKeyException {
    	try {
    		MyEntry<K, V> newEntry = new MyEntry<K, V>(k, v);
    		insertEntry(new MyEntry<K, V>(k, v));
    		return newEntry;
    	} catch(InvalidKeyException e) {
    		throw new InvalidKeyException("key is invalid");
    	}
    }
    /** Determines whether a given key is valid */
    protected void checkKey(K key) throws InvalidKeyException {
        if (key == null)
            throw new InvalidKeyException("Invalid key");
    }
    /** Auxiliary method used for insertion. */
    protected void insertEntry(Entry < K, V > e) {
        if (entries.isEmpty()) {
            entries.addFirst(e); // insert into empty list
            actionPos = entries.first(); // insertion position
        } else if (c.compare(e.getKey(), entries.last().element().getKey()) > 0) {
            entries.addLast(e); // insert at the end of the list
            actionPos = entries.last(); // insertion position
        } else {
            Position < Entry < K, V >> curr = entries.first();
            while (c.compare(e.getKey(), curr.element().getKey()) > 0) {
                curr = entries.next(curr); // advance toward insertion position
            }
            entries.addBefore(curr, e);
            actionPos = entries.prev(curr); // insertion position
        }
    }
    /** Removes and returns an entry with minimum key. */
    public Entry < K, V > removeMin() throws EmptyPriorityQueueException {
    	try {
    		return entries.remove(entries.first());
    	} catch(EmptyPriorityQueueException e) {
    		throw new EmptyPriorityQueueException("empty queue");
    	}
    }

    public static void main(String[] args) {
        SortedListPriorityQueue < Integer, Integer > SPQ =
            new SortedListPriorityQueue < Integer, Integer > ();
        int n = 10;
        System.out.println("Inserted entries are: \n");
        for (int i = 0; i <= n; ++i) {
            int s = 100 - i;
            int j = i + 1;
            SPQ.insert(s, j);
            System.out.println(SPQ.insert(s, j));
        }
        System.out.println("\nSorted entries by default  are: \n");
        for (int i = 0; i <= n; ++i) {
            SPQ.removeMin();
            System.out.println(SPQ.removeMin());
        }
        SortedListPriorityQueue < String, String > SSPQ =
            new SortedListPriorityQueue < String, String > ();
        String[] SArray = {
            "Hello",
            "Study",
            "Chenkuan Li",
            "John Chen",
            "Jenna Li"
        };
        System.out.println();
        System.out.println("\nUnorted strings are: \n");
        for (String s: SArray) {
            Entry < String, String > e = SSPQ.insert(s, s);
            System.out.println(e);
        }
        System.out.println("\nSorted strings by default are: \n");
        for (int i = 0; i <= SArray.length - 1; ++i) {
            Entry < String, String > e = SSPQ.removeMin();
            System.out.println(e);
        }
        System.out.println();
        SortedListPriorityQueue < Integer, Integer > SPQC = new SortedListPriorityQueue < Integer, Integer > (new IntegerComparator<Integer>());
        System.out.println("Inserted entries are: \n");
        for (int i = 0; i <= n; ++i) {
            SPQC.insert(i, i);
            System.out.println(SPQC.insert(i, i));
        }
        System.out.println("\nSorted entries by the integerComparator are: \n");
        for (int i = 0; i <= n; ++i) {
            SPQC.removeMin();
            System.out.println(SPQC.removeMin());
        }
        System.out.println();
        SortedListPriorityQueue < Integer, Integer > SPQCB = new SortedListPriorityQueue < Integer, Integer > (new BinaryTest<Integer>());
        System.out.println("Inserted entries are: \n");
        for (int i = 0; i <= n; ++i) {
            SPQCB.insert(i, i);
            System.out.println(SPQCB.insert(i, i));
        }
        System.out.println("\nSorted entries by the BinaryTest are: \n");
        for (int i = 0; i <= n; ++i) {
            SPQCB.removeMin();
            System.out.println(SPQCB.removeMin());
        }
        System.out.println();

    }
}