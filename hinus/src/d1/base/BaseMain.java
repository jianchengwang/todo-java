package d1.base;

/**
 * https://zhuanlan.zhihu.com/p/24313237
 */
public class BaseMain {
    public static void main(String[] args) {
        System.out.println(oct2Bin(8));
        System.out.println(transform("221", 3, 2));
        overflowTest1();
    }

    private static String oct2Bin(int a) {
        if(a == 0) {
            return "0";
        }
        boolean isNegative = a < 0;
        if(isNegative) {
            a = -a;
        }
        StringBuilder result = new StringBuilder();
        while (a != 0) {
            result.insert(0, a % 2);
            a /= 2;
        }
        if(isNegative) {
            result.insert(0, "-");
        }
        return result.toString();
    }

    private static String transform(String s, int radixSrc, int radixTgt) {
        if(s == null || s.isEmpty()) {
            return "0";
        }
        boolean isNegative = s.charAt(0) == '-';
        if(isNegative) {
            s = s.substring(1);
        }
        int num = Integer.parseInt(s, radixSrc);
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            result.insert(0, num % radixTgt);
            num /= radixTgt;
        }
        if(isNegative) {
            result.insert(0, "-");
        }
        return result.toString();
    }

    private static void overflowTest1() {
        short a = Byte.MIN_VALUE;
        int b = Short.MIN_VALUE;
        short c = Byte.MAX_VALUE + 1;
        byte d = (byte)(Byte.MAX_VALUE + 1);
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("c:" + c);
        System.out.println("d:" + d);
    }

}
