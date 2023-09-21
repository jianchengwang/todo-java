package d8.bitopt;

public class BitOptMain {
    public static void main(String[] args) {
        int n = 39;
        int t = n & 15; // not n % 16
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(15));
        System.out.println(t);

        System.out.println(isOdd(40));
        System.out.println(sevenTimes(2));
        System.out.println(expellOne(5));
    }

    private static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    private static int sevenTimes(int n) {
        return (n << 3) - n;
    }

    private static int expellOne(int a) {
        return a & (a - 1);
    }

    private static int calcOnes(int a) {
        int count = 0;
        while(a != 0) {
            a = expellOne(a);
            count++;
        }
        return count;
    }
}
