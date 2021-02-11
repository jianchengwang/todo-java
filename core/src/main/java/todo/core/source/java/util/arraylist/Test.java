package todo.core.source.java.util.arraylist;

public class Test {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList<String>();
        for(int i=0; i<12; i++) {
            list.add("a" + i);
        }
        list.remove(0);
        list.remove(10);
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
