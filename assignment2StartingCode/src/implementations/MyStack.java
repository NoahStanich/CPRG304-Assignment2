package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
	private MyArrayList<E> stack; //= new MyArrayList<>();

	/**
	 * Parameterless Constructor
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Object created.<p>
	 **/
	public MyStack() {
		stack = new MyArrayList<>();
	}
	
	/**
	 * Method to add a new element to the front of the stack
	 * 
	 * @param element that will be inserted into the stack.
	 * 
	 * @throws NullPointerException if the element passed is null.
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>The element has been inserted last on the stack<p>
	 */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if(toAdd == null ) throw new NullPointerException();
		
		stack.add(toAdd);
	}
	
	/**
	 * Method to take the last item off of the stack
	 * 
	 * @throws EmptyStackException if the stack is empty
	 * @return element that was last on the stack
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns and removes the element that was last on the stack<p>
	 */
	@Override
	public E pop() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		return stack.remove(stack.size() - 1);
	}
	
	/**
	 * Method to see the last item off of the stack
	 * 
	 * @throws EmptyStackException if the stack is empty
	 * @return element that was last on the stack
	 * 
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns the element that was last on the stack<p>
	 */
	@Override
	public E peek() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		
		return stack.get(stack.size() - 1);
	}
	
	/**
	 * Removes all items from the stack
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>All items removed from the stack<p>
	 */
	@Override
	public void clear() {
		stack.clear();
	}

	/**
	 * Checks to see if the stack is empty
	 * 
	 * @return returns true if the stack is empty
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns true if the stack is empty<p>
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * Converts the stack into an array form
	 * 
	 * @return an array containing the elements of this stack.
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns an array containing the elements of this stack.<p>
	 */
	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[stack.size()];
		
		for(int i = 0 ; i < stack.size(); i++ ) {
			newArray[i] = stack.get(stack.size() - 1 - i);
		}
		
		return newArray;
	}

	/**
	 * Converts the stack into an array form, putting it inside of an array given
	 * 
	 * @param Passes an array for items to be put through
	 * @return an array containing the elements of this stack.
	 * @throws NullPointerException if the specified array is null.
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns an array containing the elements of this stack.<p>
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder == null) throw new NullPointerException();
		int size = stack.size();
		
		if(holder.length < size) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size);
			
			for (int i = 0; i < size; i++) {
	        	newArray[i] = stack.get(size - i - 1);
	        }
	        
	        return newArray;
		}
				
		for(int i = 0 ; i < size ; i++ ) {
			holder[i] = stack.get(size - 1 - i);
		}
		
		if(holder.length > size) {
			holder[size] = null;
		}
		
		return holder;
	}

	/**
	 * Method to check if a stack contains an item inside it
	 * 
	 * @throws NullPointerException if the element is null
	 * 
	 * @return returns true if the stack contains the element
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns true if the stack contains the element<p>
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null ) throw new NullPointerException();
		return stack.contains(toFind);
	}

	/**
	 * Method to check if a stack contains an item inside it, and receives it
	 * 
	 * @throws NullPointerException if the element is null
	 * 
	 * @return returns the index where the item is in the stack
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns the index where the item is in the stack<p>
	 */
	@Override
	public int search(E toFind) {
		Iterator<E> it = stack.iterator();
		
		for(int i = stack.size() ; i != 0; i--) {
			if(it.next().equals(toFind)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns an iterator over the elements in this stack in proper sequence.
	 * 
	 * @return an iterator over the elements in this stack in proper sequence.
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns an iterator over the elements in this stack in proper sequence.<p>
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = stack.size() - 1;
			
			@Override
			public boolean hasNext() {
				return index >= 0;
			}
			
			@Override
			public E next() throws NoSuchElementException {
				if(!hasNext()) throw new NoSuchElementException();
				
				E item = stack.get(index);
				index--;
				return item;
			}
		};
	}

	/**
	 * Checks if two stacks are equal to eachother
	 * 
	 * @return returns true if the stacks are equal to each other
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns true if the stacks are equal to each other<p>
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		if(stack.size() != that.size()) {
			return false;
		}
		
		Iterator<E> stack1 = this.iterator();
		Iterator<E> stack2 = that.iterator();
		
		for(int i = 0; i < stack.size(); i++) {
			if(!stack1.next().equals(stack2.next())) {
				return false;
			}
		}
		
		return true;		
	}

	/**
	 * Gets the size of the stack
	 * 
	 * @return returns the size of the stack
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns the size of the stack.<p>
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Returns true if the number of items in the stack are equal to the limit
	 * Warns of a possible overflow
	 * Only applies if there is a limit
	 * 
	 * @return true if stack is at capacity.
	 * <b><p>Precondition:<b>none.<p>
	 * <b><p>Postcondition:<b>Returns true if stack is at capacity.<p>
	 */
	@Override
	public boolean stackOverflow() {
		return stack.size() == stack.getLength();
	}
}