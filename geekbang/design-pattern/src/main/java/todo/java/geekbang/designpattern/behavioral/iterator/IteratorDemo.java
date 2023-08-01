package todo.java.geekbang.designpattern.behavioral.iterator;

import java.util.ArrayList;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");

        IIterator iterator = new ArrayIterator(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
