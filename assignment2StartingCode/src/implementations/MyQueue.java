package implementations;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.QueueADT;


public class MyQueue<E> implements QueueADT<E>  {
	
	private MyArrayList<E> list;
	private int size = 0;
	private boolean limit = false;
	
	public MyQueue() {
		MyArrayList<E> list = new MyArrayList<E>();
		limit = false;
		list.clear();
	}
	
	public MyQueue(int newSize) {
		MyArrayList<E> list = new MyArrayList<E>();
		size = newSize;
		limit = true;
		list.clear();
	}
	
	@Override
	public void enqueue(E element) throws NullPointerException {
		if ((list.size() != size) && (limit == true)) {
			if (element != null) {
				list.add(element);
			}
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
	public E peek() {
		return list.get(0);
	}
	
	@Override
	public void dequeueAll() {
		list.clear();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public boolean contains( E toFind ) throws NullPointerException {
		Iterator<E> it = list.iterator();
		while(it.hasNext()) {
			  if (it.next() == toFind) return true;
		}
		return false;
	}
	
	@Override
	public int search( E toFind ) {
		return 0;
	}
	
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	@Override
	public boolean equals( QueueADT<E> that ) {
		return true;
	}
	
	public Object[] toArray() {
		return list.toArray();
	}
	
	public E[] toArray( E[] holder ) throws NullPointerException {
		return list.toArray(holder);
	}
	
	public boolean isFull() {
		if (!limit) return false;
		else {
			return (list.size() == size);
		}
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	
	

	
	
	
}
