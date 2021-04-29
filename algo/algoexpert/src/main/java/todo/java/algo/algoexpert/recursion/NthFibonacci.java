package todo.java.algo.algoexpert.recursion;

/**
 * 费波纳契数列
 * easy
 * link: https://www.algoexpert.io/questions/Nth%20Fibonacci
 *
 */
public class NthFibonacci {
    public static void main(String[] args) {

    }

    // O(2^n) time | O(n) space
    public static int solution1(int n) {
        if(n == 1) return 0;
        if(n == 2) return 1;
        return solution1(n-1) + solution1(n-2);
    }

    // O(n) time | O(1) space
    public static int solution2(int n) {
        int[] lastTwo = {0, 1};
        int counter = 3;
        while (counter <= n) {
            int nextFib = lastTwo[0] + lastTwo[1];
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = nextFib;
            counter++;
        }
        return n > 1 ? lastTwo[1] : lastTwo[0];
    }
}
