package todo.core.source.java.lang.integer;

public class Test {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        Integer c1 = Integer.valueOf(1);
        System.out.println(a == b); // true
        System.out.println(a == c); // false
        System.out.println(a == c1); // true

        Integer d = 128;
        Integer e = 128;
        Integer f = new Integer(128);
        System.out.println(d == e); // false
        System.out.println(d == f); // false

    }
}
