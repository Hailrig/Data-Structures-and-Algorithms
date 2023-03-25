/**FavoriteListMTF extends off favorite list and changes it to sort based on most recently accessed
 * @author Lucas Harvey 192742
 *
 * @param <E> Generic of the list
 */
public class FavoriteListMTF<E> extends FavoriteList<E> {
	
	/**Moves target node to the front of the list
	 *@param Position<Entry<E>> pos the position of the node to move up
	 */
	protected void moveUp(Position<Entry<E>> pos) {
		Entry<E> e = pos.element(); //create a temp entry
		list.remove(pos); //remove the old entry
		list.addFirst(e); //add the new one to front
	}
	
	/**Sorts through the favorite list and returns the top n most accessed
	 *@param int topNumber amount of top numbers to return
	 *@return The list of most accessed
	 */
	public PositionList <E> top(int topNumber) {
		PositionList<E> topList = new NodePositionList<E>(); //output list
		PositionList<Entry<E>> tempList = new NodePositionList<Entry<E>>(); //temp storage list
		
		Position<Entry<E>> cursor = list.first(); //move all items to temp list
		for (int i = 0; i < size; i++) {
			tempList.addLast(cursor.element());
			cursor = list.next(cursor);
		}
		
		for (int x = 0; x < topNumber; x++) { //loop through k times for top number
			cursor = tempList.first();
			int maxNumber = 0;
			Position<Entry<E>> maxElement = null;
			for (int i = 0; i < tempList.size(); i++) { //Loop through the favorite list
				if (cursor.element().getTimesAccessed() > maxNumber) { //Save the top most accessed value encountered
					maxNumber = cursor.element().getTimesAccessed();
					maxElement = cursor;
				}
				cursor = tempList.next(cursor);
			}
			topList.addLast(maxElement.element().getValue()); //Add the top value to the output list and remove from the temp list
			tempList.remove(maxElement);
		}
		return topList;
	}
}
