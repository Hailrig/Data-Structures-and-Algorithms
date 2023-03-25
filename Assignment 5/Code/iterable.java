

/** An iterator is a software design pattern that abstracts the process
of scanning through a collection of elements one element at a time.  An iterator
consists of a sequence S, a current element , and the way of stepping to the next element
in S and making it the current element.    Thus, an iterator extends the concept of position
ADT.  In fact, a position can be thought of an iterator that does not go anywhere.

We define the iterator ADT as supporting the following two methods

1.  hasNext(): Test whether there are elements left in the iterator
2.  next():    Return the next element in the iterator.                    */


public interface iterable<E> {
	public boolean hasNext();
	public E next() throws NoSuchElementException;
}