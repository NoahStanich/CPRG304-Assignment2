package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {

	// Inner Node Class
	@SuppressWarnings("hiding")
	public class MyDLLNode<E> {
		
		private E data;
		private MyDLLNode<E> next;
		private MyDLLNode<E> prev;
		
		// Parameterless constructor
		public MyDLLNode() {
			
		}
		
		// Constructor with a parameter
		public MyDLLNode(E element) {
			this.data = element;
		}
		
		// Getters and Setters
		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public MyDLLNode<E> getNext() {
			return next;
		}

		public void setNext(MyDLLNode<E> next) {
			this.next = next;
		}

		public MyDLLNode<E> getPrev() {
			return prev;
		}

		public void setPrev(MyDLLNode<E> prev) {
			this.prev = prev;
		}
	}
	
	public class DLLIterator implements Iterator<E>{
		
		private E[] copyOfDLL;
		private int pos = 0;
		
		@SuppressWarnings("unchecked")
		public DLLIterator() {
			copyOfDLL = (E[]) new Object[size];
			copyOfDLL = toArray(copyOfDLL);
		}
		@Override
		public boolean hasNext() {
			return pos < copyOfDLL.length;
		}

		@Override
		public E next() throws NoSuchElementException {
			if(!hasNext()) throw new NoSuchElementException();
			
			E element = copyOfDLL[pos];
			pos++;
			return element;
		}
		
	}

	// Fields
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size = 0;
	
	// Parameterless constructor
	public MyDLL() {
		
	}
	
	//Constructor with parameter
	public MyDLL(E element){
		createFirst(element);
	}
	
	public boolean createFirst(E element) {
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

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(toAdd == null) throw new NullPointerException();
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		
		// if list is yet empty 
		if(size == 0) {
			return createFirst(toAdd);
		}
		
		// if add in the end
		if(index == size) {
			return add(toAdd);
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		if(index == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			size++;
			
			return true;
		}
				
		// other cases
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

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) throw new NullPointerException();
		
		if(size == 0) {
			return createFirst(toAdd);
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		this.tail.next = newNode;
		newNode.prev = this.tail;
		
		this.tail = newNode;
		this.size++;
		
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		Iterator<? extends E> it = toAdd.iterator();
		MyDLLNode<E> curr = tail;
		
		while(it.hasNext()) {
			E element = it.next();
			if(element == null) throw new NullPointerException();
			
			add(element);
			curr = curr.next;
		}
		
		tail = curr;
		
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		if(index == 0) {
			return head.data;
		}
		
		if(index == size - 1) {
			return tail.data;
		}
		
		MyDLLNode<E> curr = head;
		for( int i = 0 ; i < index; i++ ) {
			curr = curr.next;
		}
		
		return curr.data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E element;
		
		if(size == 1) {
			element = head.data;
			clear();	
			return element;
		}
		
		if(index == 0) {
			 element = head.data;
			 head = head.next;
			 head.prev = null;
			 size--;
			 return element;
		}
				
		if(index == size - 1) {
			element = tail.data;
			tail = tail.prev;
			tail.next = null;
			size--;
			return element;
		}
		
		MyDLLNode<E> curr = head;
		for( int i = 0 ; i < index; i++ ) {
			curr = curr.next;
		}
		
		element = curr.data;
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
		size--;
		
		return element;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove == null) throw new NullPointerException();
		
		if(size == 0) {
			return toRemove;
		}
		if(size == 1 && head.data == toRemove) {
			clear();
			return toRemove;
		}
		
		if(head.data.equals(toRemove)) {
			head = head.next;
			head.prev = null;
			size--;
			return toRemove;
		}
		
		if(tail.data.equals(toRemove)) {
			tail = tail.prev;
			tail.next = null;
			size--;
			return toRemove;
		}
		
		MyDLLNode<E> curr = head;
		E element = null;
		
		for(int i = 0 ; i < size ; i++ ){
			if(curr.data.equals(toRemove)) {
				element = curr.data;
				break;
			}
			curr = curr.next;
		}
		
		if(element != null) {
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
			size--;
		}
		
		return element;
	}

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
