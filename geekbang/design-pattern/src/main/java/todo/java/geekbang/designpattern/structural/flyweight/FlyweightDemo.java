package todo.java.geekbang.designpattern.structural.flyweight;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        Tree oakTree1 = TreeFactory.getTree("Oak");
        oakTree1.display(10, 20);

        Tree oakTree2 = TreeFactory.getTree("Oak");
        oakTree2.display(30, 40);

        Tree pineTree = TreeFactory.getTree("Pine");
        pineTree.display(50, 60);

//        testInteger2();
        testString2();
    }

    public static void testInteger() {
        Integer i1 = 56;
        Integer i2 = 56;
        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    public static void testString() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
    }

    public static void testInteger2() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c==d); // true
        System.out.println(e==f); // false
        System.out.println(c==(a+b));// true // System.out.println(c.intValue() == a.intValue() + b.intValue()); //true
        System.out.println(c.equals(a+b)); // true // System.out.println(c.equals(Integer.valueOf(a.intValue() + b.intValue()))); //true
        System.out.println(g ==(a+b)); // true // System.out.println(g.longValue() == (long)(a.intValue() + b.intValue()));  //true
        System.out.println(g.equals(a+b)); // false //  System.out.println(g.equals(Integer.valueOf(a.intValue() + b.intValue()))); //false
    }

    public static void testString2() {
        String str1 = new StringBuilder("jisuanji").append("ruanjian").toString();
        System.out.println(str1==str1.intern());// true
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2==str2.intern()); // false
    }
}
