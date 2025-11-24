package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;


public class MyQueue<E> implements QueueADT<E>  {
	
	private MyArrayList<E> queue;
	
	public MyQueue() {
		this.queue = new MyArrayList<E>();  
	}
	
	@Override
	public void enqueue(E element) throws NullPointerException {
		if (element == null) throw new NullPointerException();
		
		queue.add(element);		
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		if(queue.size() == 0 ) throw new EmptyQueueException();
		
		return queue.remove(0);
	}
	
	@Override
	public E peek() throws EmptyQueueException {
		if(queue.size() == 0) throw new EmptyQueueException();
		
		return queue.get(0);
	}
	
	@Override
	public void dequeueAll() {
		queue.clear();
	}
	
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null) throw new NullPointerException();
		
		return queue.contains(toFind);
	}
	
	@Override
	public int search(E toFind) {
		
		Iterator<E> it = queue.iterator();
		
		for(int i = 1 ; i <= queue.size(); i++ ) {
			if(it.next().equals(toFind)) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}
	
	@Override
	public boolean equals(QueueADT<E> that) {
		if(queue.size() != that.size()) {
			return false;
		}
		
		boolean flag = true;
		
		Iterator<E> queue1 = queue.iterator();
		Iterator<E> queue2 = that.iterator();
		
		for(int i = 0 ; i < queue.size(); i++ ) {
			if(!queue1.next().equals(queue2.next())) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}
		
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

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if(holder  == null) throw new NullPointerException();
		
		if( queue.size() > holder.length) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), queue.size());
			Iterator<E> it = queue.iterator();
			for (int i = 0; i < queue.size(); i++) {
	        	newArray[i] = it.next();
	        }
	        
	        return newArray;
		}
		
		Iterator<E> it = queue.iterator();
		
		for(int i = 0 ; i < queue.size(); i++ ) {
			holder[i] = it.next();
		}
		
		return holder;
	}

	@Override
	public boolean isFull() {
		return queue.size() == queue.getLength();
	}
	
	@Override
	public int size() {
		return queue.size();
	}
	
}
