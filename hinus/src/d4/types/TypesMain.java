package d4.types;

/**
 * https://zhuanlan.zhihu.com/p/24412920
 */
public class TypesMain {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = Integer.valueOf(1);
        Integer c = inc(0);
        Integer d = 1;
        System.out.println(a == b);  // false
        System.out.println(a.equals(b)); // true

        System.out.println(a == c); // false
        System.out.println(a.equals(c)); // true

        System.out.println(a == d);  // false
        System.out.println(a.equals(d)); // true

        System.out.println(b == c);  // true
        System.out.println(b.equals(c));  //true

        System.out.println(b == d);  // true
        System.out.println(b.equals(d)); // true

        System.out.println(((Long)1L) == 1); // true
        System.out.println(new Long(1).equals(1)); // false

        Long e = 100L;
        Long f = 100L;
        System.out.println(e == f);  // true
        e = 1000L;
        f = 1000L;
        System.out.println(e == f);  // false
    }

    public static Integer inc(Integer x) {
        return x + 1;
    }
}
