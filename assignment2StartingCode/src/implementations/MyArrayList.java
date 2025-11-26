package implementations;


import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * Custom implementation of ArrayList - linear data structure.
 * 
 * @author Oleksandr (Sasha) Maksymenko, Chukwudumebi (Max) Anaruagu, Noah Stanich, Jude (Rei) Uyeno
 * @version 1.0
 * @param <E> Data type.
 */
public class MyArrayList<E> implements ListADT<E>{

	private E[] myList;
	private int size;
	private int DEFAULT_SIZE = 10;
	
	
	/**
	 * Parameterless Constructor
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b>Object created.</p>
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.myList = (E[]) new Object[DEFAULT_SIZE];
		this.size = 0;
	}
	
	/**
	 * Constructor with a size parameter
	 * 
	 * @param size the initial length of an array.
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b>Object created.</p>
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList(int size) {
		if(size <= 0) size = DEFAULT_SIZE;
		
		this.myList = (E[]) new Object[size];
		
		this.size = 0;
	}
	
	/**
	 * getter for length of the list.
	 * @return length of the list.
	 */
	public int getLength() {
		return myList.length;
	}

	/**
	 * Method to get the number of elements in the array.
	 * 
	 * @return number of elements in the list.
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Method to clear the list (Creates new array filled with null values and replaces the old array).
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.myList = (E[]) new Object[DEFAULT_SIZE];
		
		size = 0;
	}
	
	/**
	 * Doubles the current array capacity when full.
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
	    int newlength = myList.length * 2;
	    E[] temp = (E[]) new Object[newlength];

	    for (int i = 0; i < size; i++) {
	        temp[i] = myList[i];
	    }

	    myList = temp;
	}
	
	/**
	 * Method to add a new element to the list.
	 * 
	 * @param index and index where element should be inserted
	 * @param element the element that will be inserted in the list.
	 * 
	 * @throws NullPointerException if the element passed is null.
	 * @throws IndexOutOfBoundsException if index passed is longer than the list itself.
	 * 
	 * @return true if element is successfully added.
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b>The element inserted to the specified index in the list.</p>
	 */
	@Override
	public boolean add(int index, E element) throws NullPointerException, IndexOutOfBoundsException {
	    if (element == null) throw new NullPointerException("New element cannot be null.");
	    if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds.");

	    // Resize if array is full
	    if (size == myList.length) resize();

	    // Shift elements to the right to make space for new element
	    for (int i = size; i > index; i--) {
	        myList[i] = myList[i - 1];
	    }

	    // Insert the new element
	    myList[index] = element;
	    size++;

	    return true;
	}

	/**
	 * Method to add the specified element to the end of the list.
	 * 
	 * @param element an element to be added to the end of the list.
	 * 
	 * @throws NullPointerException if the element passed is null.
	 * 
	 * @return true if element successfully added to the list.
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b> Element added to the end of the list.</p>
	 */
	
	@Override
	public boolean add( E element ) throws NullPointerException{
		if(element == null) throw new NullPointerException();
		
		if (size == myList.length) resize();
		
		myList[size] = element;
		size++;
		
		return true;
	}
	
	/**
	 *  Method to add another list in the already existing list
	 *  
	 *  @throws NullPointerException if one of the elements in passed array is null.
	 *  @return true if successfully added.
	 *  @param toAdd is an array to be added at the end of existing values in the existing array.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException{
		 Iterator<? extends E> it = toAdd.iterator();
		 
		 while(it.hasNext()) {
			 E element = it.next();
			 if(element == null) throw new NullPointerException();
			 
			 add(element);
		 }
		 
		return true;
	}
	
	
	/**
	 * Get is method that returns the element by the index provided.
	 * 
	 * @throws IndexOutOfBoundsException if index passed is out of range of the array.
	 * @return  the element of the array ( without deleting it) by the index provided.
	 * @param index of the element to return
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		return myList[index];
	}
	
	
	/**
	 * Method to remove an element from the array by the index provided.
	 * 
	 * @throws IndexOutOfBoundsException if index passed is out of range.
	 * 
	 * @return an element that was deleted.
	 * 
	 * @param index of the element to be deleted.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E element = myList[index];
		
		for(int i = index ; i < size - 1 ; i++ ) {
			myList[i] = myList[i + 1];
		}
		
		size--;
		myList[size] = null;
		
		return element;
	}
	
	/**
	 * Method deletes the element from the array
	 * 
	 * @throws NullPointerException if the element provided is null
	 * @param toRemove is the element that will be removed from the array (if found)
	 */
	@Override
	public E remove( E toRemove ) throws NullPointerException{
		if(toRemove == null) throw new NullPointerException();
		
		int index = -1;
		
		for( int i = 0 ; i < size ; i ++ ) {
			if(myList[i].equals(toRemove)) {
				index = i;
				break;
			}
		}
		
		if(index != -1) {
			return remove(index);
		}
		
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index The index of the element to replace.
	 * @param toChange Element to be stored at the specified position.
	 * @return The element previously at the specified position.
	 * @throws NullPointerException if element passed is null
	 * @throws IndexOutOfBoundsException if index passed is out of range.
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException{
		if(toChange == null) throw new NullPointerException();
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E element = myList[index];
		
		myList[index] = toChange;
		
		return element;
	}
	
	/**
	 * Returns <code>true</code> if this list contains no elements.
	 * 
	 * @return <code>true</code> if this list contains no elements, false if list contains >= 1 elements.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element
	 * <code>e</code> such that <code>toFind.equals(e)</code>.
	 * 
	 * @param toFind The element whose presence in this list is to be tested.
	 * @return <code>true</code> if this list contains the specified element.
	 * @throws NullPointerException If the specified element is <code>null</code>
	 *                              and the list implementation does not support
	 *                              having <code>null</code> elements.
	 */
	@Override
	public boolean contains( E toFind ) throws NullPointerException{
		if(toFind == null) throw new NullPointerException();
		
		for(int i = 0 ; i < size ; i++ ) {
			if(myList[i].equals(toFind)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the
	 * <code>java.util.Collection.toArray()</code> method.
	 * 
	 * @return An array containing all of the elements in this list in proper
	 *         sequence.
	 */
	@Override
	public Object[] toArray() {
	    Object[] result = new Object[size];
	    
	    for (int i = 0; i < size; i++) {
	        result[i] = myList[i];
	    }
	    
	    return result;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the
	 * <code>java.util.Collection.toArray(Object [])</code> method.
	 * 
	 * @param toHold The array into which the elements of this list are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return An array containing the elements of this list.
	 * @throws NullPointerException If the specified array is <code>null</code>.
	 */
	@SuppressWarnings("unchecked")
	public E[] toArray( E[] toHold ) throws NullPointerException{
		if(toHold == null) throw new NullPointerException();
		
		if (toHold.length < size) {
	        // Create a new array of correct runtime type
	        E[] newArray = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	        for (int i = 0; i < size; i++) {
	            newArray[i] = myList[i];
	        }
	        return newArray;
	    }
		for(int i = 0 ; i < size ; i++ ) {
			toHold[i] = myList[i];
		}
		
		if (toHold.length > size) {
		    toHold[size] = null;
		}
		
		return toHold;
	}
	
	// Iterator initialization.
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	/**
	 * Inner Iterator Class
	 */
	public class ArrayIterator implements Iterator<E>{
		
		// Attributes
		private int pos = 0;
			
		/**
		 * Iterator method that returns if Array has another value or not.
		 * 
		 * @return true if there is next element, false if not.
		 */
		@Override
		public boolean hasNext() {
			return pos < size;
		}
		
		
		/**
		 * Iterator method that returns next element in the list.
		 * 
		 * @return E element (next in the list)
		 * @throws NoSuchElementException if there is no more values in the array.
		 */
		@Override
		public E next() throws NoSuchElementException {
			if(!hasNext()) throw new NoSuchElementException();
			
			E element = myList[pos];
			pos++;
			
			return element; 
		}
		
	}
	
}
