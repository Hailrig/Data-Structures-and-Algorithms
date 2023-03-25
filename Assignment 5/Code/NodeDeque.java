

public class NodeDeque<E> implements Deque<E> {
  protected DLNode<E> header, trailer;  // sentinels
  protected int size;    // number of elements
  public NodeDeque() {  // initialize an empty deque
    header = new DLNode<E>(null, null, null);
    trailer = new DLNode<E>(null, header, null);
    header.setNext(trailer);  // make header point to trailer

    size = 0;
  }

   public int size() {
      return size;
   }

   public boolean isEmpty() {
       if (size ()== 0)
         return true;
       return false;
   }

   public E getFirst() throws EmptyDequeException {
	   if (isEmpty())
         throw new EmptyDequeException("Deque is empty.");
       return header.getNext().getElement();
   }

   public E getLast() throws EmptyDequeException {
	   if (isEmpty())
         throw new EmptyDequeException("Deque is empty.");
       return trailer.getPrev().getElement();
   }

   public void addFirst(E o) {
       DLNode<E> second = header.getNext();
       DLNode<E> first = new DLNode<E>(o, header, second);
       second.setPrev(first);
       header.setNext(first);
       size++;
   }


   public void addLast (E element)
   {
	   DLNode<E> lastsecond = trailer.getPrev();
	   DLNode<E> last = new DLNode<E>(element, lastsecond, trailer);
	   lastsecond.setNext(last);
	   trailer.setPrev(last);
	   size++;
   }


   public E removeLast() throws EmptyDequeException {
       if (isEmpty())
         throw new EmptyDequeException("Deque is empty.");
       DLNode<E> last = trailer.getPrev();
       E o = last.getElement();
       DLNode<E> secondtolast = last.getPrev();
       trailer.setPrev(secondtolast);
       secondtolast.setNext(trailer);
       size--;
       return o;
     }

   public E removeFirst() throws EmptyDequeException {

	   if (isEmpty())
         throw new EmptyDequeException("Deque is empty.");
       DLNode<E> first = header.getNext();
	   E o = first.getElement();
	   DLNode<E> second = first.getNext();
	   header.setNext(second);
	   second.setPrev(header);
	   size--;
       return o;
   }




   public static void main(String[] args) {
	   NodeDeque<Integer> D = new NodeDeque<Integer>();
	   for (int i = 0; i <= 10; i++)
	      D.addLast(i);
	   System.out.println ("The size of deque is " + D.size());
	   System.out.println ("All elements from the beginning to the end are ");
	   while(!D.isEmpty())
	      System.out.println (D.removeFirst());
   }
}



