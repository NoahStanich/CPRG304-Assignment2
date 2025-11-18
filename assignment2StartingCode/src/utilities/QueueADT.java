package utilities;

import java.util.ArrayList;

/**
 * Queue data structure class - First In First Out (FIFO).
 * @author Chukwudumebi (Maxwell) Anaruagu,
			Maksymenko (Sasha) Oleksandr,
			Noah Stanich,
			Jude (Rei) Uyeno.


 * @version 1.0
 * @param <E> data type for Queue Array
 */
public interface QueueADT<E> {
	
	/**
	 * Clears the queue (makes an array empty).
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b> Queue arrayList is emptied.</p>
	 */
	public void clear();
	
	
	/**
	 * Size of the queue array (number)
	 * @return number of elements in queue array (int). 0 if empty.
	 * 
	 * <p><b>Precondition:</b>none.</p>
	 * <p><b>Postcondition:</b> none.</p>
	 */
	public int size();
	
	/**
	 * Adds an element to the end of the queue.
	 * 
	 * @param element to be enqueued
	 * @return true if successful, false if not 
	 * @throws NullPointerException if parameter passed is null.
	 * 
	 * <p><b>Precondition:</b>none.</p> 
	 * <p><b>Postcondition:</b> element added to the queue.</p>
	 */
	public boolean enqueue(E element) throws NullPointerException;
	
	/**
	 * Adds many elements (1 or more) to the queue array.
	 * 
	 * @param arr an array containing elements to add to the queue in respectful order.
	 * @return true if successful, false if not
	 * @throws NullPointerException if any of the elements in passed array is null.
	 * 
	 * <p><b>Precondition:</b>none.</p> 
	 * <p><b>Postcondition:</b> 1 or more elements added to the queue.</p>
	 */
	public boolean enqueueMany(ArrayList<E> arr) throws NullPointerException;
	
	/**
	 * Removes and returns first element of the queue array 
	 * 
	 * @return element of the queue array after removing it.
	 * 
	 * <p><b>Precondition:</b>Queue is not empty</p> 
	 * <p><b>Postcondition:</b> first element is removed from the queue.</p>
	 */
	public E dequeue();
	
	/**
	 * Method to check if queue array is empty.
	 * 
	 * @return true, if empty, false if not.
	 * 
	 * <p><b>Precondition:</b>none.</p> 
	 * <p><b>Postcondition:</b> none.</p>
	 */
	public boolean isEmpty();
	
	/**
	 * Return an element next in queue array without removing it.
	 * 
	 * @return next element of the array.
	 * 
	 * <p><b>Precondition:</b>Queue is not empty.</p> 
	 * <p><b>Postcondition:</b> none.</p>
	 */
	public E peek();
	
}
