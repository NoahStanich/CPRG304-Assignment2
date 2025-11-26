package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;


public class MyQueue<E> implements QueueADT<E>  {
	
	private MyArrayList<E> queue;
	
	/**
	 * Parameterless Constructor
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Object created.<p>
	 **/
	public MyQueue() {
		this.queue = new MyArrayList<E>();  
	}
	
	/**
	 * Method to add a new element to the list.
	 * 
	 * @param element that will be inserted into the queue.
	 * 
	 * @throws NullPointerException if the element passed is null.
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>The element inserted first into the queue<p>
	 */
	@Override
	public void enqueue(E element) throws NullPointerException {
		if (element == null) throw new NullPointerException();
		
		queue.add(element);		
	}
	
	/**
	 * Method to remove the first element from the queue
	 * 
	 * @throws EmptyQueueException if the queue is empty
	 * 
	 * @return an element that was deleted.
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if(queue.size() == 0 ) throw new EmptyQueueException();
		
		return queue.remove(0);
	}
	
	/**
	 * Method to view the first element from the queue
	 * 
	 * @throws EmptyQueueException if the queue is empty
	 * 
	 * @return the first element in the queue
	 */
	@Override
	public E peek() throws EmptyQueueException {
		if(queue.size() == 0) throw new EmptyQueueException();
		
		return queue.get(0);
	}
	
	/**
	 * Removes all items from the queue
	 */
	@Override
	public void dequeueAll() {
		queue.clear();
	}
	/**
	 * Checks to see if the queue is empty
	 * 
	 * @return returns true if the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * Method to check if a queue contains an item inside it
	 * 
	 * @throws NullPointerException if the element is null
	 * 
	 * @return returns true if the queue contains the element
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) throw new NullPointerException();
		
		return queue.contains(toFind);
	}
	
	/**
	 * Method to check if a queue contains an item inside it, and receives it
	 * 
	 * @throws NullPointerException if the element is null
	 * 
	 * @return returns the index where the item is in the queue
	 */
	@Override
	public int search(E toFind) throws NullPointerException{
		if(toFind == null ) throw new NullPointerException();
		
		Iterator<E> it = queue.iterator();
		
		for(int i = 1 ; i <= queue.size(); i++ ) {
			if(it.next().equals(toFind)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Returns an iterator over the elements in this queue in proper sequence.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}
	
	/**
	 * Checks if two queues are equal to eachother
	 * 
	 * @return returns true if the queues are equal to each other
	 */
	@Override
	public boolean equals(QueueADT<E> that) {
		if(that == null ) return false;
		if(queue.size() != that.size()) return false;
		
		Iterator<E> queue1 = queue.iterator();
		Iterator<E> queue2 = that.iterator();
		
		for(int i = 0 ; i < queue.size(); i++ ) {
			if(!queue1.next().equals(queue2.next())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Converts the queue into an array form
	 * 
	 * @return an array containing the elements of this queue.
	 */
	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[queue.size()];
		Iterator<E> it = queue.iterator();
		int index = 0;
		
		while(it.hasNext()) {
			newArray[index] = it.next();
			index++;
		}
		return newArray;
	}

	
	/**
	 * Converts the queue into an array form, putting it inside of an array given
	 * 
	 * @param Passes an array for items to be put through
	 * @return an array containing the elements of this queue.
	 * @throws NullPointerException if the specified array is null.
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder  == null) throw new NullPointerException();
		int size = queue.size();
		
		if(size > holder.length) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
			Iterator<E> it = queue.iterator();
			for (int i = 0; i < size; i++) {
	        	newArray[i] = it.next();
	        }
	        
	        return newArray;
		}
		
		Iterator<E> it = queue.iterator();
		
		for(int i = 0 ; i < size; i++ ) {
			holder[i] = it.next();
		}
		
		if(holder.length > size) holder[size] = null;
		
		return holder;
	}

	/**
	 * Returns true if the number of items in the queue is equal to the limit
	 * Only applies if there is a limit
	 * 
	 * @return true if queue is at capacity.
	 */
	@Override
	public boolean isFull() {
		return queue.size() == queue.getLength();
	}
	
	/**
	 * Gets the size of the queue
	 * 
	 * @return returns the size of the queue
	 */
	@Override
	public int size() {
		return queue.size();
	}
	
}
