package todo.java.algo.algoexpert.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数组四数之和等于给定的值
 * hard
 * link: https://www.algoexpert.io/questions/Four%20Number%20Sum
 *
 * simple input
 * array = [7, 6, 4, -1, 1, 2]
 * targetSum = 16
 *
 * simple output
 * [[7, 6, 4, -1], [7, 6, 1, 2]]
 *
 */
public class FourNumberSum {
    public static void main(String[] args) {
        int[] array = new int[]{7, 6, 4, -1, 1, 2};
        int targetSum = 16;
        for(Integer[] quadruplet: solution(array, targetSum)) {
            for(Integer value: quadruplet) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // avg O(n^2) time | O(n^2) space
    // worst O(n^3) time | O(n^2) space
    public static List<Integer[]> solution(int[] array, int targetSum) {
        Map<Integer, List<Integer[]>> allPairSums = new HashMap<>();
        List<Integer[]> quadruplets = new ArrayList();
        for(int i=1; i < array.length-1; i++) {
            for(int j=i+1; j<array.length; j++) {
                int currentSum = array[i] + array[j];
                int difference = targetSum - currentSum;
                if(allPairSums.containsKey(difference)) {
                    for(Integer[] pair: allPairSums.get(difference)) {
                        Integer[] newQuadruplet = {pair[0], pair[1], array[i], array[j]};
                        quadruplets.add(newQuadruplet);
                    }
                }
            }
            for (int k = 0; k < i; k++) {
                int currentSum = array[i] + array[k];
                Integer[] pair = {array[k], array[i]};
                if (!allPairSums.containsKey(currentSum)) {
                    List<Integer[]> pairGroup = new ArrayList();
                    pairGroup.add(pair);
                    allPairSums.put(currentSum, pairGroup);
                } else {
                    allPairSums.get(currentSum).add(pair);
                }
            }
        }
        return quadruplets;
    }
}
