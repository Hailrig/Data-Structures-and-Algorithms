import java.util.Random;

public class FavoriteList<E> {
	protected PositionList<Entry<E>> list; // List of entries
	  /** Constructor; O(1) time */
	  public FavoriteList() { list = new NodePositionList<Entry<E>>(); }
	  
	  public static void main(String[] args) {
		  FavoriteList<Integer> favs = new FavoriteList<Integer>();
		  Random rand = new Random();
		  int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		  for (int i = 0; i < 50; i++) {
			  favs.access(array[rand.nextInt(9)]);
		  }
		  System.out.println(favs.toString());
		  System.out.println("Removing 5");
		  favs.remove(5);
		  System.out.println(favs.toString());
		  System.out.println("Getting top three");
		  System.out.println(favs.top(3).toString());
		}
	  
	  public void access(E e) {
		  if (list.isEmpty()) {
			  list.addFirst(new Entry<E>(e));
			  return;
		  }
		  Position<Entry<E>> pos = lookUp(e);
		  if (pos == null) {
			 list.addLast(new Entry<E>(e));
			  return;
		  }
		  if(pos != null) {
			  pos.element().incrementCount();
			  moveUp(pos);
		  }
		  
	  }
	  
	  protected void moveUp(Position<Entry<E>> pos) {
		  Entry<E> e = pos.element();
		  int c = pos.element().getTimesAccessed();
		  while (pos != list.first()) {
			  Position<Entry<E>> cursor = list.prev(pos);
			  if (cursor.element().getTimesAccessed() >= c) {
				  break;
			  }
			  list.set(pos, cursor.element());
			  pos = cursor;
			  
		  }
		  list.set(pos, e);
	  }
	  
	  public PositionList <E> top(int topNumber) {
		  PositionList<E> topList = new NodePositionList<E>();
		  Position<Entry<E>> cursor = list.first();
		  for (int i = 0; i < topNumber; i++) {
			  topList.addLast(cursor.element().getValue());
			  cursor = list.next(cursor);
		  }
		  return topList;
	  }
	  
	  protected Position<Entry<E>> lookUp(E e){
		   Position<Entry<E>> pos = list.first();
		   while (true) {
			   if (e == pos.element().getValue()) {
				   return pos;
			   }
			   if (pos == list.last() || pos == null) {
				   break;
			   }
			   pos = list.next(pos);
		   }
		   return null;
	  }
	  
	  public void remove(E e) {
		  if (list.isEmpty()){
			  throw new EmptyListException("Empty List");
		  }
		  Position<Entry<E>> cursor = lookUp(e);
		  if (cursor == null) {
			  throw new NoSuchElementException("No such element");
		  } else {
			  list.remove(cursor);
		  }
	  }
	  
	  public String toString() {
		  return list.toString();
	  }

	protected static class Entry<E> {
		private E value;
		private int timesAccessed;
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
