public class SinglyLinkedList<E> {
  protected Node<E> start;

  protected int size;
  public SinglyLinkedList(){
	  start = null;
	  size = 0;
	  }
	  //insertAtLast
	  public void add(E data){
		  insertAtLast(data);
		  }
	  public void insertAtLast(E data){
		  if(size==0){
			  start = new Node<E>();
			  start.next = null;
			  start.element = data;
			  }
		  else{
			  Node<E> currentNode = getNodeAt(size-1);
			  Node<E> newNode = new Node<E>();
			  newNode.element = data;
			  newNode.next = null;
			  currentNode.next = newNode;   }
			  size++;
		  }

	   public void insertAtFirst(E data){
		   if(size==0){
			   start = new Node<E>();
			   start.next = null;
			   start.element = data;   }
		   else{
				   Node<E> newNode = new Node<E>();
				   newNode.element = data;
				   newNode.next = start;
				   start = newNode;   }
				   size++;  }

		public Node<E> getNodeAt(int nodePos) throws ArrayIndexOutOfBoundsException{
			if(nodePos>=size || nodePos<0){    throw new ArrayIndexOutOfBoundsException();   }
			Node<E> temp = start; //Move pointer to front   int counter = 0;
			for(int counter = 0;counter<nodePos;counter++){
				temp = temp.next;   }   return temp;
		    }

		public void insertAt(int position, E data){ //  0 <= position <= size - 1
			if(position == 0){
				insertAtFirst(data);   }
			else if(position==size-1){
				insertAtLast(data);   }
				else{
						Node<E> priorNode = getNodeAt(position-1);
						Node<E> newNode = new Node<E>();
						newNode.element = data;
						newNode.next = priorNode.getNext();
						priorNode.next = newNode;
						size++;

					  }  }

		public Node<E> getFirst(){
			if (size == 0)
			   return null;
			   return getNodeAt(0);  }

		public Node<E> getLast(){
			if (size == 0) return null;
		    return getNodeAt(size-1);  }

		public E removeAtFirst(){
			if(size==0){
				return null;  }
			E data = start.element;
			start = start.next;
			size--;
			return data;  }

		public E removeAtLast(){
			if(size==0){
				throw new ArrayIndexOutOfBoundsException();   }
			if (size == 1) {
				E temp = start.element;
				start = null;
				size--;
				return temp;
			} 

			Node<E> secondLast = getNodeAt(size-2);
			E returnNode = secondLast.getNext().element;
			secondLast.next = null;
			size--;

			return returnNode;  }

			public E removeAt(int position){
				if(position==0){    return removeAtFirst();   }
				else if(position == size-1){    return removeAtLast();   }
				     else{
						 Node<E> tempNode = getNodeAt(position-1);
						 E data = tempNode.next.element;
						 tempNode.next = tempNode.next.next;
						 size--;
						 return data;   }  }

		public int size(){   return size;  }

		public static void main(String[] args) {
			SinglyLinkedList<Integer> SLL = new SinglyLinkedList<Integer>();
			for (int i = 0; i <= 10; i++) {
				SLL.add(i);
			}
			System.out.println("Adding 0-10 to list...");
			System.out.println("Adding 10 at position 1...");
			System.out.println("Removing last value : " + SLL.removeAtLast());
			SLL.insertAt(1, 10);
			System.out.println("New value at position 1: " + SLL.getNodeAt(1).element);
			System.out.println("New last value: " + SLL.getLast().element);
			System.out.println("The Size of List is:  " + SLL.size());
			
		}


}