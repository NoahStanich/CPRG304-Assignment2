package implementations;

import utilities.Iterator;
import utilities.StackADT;
import java.util.EmptyStackException;

public class MyStack<E> implements StackADT<E> {
    private Object[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public MyStack() {
        elements = new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element to the stack.");
        }
        if (top == elements.length - 1) {
            resize();
        }
        elements[++top] = toAdd;
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E item = (E) elements[top];
        elements[top--] = null; // Avoid memory leak
        return item;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elements[top];
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[top + 1];
        System.arraycopy(elements, 0, array, 0, top + 1);
        return array;
    }

    private void resize() {
        int newSize = elements.length * 2;
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public int search(E toFind) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public boolean equals(StackADT<E> that) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean stackOverflow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stackOverflow'");
    }
}