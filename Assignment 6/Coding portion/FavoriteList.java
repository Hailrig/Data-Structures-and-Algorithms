/**Sorts list by elements most accessed
 * @author Lucas Harvey 192742
 *
 * @param <E> Generic to use
 */
public class FavoriteList<E> {
	protected PositionList<Entry<E>> list; // List of entries
	int size = 0;
	  /** Constructor; O(1) time */
	  public FavoriteList() { list = new NodePositionList<Entry<E>>(); }
	  
	  /**Accesses an element, increasing and moving as nessesary
	 * @param e element to access
	 */
	public void access(E e) {
		  if (list.isEmpty()) { //If the list is empty just add the term
			  list.addFirst(new Entry<E>(e));
			  size++;
			  return;
		  }
		  Position<Entry<E>> pos = lookUp(e);
		  if (pos == null) { //if the term isn't in the list, add it
			 list.addLast(new Entry<E>(e));
			 size++; 
			 moveUp(list.last());
			 return;
		  }
		  if(pos != null) { //if it's already in the list increment the times we've seen it and move it
			  pos.element().incrementCount();
			  moveUp(pos);
		  }
		  
	  }
	  
	  /**Move an element up to it's appropriate spot in the list
	 * @param pos the element to move
	 */
	protected void moveUp(Position<Entry<E>> pos) {
		  Entry<E> e = pos.element(); 
		  int c = pos.element().getTimesAccessed();
		  while (pos != list.first()) { //until we reach the start of the list
			  Position<Entry<E>> cursor = list.prev(pos); 
			  if (cursor.element().getTimesAccessed() >= c) {//compare to see if our cursor is where it should be
				  break;
			  }
			  list.set(pos, cursor.element());//Set the cursor to be the correct element
			  pos = cursor;
			  
		  }
		  list.set(pos, e);
	  }
	  
	  /**Returns the top n accessed terms
	 * @param topNumber number of terms to return
	 * @return the list of top terms
	 */
	public PositionList <E> top(int topNumber) {
		  PositionList<E> topList = new NodePositionList<E>();
		  Position<Entry<E>> cursor = list.first();
		  for (int i = 0; i < topNumber; i++) { //loop through top k termns and add them to the list
			  topList.addLast(cursor.element().getValue());
			  cursor = list.next(cursor);
		  }
		  return topList;
	  }
	  
	  /**Check to see where an element is in the list
	 * @param e element to check
	 * @return pos of the element, null if isn't in the list
	 */
	protected Position<Entry<E>> lookUp(E e){
		   Position<Entry<E>> pos = list.first();
		   while (true) { //search through the list
			   if (e == pos.element().getValue()) {
				   return pos; //If we've found it return the pos
			   }
			   if (pos == list.last() || pos == null) { //if we hit the end of the list
				   break;
			   }
			   pos = list.next(pos);
		   }
		   return null;
	  }
	  
	  /**Remove a particular element from the list
	 * @param e element to remove
	 */
	public void remove(E e) {
		  if (list.isEmpty()){
			  throw new EmptyListException("Empty List");
		  }
		  Position<Entry<E>> cursor = lookUp(e); //get the entry
		  if (cursor == null) {
			  throw new NoSuchElementException("No such element");
		  } else {
			  size--; //Remove the entry
			  list.remove(cursor);
		  }
	  }
	  
	  public int size() {
		  return size;
	  }
	  
	  public String toString() {
		  return list.toString();
	  }

	/** Internal helper class to store times accessed
	 * @author Lucas Harvey 192742
	 *
	 * @param <E> generic of the list
	 */
	protected static class Entry<E> {
		private E value; //The element
		private int timesAccessed; //Times it's been accessed
		Entry(E v){
			timesAccessed = 1; value = v;
		}
		public E getValue() {
			return value;
		}
		public int getTimesAccessed() {
			return timesAccessed;
		}
		public void incrementCount() {
			timesAccessed++;
		}
		public String toString() {
			return value + " (accessed " + timesAccessed + " time(s)) ";
		}
	}
}
