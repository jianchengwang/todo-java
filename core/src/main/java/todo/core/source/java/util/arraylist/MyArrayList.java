package todo.core.source.java.util.arraylist;

import java.util.Arrays;
import java.util.RandomAccess;

public class MyArrayList<E> implements RandomAccess {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initCapacity) {
        this.elementData = new Object[initCapacity];
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public E remove(int index) {
        E oldValue = get(index);
        int numMoved = size - index - 1;
        if(numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    public int size() {
        return size;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if(elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
        }
        if(minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        } else if(newCapacity - Integer.MAX_VALUE > 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

}
