

public interface Sequence<E>
     extends Deque<E>, IndexList<E>, PositionList<E> {
  /** Returns the position containing the element at the given index. */
  public Position<E> atIndex(int r) throws BoundaryViolationException;
  /** Returns the index of the element stored at the given position. */
  public int indexOf(Position<E> p) throws InvalidPositionException;
}
