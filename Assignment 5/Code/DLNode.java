
public class DLNode<E> {
  protected E element;
  protected DLNode<E>  next, prev;	// Pointers to next and previous nodes
  /** Constructor that creates a node with given fields */
  public DLNode(E e, DLNode<E> p, DLNode<E> n) {
    element = e;
    prev = p;
    next = n;
  }
  /** Returns the element of this node */
  public E getElement() { return element; }
  /** Returns the previous node of this node */
  public DLNode<E> getPrev() { return prev; }
  /** Returns the next node of this node */
  public DLNode<E> getNext() { return next; }
  /** Sets the element of this node */
  public void setElement(E newElem) { element = newElem; }
  /** Sets the previous node of this node */
  public void setPrev(DLNode<E> newPrev) { prev = newPrev; }
  /** Sets the next node of this node */
  public void setNext(DLNode<E> newNext) { next = newNext; }
}
