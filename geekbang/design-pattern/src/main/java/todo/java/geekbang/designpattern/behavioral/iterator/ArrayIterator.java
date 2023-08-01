package todo.java.geekbang.designpattern.behavioral.iterator;

import java.util.ArrayList;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class ArrayIterator<E> implements IIterator<E> {

    private int cursor;
    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new RuntimeException("no more element");
        }
        return arrayList.get(cursor);
    }
}
