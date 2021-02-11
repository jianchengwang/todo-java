package todo.core.source.java.util.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
//        MyLinkedList list = new MyLinkedList();
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add(null);
        list.add("d");
        for(Iterator iterator = list.iterator(); iterator.hasNext();) {
            String next = (String) iterator.next();
            if(next!=null && next.equals("c")) {
                iterator.remove();
            }
        }
//        list.remove("c");
        list.remove(1);
        for(int i=0; i< list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
