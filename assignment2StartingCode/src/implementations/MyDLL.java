package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;
/**
 * Double Linked List class.
 * 
 * @author Oleksandr (Sasha) Maksymenko, Chukwudumebi (Max) Anaruagu, Noah Stanich, Jude (Rei) Uyeno
 * @version 1.0
 * 
 * Implementation of ListADT.
 * Creates and manages Double Linked List
 * @param <E> - type of data stored in the list.
 */
public class MyDLL<E> implements ListADT<E> {

	/**
	 * Inner Single Node Class
	 * @param <E> Data type.
	 */
	@SuppressWarnings("hiding")
	public class MyDLLNode<E> {
		
		// Fields
		private E data;
		private MyDLLNode<E> next;
		private MyDLLNode<E> prev;
		
		/**
		 * Parameterless constructor
		 */
		public MyDLLNode() {
			
		}
		
		/**
		 * Constructor with a parameter
		 * @param element First element in the DLL
		 */
		public MyDLLNode(E element) {
			this.data = element;
		}
	}
	
	/**
	 * Inner DLL Iterator class.
	 */
	public class DLLIterator implements Iterator<E>{
		
		private MyDLLNode<E> curr;
		
		/**
		 * Constructor.
		 */
		public DLLIterator() {
			curr = head;
		}
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}
		
		@Override
		public E next() throws NoSuchElementException {
			if(!hasNext()) throw new NoSuchElementException();
			E item = curr.data;
			curr = curr.next;
			return item;
		}
		
	}

	// Fields
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size = 0;

	/**
	 * Parameterless constructor
	 */
	public MyDLL() {
		
	}
	
	/**
	 * Constructor with parameter
	 * @param element First element in the DLL.
	 */
	public MyDLL(E element){
		createFirst(element);
	}
	
	/**
	 * Method that creates new DLL using the E element passed as a first element.
	 * @param element to be the first element in the list.
	 * @return true if successful.
	 * @throws NullPointerException if element passed is null.
	 */
	public boolean createFirst(E element) throws NullPointerException {
		if(element == null) throw new NullPointerException();
		
		this.head = this.tail = new MyDLLNode<>(element);
		size = 1;
		
		return true;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		this.head = this.tail = null;
		size = 0;
	}
	
	/**
	 * Method to insert the element in the first position of the list. 
	 * @param element to add first in the list
	 * @return true if successful
	 * @throws NullPointerException if element passed is null
	 */
	public boolean addFirst(E element) throws NullPointerException{
		if(element == null) throw new NullPointerException();
		
		if( size == 0 ) {
			return createFirst(element);
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(element);
		
		head.prev = newNode;
		newNode.next = head;
		head = newNode;
		size++;
		
		return true;
	}
	
	/**
	 * Method to remove first Node from DLL
	 * @return element from the Node removed
	 */
	private E removeFirst() {
		E element = head.data;
		
		head = head.next;
		head.prev.next = null;
		head.prev = null;
		size--;
		
		return element;
	}
	
	/**
	 * Method to remove the last Node from the list.
	 * @return Element deleted
	 */
	private E removeLast(){
		E element = tail.data;
		
		tail = tail.prev;
		tail.next.prev = null;
		tail.next = null;
		
		size--;
		
		return element;
	}
	
	/**
	 * Method that allows to insert an element to a certain index in the list.
	 * @param index where the element should be inserted
	 * @param toAdd element to be inserted in the list
	 * @return true if successful
	 * @throws NullPointerException if element passed is null.
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(toAdd == null) throw new NullPointerException();
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
				
		// if element has to be added in the first position
		if(index == 0) {
			return addFirst(toAdd);
		}
		
		// if element to be added at the end
		if(index == size) {
			return add(toAdd);
		}
		
		// other cases (element has to be added in between other elements)
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
						
		MyDLLNode<E> curr = head;
		for( int i = 0 ; i < index ; i++ ) {
			curr = curr.next;
		}
		
		curr.prev.next = newNode;
		newNode.prev = curr.prev;
		newNode.next = curr;
		curr.prev = newNode;
		
		size++;
		
		return true;
	}

	/**
	 * Method to add a new element to the end of the list.
	 * @param toAdd element to be added in the list.
	 * @return true if successful.
	 * @throws NullPointerException if element passed is null.
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) throw new NullPointerException();
		
		// if list is yet empty.
		if(size == 0) {
			return createFirst(toAdd);
		}
		
		// otherwise
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		this.tail.next = newNode;
		newNode.prev = this.tail;
		
		this.tail = newNode;
		this.size++;
		
		return true;
	}

	/**
	 * Method that extends DLL with another DLL 
	 * @param toAdd list that extends the original DLL
	 * @return true if successful
	 * @throws NullPointerException if new list passed contains null
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if(toAdd == null) throw new NullPointerException();
		
		Iterator<? extends E> it = toAdd.iterator();
				
		while(it.hasNext()) {
			E element = it.next();
			if(element == null) throw new NullPointerException();
			
			add(element);
		}		
		return true;
	}

	/**
	 * Method to retrieve an element based on it's index (without deleting it).
	 * @param index of the Node containing data.
	 * @return element of the node at the index specified.
	 * @throws IndexOutOfBoundsException if index provided is out of range <code>index &lt; 0 || index &gt;= size</code>
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		// quick check if it is head or tail that needs to be returned
		if(index == 0) {
			return head.data;
		}
		if(index == size - 1) {
			return tail.data;
		}
		
		// otherwise iterate until index is reached
		MyDLLNode<E> curr = head;
		for( int i = 0 ; i < index; i++ ) {
			curr = curr.next;
		}
		
		return curr.data;
	}

	/**
	 * Method to remove a Node from the List based on it's index
	 * @param index of the Node that needs to be deleted
	 * @throws IndexOutOfBoundsException if <code>index &lt; 0 || index &gt;= size</code>
	 * @return data from the deleted Node.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E element;
		
		//Edge cases
		// if there is only one element in the list.
		if(size == 1) {
			element = head.data;
			clear();	
			return element;
		}
		
		// if the first element has to be removed
		if(index == 0) {
			 return removeFirst();
		}
		
		//if the last element has to be removed
		if(index == size - 1) {
			return removeLast();
		}
		
		MyDLLNode<E> curr = head;
		for( int i = 0 ; i < index; i++ ) {
			curr = curr.next;
		}
		
		element = curr.data;
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
		curr.next = null;
		curr.prev = null;
		
		size--;
		
		return element;
	}

	/**
	 * Method to remove first instance of the element passed
	 * @param toRemove instance of the element to be removed (if found)
	 * @throws NullPointerException if toRemove element passed is null.\
	 * @return data that is being removed
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove == null) throw new NullPointerException();
		
		E element = null;
		
		// Edge cases
		// if list is yet empty.
		if(size == 0) return element;
		
		// if list contains just this one element.
		if(size == 1 && head.data.equals(toRemove)) {
			element = head.data;

			clear();
			return element;
		}
		
		// if head contains the element.
		if(head.data.equals(toRemove)) {
			return removeFirst();
		}
		
		// Otherwise iterate through the list until first instance of the element is found
		MyDLLNode<E> curr = head;
		
		for(int i = 0 ; i < size ; i++ ){
			if(curr.data.equals(toRemove)) {
				element = curr.data;
				break;
			}
			curr = curr.next;
		}
		
		// check if element was found
		if(element != null ) {
			
			// make sure it is no in the tail (curr.next will throw nullPointer)
			if(curr != tail) {
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				size--;
			} else {
				removeLast();
			}
				
		}
		
		return element;
	}

	/**
	 * Method to change data inside the Node located at the index specified
	 * @param index The index of the Node who's data has to be changed.
	 * @param toChange new data for the Node
	 * @throws NullPointerException if the toChange data element is null
	 * @throws IndexOutOfBoundsException if index specified is out of range <code>index &lt; 0 || index &gt;= size</code>
	 * @return Data that was previously stored in the Node
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if(toChange == null) throw new NullPointerException();
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		MyDLLNode<E> curr = head;
		for(int i = 0 ; i < index ; i++ ) {
			curr = curr.next;
		}
		
		E deletedElement = curr.data;
		curr.data = toChange;
		
		return deletedElement; 
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Method to check if DLL contains instance of the data provided in the arguments
	 * @param toFind Data to be checked for existence in the DLL
	 * @throws NullPointerException if toFind data provided is null
	 * @return true if data found in the list, false if not.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) throw new NullPointerException();
		
		MyDLLNode<E> curr = head;
		
		for(int i = 0 ; i < size ; i ++ ) {
			if(curr.data.equals(toFind)) {
				return true;
			}
			curr = curr.next;
		}
		
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold == null) throw new NullPointerException();
		
		if(size > toHold.length) {
			// Create a new array of correct runtime type
	        @SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	        MyDLLNode<E> curr = head;
	        for (int i = 0; i < size; i++) {
	        	newArray[i] = curr.data;
	        	curr = curr.next;
	        }
	        
	        return newArray;
		}
		
		MyDLLNode<E> curr = head;
		for(int i = 0 ; i < size ; i++ ) {
			toHold[i] = curr.data;
			curr = curr.next;
		}
		
		return toHold;
	}

	@Override
	public Object[] toArray() {
		Object[] newArray =  new Object[size];
		MyDLLNode<E> curr = head;
		
		for( int i = 0 ; i < size ; i++ ) {
			newArray[i] = curr.data;
			curr = curr.next;
		}
		
		return newArray;
	}

	@Override
	public Iterator<E> iterator() {
		return new DLLIterator();
	}

}
