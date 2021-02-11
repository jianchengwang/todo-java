package todo.core.source.java.lang.string;

/**
 * @author wjc
 * @date 2021/2/8
 */
public class Test {
    public static void main(String[] args) {
        testConstructor();
    }

    static void testConstructor() {
        String a = "aa";

        String s = new String("2");
        s.intern();
        String s2 = "2";
        System.out.println(s == s2);


        String s3 = new String("3") + new String("3");
        s3.intern();
        String s4 = "33";
        System.out.println(s3 == s4);
    }
}
