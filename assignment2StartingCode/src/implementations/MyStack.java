package implementations;

import java.util.ArrayList;
import java.util.NoSuchElementException;

interface StackADT<E> {
    void clear();
    int depth();
    boolean push(E element) throws NullPointerException;
    E pop();
    boolean isEmpty();
    E peek();
}

public abstract class MyStack<E> implements StackADT<E> {
    
    private ArrayList<E> stackList;
    
    public MyStack() {
        stackList = new ArrayList<E>();
        clear();
    }

    @Override
    public void clear() {
        stackList.clear();
    }
    
    @Override
    public int depth() {
        return stackList.size();
    }
    
    @Override
    public boolean push(E element) throws NullPointerException {
        if (element != null) {
            stackList.add(element);
            return true;
        }
        
        throw new NullPointerException();
    }
    
    public boolean pushMany(ArrayList<E> arr) throws NullPointerException {
        if (arr != null) {
            for (E element : arr) {
                if (element == null) {
                    throw new NullPointerException();
                }
                stackList.add(element);
            }
            return true;
        }
        throw new NullPointerException();
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.remove(stackList.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.get(stackList.size() - 1);
    }
}