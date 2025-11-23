package utilities;

import java.util.ArrayList;

/**
 * @author Oleksandr (Sasha) Maksymenko
 * @version 1.0
 * 
 * Stack data structure array of <E> elements. First In Last Out (FILO).
 * @param <E> data type of the stack array.
 */
public interface StackADT<E> {
		
	/**
	 * <p>Method to clear out the stack array. ( Delete all the elements)</p>
	 * 
	 * <p><b>Precondition:<b>none.<p> 
	 * <p><b>Postcondition:<b> stack array is emptied.<p>
	 */
	public void clear();

	/**
	 * <p>Returns the number of elements of the stack array.</p>
	 * 
	 * @return number of elements in the stack. 0 if empty.
	 * 
	 * <p><b>Precondition:<b>none.<p> 
	 * <p><b>Postcondition:<b> none.<p>
	 */
	public int depth();
	
	/**
	 * <p>Method to add an element to the end of the stack array.</p>
	 * 
	 * @param element to add to the end of the stack array.
	 * @return true if successful, false if not.
	 * @throws NullPointerException is thrown when element passed is null.
	 * 
	 * <p><b>Precondition:<b>none.<p> 
	 * <p><b>Postcondition:<b> element added to the Stack ArrayList.<p>
	 */
	public boolean push(E element) throws NullPointerException;
	
	/**
	 * <p>Method to add many elements to the stack array in respectful order.</p>
	 * 
	 * @param arr - array that is going to be added to the stack array.
	 * @return true if successful, false if not.
	 * @throws NullPointerException if any of the elements in passed array is null.
	 * 
	 * <p><b>Precondition:<b>none.<p> 
	 * <p><b>Postcondition:<b> 1 or more elements added to the Stack ArrayList.<p>
	 */
	public boolean pushMany(ArrayList<E> arr) throws NullPointerException;
	
	/**
	 * <p>Removes and returns the last element from the stack array.</p>
	 * 
	 * @return the last element of the stack array.
	 * 
	 * <p><b>Precondition:<b>Stack ArrayList is not empty.<p> 
	 * <p><b>Postcondition:<b> Last element of the Stack ArrayList is removed.<p>
	 */
	public E pop();
	
	/**
	 * <p>Method to check if the stack array is empty.</p>
	 * 
	 * @return true if empty, false if not.
	 * 
	 * <p><b>Precondition:<b>none.<p> 
	 * <p><b>Postcondition:<b> none.<p>
	 */
	public boolean isEmpty();
	
	/**
	 * <p>Returns the last element of the stack array without removing it.</p>
	 * 
	 * @return last element of the stack array.
	 * 
	 * <p><b>Precondition:<b>Stack ArrayList is not empty.<p> 
	 * <p><b>Postcondition:<b> none.<p>
	 */
	public E peek();	
}
