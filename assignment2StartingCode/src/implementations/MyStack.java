package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.EmptyStackException;

/**
 * @author Maxwell Anaruagu, Oleksandr Maksymenko, Jude Uyeno, Noah Stanich
 * @version 1.0
 * 
 * Description: This class implements the StackADT interface using a dynamic array
 *              (MyArrayList) as the underlying data structure. It provides standard
 */

public class MyStack<E> implements StackADT<E> {

    /**
     * The underlying data structure for the stack.
     * It uses a dynamic array (MyArrayList) to store the elements.
     * 
     */
	private MyArrayList<E> stack; //= new MyArrayList<>();

	public MyStack() {
		stack = new MyArrayList<>();
	}

    /**
     * Adds an element to the top of the stack.
     * @param toAdd the element to be added
     * @throws NullPointerException if the element to add is null
     */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if(toAdd == null ) throw new NullPointerException();
		
		stack.add(toAdd);
	}

    /** 
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E pop() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		return stack.remove(stack.size() - 1);
	}

	/**
	 * Returns the element at the top of the stack without removing it.
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E peek() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		
		return stack.get(stack.size() - 1);
	}

	/** 
	 * Removes all elements from the stack.
	 * @return void
	 */
	@Override
	public void clear() {
		stack.clear();
	}

	/** 
	 * Checks if the stack is empty.
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/** 
	 * Converts the stack to an array.
	 * @return an array containing the elements of the stack
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
	 * Converts the stack to an array and stores it in the provided holder array.
	 * @param holder the array to store the elements of the stack
	 * @return an array containing the elements of the stack
	 * @throws NullPointerException if the holder array is null
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder == null) throw new NullPointerException();
		
		if(holder.length < stack.size()) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), stack.size());
			for (int i = 0; i < stack.size(); i++) {
	        	newArray[i] = stack.get(stack.size() - i - 1);
	        }
	        
	        return newArray;
		}
				
		for(int i = 0 ; i < stack.size() ; i++ ) {
			holder[i] = stack.get(stack.size() - 1 - i);
		}
		
		return holder;
	}

	/** 
	 * Checks if the stack contains a specific element.
	 * @param toFind the element to find
	 * @return true if the stack contains the element, false otherwise
	 * @throws NullPointerException if the element to find is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null ) throw new NullPointerException();
		return stack.contains(toFind);
	}

	/** 
	 * Searches for an element in the stack and returns its position from the top.
	 * @param toFind the element to find
	 * @return the position of the element from the top of the stack, or -1 if not found
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
	 * Returns an iterator for the stack.
	 * The iterator traverses the stack from top to bottom.
	 * @return an iterator for the stack
	 */
	@Override
	public Iterator<E> iterator() {
		MyArrayList<E> newArray = new MyArrayList<>();
		
		Iterator<E> it = stack.iterator();
		
		for(int i = 0 ; i < stack.size(); i++) {
			newArray.add(0, it.next());
		}
		
		return newArray.iterator();
	}

	/** 
	 * Compares this stack with another stack for equality.
	 * @param that the stack to compare with
	 * @return true if the stacks are equal, false otherwise
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
	 * Returns the number of elements in the stack.
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}
	@Override
	public boolean stackOverflow() {
		return false;
	}

//	@Override
//	public boolean stackOverflow() {
//		return stack.size() == stack.getLength();
//	}
}