package d2.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * https://zhuanlan.zhihu.com/p/24305359
 * 如果调用者在调用一个递归函数并且取得返回值以后，不再进行其他的操作，而是直接将这个值返回出去，那么这就是一个尾递归
 */
public class RecursionMain {
    public static void main(String[] args) {
        System.out.println(fib1(20));
        System.out.println(fibCache(20));
        System.out.println(fib2(0, 1, 20));
        System.out.println(fib3(20));

        System.out.println(power1(2, 3));
        System.out.println(power2(2, 3));
        System.out.println(power3(2, 3, 1, 1));
    }

    private static int fib1(int n) {
        if(n < 2) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    private final static Map<Integer, Integer> cacheMap = new HashMap<>();
    private static int fibCache(int n) {
        if(n < 2) {
            cacheMap.put(n, n);
            return n;
        }
        if(cacheMap.containsKey(n)) {
            return cacheMap.get(n);
        }
        int result = fibCache(n - 1) + fibCache(n - 2);
        cacheMap.put(n, result);
        return result;
    }

    private static int fib2(int a, int b, int n) {
        if(n == 0) {
            return a;
        }
        return fib2(a + b, a, n - 1);
    }

    private static int fib3(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int t = b;
            b = a;
            a = t + b;
        }
        return a;
    }

    private static double power1(double m, int n) {
        if(n == 0) {
            return 1;
        }
        return power1(m, n - 1) * m;
    }

    private static double power2(double m, int n) {
        double result = 1.0;
        double t = m;

        for (int i = 1; i <= n; i <<= 1) {
            if ((n & i) > 0)
                result *= t;

            t *= t;
        }
        return result;
    }

    private static double power3(int m, int n, double t, int i) {
        if(n < i) {
            return t;
        }
        return power3(m, n, t * m, i + 1);
    }

}
