package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.EmptyStackException;

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
		MyArrayList<E> newArray = new MyArrayList<>();
		
		Iterator<E> it = stack.iterator();
		
		for(int i = 0 ; i < stack.size(); i++) {
			newArray.add(0, it.next());
		}
		
		return newArray.iterator();
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