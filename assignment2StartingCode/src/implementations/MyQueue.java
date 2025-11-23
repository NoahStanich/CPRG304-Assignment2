package utilities;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import utilities.QueueADT;


public class MyQueue<E> implements QueueADT<E>  {
	
	private ArrayList<E> list;
	
	
	public MyQueue() {
		ArrayList<E> list = new ArrayList<E>();
		clear();
	}

	@Override
	public void clear() {
		list.clear();
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public boolean enqueue(E element) throws NullPointerException {
		if (element != null) {
			list.add(element);
			return true;
		}
		
		throw new NullPointerException();
	}
	
	@Override
	public boolean enqueueMany(ArrayList<E> arr) throws NullPointerException {
		if (arr != null) {
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				list.add(iterator.next());
			}
			return true;
		}
		throw new NullPointerException();
	}
	
	@Override
	public E dequeue() {
		E value = list.get(0);
		list.remove(0);
		return value;
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public E peek() {
		return list.get(0);
	}
	
}
