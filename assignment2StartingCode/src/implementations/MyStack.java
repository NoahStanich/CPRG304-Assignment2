package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
	private MyArrayList<E> stack; //= new MyArrayList<>();

	public MyStack() {
		stack = new MyArrayList<>();
	}
	@Override
	public void push(E toAdd) throws NullPointerException {
		if(toAdd == null ) throw new NullPointerException();
		
		stack.add(toAdd);
	}

	@Override
	public E pop() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		return stack.remove(stack.size() - 1);
	}

	@Override
	public E peek() throws EmptyStackException {
		if(stack.size() == 0 ) throw new EmptyStackException();
		
		return stack.get(stack.size() - 1);
	}

	@Override
	public void clear() {
		stack.clear();
	}

	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[stack.size()];
		
		for(int i = 0 ; i < stack.size(); i++ ) {
			newArray[i] = stack.get(stack.size() - 1 - i);
		}
		
		return newArray;
	}

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

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null ) throw new NullPointerException();
		return stack.contains(toFind);
	}

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

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean stackOverflow() {
		return stack.size() == stack.getLength();
	}
}