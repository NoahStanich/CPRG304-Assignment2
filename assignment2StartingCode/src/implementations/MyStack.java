package implementations;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import utilities.StackADT;

/**
 * Stack implementation backed by MyArrayList.
 */
public class MyStack<E> implements StackADT<E> {

    private MyArrayList<E> stackList;

    public MyStack() {
        stackList = new MyArrayList<E>();
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
        if (element == null) throw new NullPointerException();
        stackList.add(element);
        return true;
    }

    @Override
    public boolean pushMany(ArrayList<E> arr) throws NullPointerException {
        if (arr == null) throw new NullPointerException();
        for (E e : arr) {
            if (e == null) throw new NullPointerException();
            stackList.add(e);
        }
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException();
        return stackList.remove(stackList.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return stackList.get(stackList.size() - 1);
    }
}