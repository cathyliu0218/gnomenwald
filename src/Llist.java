public class Llist<T> {//linked list

	private T label;
	private Node<T> header, back;
	private int length;

	public Llist() {//default constructor
		this.length = 0;
		this.header = new Node<T>();
	}//end

	public Llist(T i) {//constructor
		this.label = i;
		this.header = new Node<T>();
		this.length = 0;
	}//end

	public Node<T> getHeader() {//getter
		return this.header;
	}//end
	
	/**
	 * returns the index of a given Person in this list.
	 */
	public int findIndex(T p) {

		Node<T> result = this.header;
		int counter = -1;
		while (!(result.getData()==(p))) {
		//while (!(p.equals(result.getData()))) {
			result = result.getNext();
			counter++;
		}
		return counter;
	}
	
	/**
	 * returns the element at the specified position in this list.
	 */
	public Node<T> get(int index){
    
		//header is null, so go to next of header
		Node<T> result = this.header.getNext();
		for (int i = 0; i < index; i++) {
			if (result.getNext() == null) {//index out of range
				return null;
			}//end if
			result = result.getNext();//keep taking the next node
		}//end for loop
		return result;

	}//end get

	public boolean isEmpty() {//returns true if linked list is empty
		return this.length == 0;
	}//end

	/**
	 * append a person to linked list
	 * by using a Node to store the person
	 */
	public void append(T p) {
		if (isEmpty()) {
			this.header.setNext(new Node<T>(p));
			this.back = header.getNext();
		} else {
			this.back.setNext(new Node<T>(p));
			this.back = back.getNext();
		}
		length++;
	}//end
	
	public void remove(T v){
		int index = this.findIndex(v);
		if (this.getLength()==1){
			this.getHeader().setNext(null);
			this.length--;
		}
		else if(this.getLength()==2){
			if (index==0)
			 this.header.setNext(this.back);
			else{
				this.back = this.get(index);
				this.header.setNext(this.back);
			}
			this.length--;
		}
		
		else if (this.back.equals(this.get(index))){
			Node<T> prevNode = this.get(index-1);
			prevNode.setNext(null);
			this.length--;	
		}
		
		else{
		Node<T> prevNode = this.get(index-1);
		Node<T> nextNode = this.get(index+1);
		prevNode.setNext(nextNode);
		this.length--;
		}	
	}

	public int getLength() {//getter
		return this.length;
	}//end
	
	public Node<T> graphFind(Village x) {
		Node<T> curr = new Node<T>((T) x);
		while (curr != null) {
			if (curr.getData()==x)
				return curr;
			else
				curr = curr.getNext();
		} // end while loop
		return null;
	}// end
	
	public String toString(){
		String StringResult = "";
		for(int i=0; i<this.length; i++){
			StringResult+=((Village) this.get(i).getData()).getName()+" ";
		}
		return StringResult ;
	}
	
//	public String toStringRoads(){
//		String StringResult = "";
//		for(int i=0; i<this.length; i++){
//			StringResult+=((Road) this.get(i).getData()..+" ";
//		}
//		return StringResult ;
//	}


	/**
	 * an inner class of Llist
	 * Node stores a Village
	 */
	
        public static class Node<T> {
		private T data; // holds the data
		private Node<T> next;

		public T getData() {//getter
			return data;
		}//end

		public void setData(T p) {//setter
			data = p;
		}//end

		public Node<T> getNext() {//getter
			return this.next;
		}//end

		public void setNext(Node<T> n) {//setter
			this.next = n;
		}//end

		public Node(T p) {//constructor
			this.data = p;

		}//end

		public Node() {//default constructor
			this(null);
		}//end
	} // end class Node

}//end class Llist