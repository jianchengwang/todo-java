package todo.java.geekbang.designpattern.behavioral.iterator;

public interface IIterator<E> {
    boolean hasNext();
    void next();
    E currentItem();
}
