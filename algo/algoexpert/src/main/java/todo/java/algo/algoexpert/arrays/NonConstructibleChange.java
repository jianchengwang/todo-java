package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;

/**
 * 给一个int数组，求不能创建的最小值
 * easy
 * link: https://www.algoexpert.io/questions/Non-Constructible%20Change
 *
 * simple input
 * coins = [5, 7, 1, 1, 2, 3, 22]
 *
 * simple output
 * 20
 *
 */
public class NonConstructibleChange {
    public static void main(String[] args) {
        int[] coins = new int[]{5, 7, 1, 1, 2, 3, 22};
        System.out.println(solution(coins));
    }

    // O(nlog(n)) time | O(1) space
    public static int solution(int[] coins) {
        Arrays.sort(coins);
        int currentChangeCreated = 0;
        for(int coin: coins) {
            if(coin > currentChangeCreated + 1) {
                return currentChangeCreated + 1;
            }
            currentChangeCreated += coin;
        }
        return currentChangeCreated + 1;
    }

}
