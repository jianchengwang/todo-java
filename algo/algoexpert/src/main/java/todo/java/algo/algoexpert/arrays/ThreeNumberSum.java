package todo.java.algo.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组三数之和等于给定的值
 * medium
 * link: https://www.algoexpert.io/questions/Three%20Number%20Sum
 *
 * simple input
 * array = [12, 3, 1, 2, -6, 5, -8, 6]
 * targetSum = 0
 *
 * simple output
 * [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 *
 */
public class ThreeNumberSum {
    public static void main(String[] args) {
        int[] array = new int[]{12, 3, 1, 2, -6, 5, -8, 6};
        int targetSum = 0;
        List<Integer[]> target = solutions(array, targetSum);
        for(int i=0; i<target.size(); i++) {
            Integer[] nums = target.get(i);
            for (int j=0; j<nums.length; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println();
        }
    }

    // O(n^2) time | O(n) space
    public static List<Integer[]> solutions(int[] array, int targetSum) {
        Arrays.sort(array);
        List<Integer[]> targetList = new ArrayList<>();
        for(int i=0; i<array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if(currentSum == targetSum) {
                    targetList.add(new Integer[]{array[i], array[left], array[right]});
                    left++;
                    right--;
                } else if(currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return targetList;
    }
}
