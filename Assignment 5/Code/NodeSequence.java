import java.util.Random;

/** Implements a sequence using nodes
 * @author Lucas Harvey 192742
 *
 * @param <E> type of sequence
 */
public class NodeSequence<E> extends NodePositionList<E> implements Sequence<E> {
	DefaultComparator<E> comp = new DefaultComparator<E>();
	
	public NodeSequence(){
		super();
	}
	
	public static void main(String[] Args) {
		Random rand = new Random();
		NodeSequence<Integer> seq = new NodeSequence<Integer>(); //creates test sequences
		NodeSequence<Integer> seqTwo = new NodeSequence<Integer>();
		
		for (int i = 0; i < 20000; i++) { //populates values
			int addValue = rand.nextInt(100);
			seq.addFirst(addValue);
			seqTwo.addFirst(addValue);
		}
		
		System.out.println("Starting bubble sort..."); //run bubbles sort and check tiemes
		long time = System.currentTimeMillis();
		seq.bubbleSort();
		System.out.println("Bubble sort complete. Took: " + (System.currentTimeMillis() - time));
		System.out.println(seq.toString());
		
		System.out.println("Starting merge sort..."); //run merge sort and check times
		time = System.currentTimeMillis();
		seqTwo.mergeSort();
		System.out.println("Merge sort complete. Took: " + (System.currentTimeMillis() - time));
		System.out.println(seqTwo.toString());
	}

	/**
	 * returns first element
	 */
	@Override
	public E getFirst() throws EmptyDequeException {
		return first().element();
	}

	/**
	 * returns last element
	 */
	@Override
	public E getLast() throws EmptyDequeException {
		return last().element();
	}

	/**
	 * removes first node
	 * @return first element
	 */
	@Override
	public E removeFirst() throws EmptyDequeException {
		Position<E> p = first();
		E returnElement = p.element();
		remove(p);
		return returnElement;
	}

	/**
	 * removes last node
	 * @return last element
	 */
	@Override
	public E removeLast() throws EmptyDequeException {
		Position<E> p = last();
		E returnElement = p.element();
		remove(p);
		return returnElement;
	}

	/**
	 * Adds an element at index
	 * @param i integer rank to add at
	 * @param e element to add
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (i == 0 ) { //if it's the start of the list
			addFirst(e);
		} else if (i == numElts) { //if it's the end of the list
			addLast(e);
		} else { //if it's somewhere in between
			addBefore(atIndex(i), e);
		}
	}

	/**
	 * gets element at index
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		return atIndex(i).element();
	}

	/**
	 * removes node at index
	 * @return element removed
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		Position<E> pos = atIndex(i);
		E returnValue = (E) pos.element();
		remove(atIndex(i));
		return returnValue;
	}

	/**
	 * sets an element at index
	 * @param i integer rank to set at
	 * @param e element to set
	 * @return element overwritten
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		Position<E> pos = atIndex(i);
		E returnValue = (E) pos.element();
		set(atIndex(i), e);
		return returnValue;
	}

	/**
	 * Returns position at indicated index
	 * @param index to search
	 * @return position at index
	 */
	@Override
	public Position<E> atIndex(int r) throws BoundaryViolationException {
		iterable <Position<E>> it = positions().iter();
		int i = 0;
		Position<E> pointer = header.getNext();
		while(it.hasNext() && i != r) { //Itterate through until you reach target index, moving the pointer as you go
			pointer = next(pointer);
			i++;
		}
		return pointer;
	}

	/**
	 * returns index for target position
	 * @param position to search
	 * @return i index position is found at
	 */
	@Override
	public int indexOf(Position<E> p) throws InvalidPositionException {
		iterable <Position<E>> it = positions().iter();
		int i = 0;
		while(it.hasNext() && it.next() != p) {
			i++;
		}
		return i;
	}
	
	/**
	 * sorts sequence via the bubblesort method
	 */
	public void bubbleSort() {
		for (int i = 0; i < numElts; i++) { //loop through eleements
			Position<E> pointer = header.getNext(); //reset pointer
			Position<E> next = next(pointer);
			for (int x = 0; x < numElts-i-1; x++) { //loop through elements minus the elements already sorted
				next = next(pointer); //shift next based on new pointer
				if (comp.compare(pointer.element(), next.element()) > 0) { //swap with next if current is bigger than next
					addBefore(pointer, next.element());
					remove(next);
				} else { //otherwise shift pointer
					pointer = next(pointer);
				}
			}
		}
	}
	
	/**
	 * sort sequence based on merge sort
	 */
	@SuppressWarnings("unchecked")
	void mergeSort() {
		E[] values = (E[]) new Object[numElts]; //create some E arrays
		E[] temp = (E[]) new Object[numElts];
		Position<E> pointer = header.getNext();
		for (int i = 0; i < numElts; i++) { //fill array with values from sequence
			values[i] = pointer.element();
			pointer = next(pointer);
		}
		m_sort(values, temp, 0, numElts-1); //sort sequence
	}
		
	/** 
	 * recursively sorts the array
	 * @param values array of seq values
	 * @param temp temporary array of correct size
	 * @param left left most index
	 * @param right right most index
	 */
	void m_sort(E values[], E temp[], int left, int right) {
		int mid;
		if (right > left) { //if left index hasn't passed right index
			mid = (right + left) / 2; //get the new mid value
			m_sort(values, temp, left, mid); //recursively sort and merge
			m_sort(values, temp, mid+1, right);
			merge(values, temp, left, mid+1, right);
		}
	}
	
	/**
	 * Use merge sort to sort array and update sequence
	 * @param values array of seq values
	 * @param temp array of correct size for seq values
	 * @param left left most index
	 * @param mid middle index
	 * @param right right most index
	 */
	void merge(E values[], E temp[], int left, int mid, int right) {
		int i, left_end, num_elements, tmp_pos; //define variables
		left_end = mid - 1;
		tmp_pos = left;
		num_elements = right - left + 1;
		while ((left <= left_end) && (mid <= right)) { //while our indexes have not passed each other
			if (comp.compare(values[left], values[mid]) < 1) { //compare left and mid values, add to temp sorted array as approriate
				temp[tmp_pos] = values[left];
				tmp_pos = tmp_pos + 1;
				left = left +1;
			} else {
				temp[tmp_pos] = values[mid];
				tmp_pos = tmp_pos + 1;
				mid = mid + 1;
			}
		}
		while (left <= left_end) { //if left bound has reached left of mid index
			temp[tmp_pos] = values[left];
			left = left + 1;
			tmp_pos = tmp_pos + 1;
		}
		while (mid <= right) { //if mid index has reached right bound
			temp[tmp_pos] = values[mid];
			mid = mid + 1;
			tmp_pos = tmp_pos + 1;
		}
		for (i=0; i < num_elements; i++){ //return values to original array
			values[right] = temp[right];
			right = right - 1;
		}
		Position<E> pointer = header.getNext(); //update sequence
		for (i = 0; i < numElts; i++) {
			set(pointer, values[i]);
			pointer = next(pointer);
		}
	}
}
