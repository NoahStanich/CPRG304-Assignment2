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
		
		if( queue.contains(toFind)) {
			Iterator<E> it = queue.iterator();
			int index = 0;
			
			while(it.hasNext()) {
				index++;
				
				if(it.next().equals(toFind)) {
					return index;
				}
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
		
		while(queue1.hasNext()) {
			E item1 = queue1.next();
			E item2 = queue2.next();
			
			if(!item1.equals(item2)) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}
		
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int size() {
		return queue.size();
	}
	
}
