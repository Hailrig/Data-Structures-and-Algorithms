/** Comparator based on the natural ordering
  */
import java.util.Comparator;
public class DefaultComparator<E> implements Comparator<E> {
  /** Compares two given elements
    */
  @SuppressWarnings("unchecked")
public int compare(E a, E b) throws ClassCastException {
    return ((Comparable<E>) a).compareTo(b);

  }
}
