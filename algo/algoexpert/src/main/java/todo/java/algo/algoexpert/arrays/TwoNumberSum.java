package todo.java.algo.algoexpert.arrays;

import java.util.*;

/**
 * 数组两数之和等于给定的值
 * easy
 * link: https://www.algoexpert.io/questions/Two%20Number%20Sum
 *
 * simple input
 * distinct not empty array = [3, 5, -4, 8, 11, 1, -1, 6]
 * targetNum = 10
 *
 * simple output
 * [-1, 11]
 *
 */
public class TwoNumberSum {

    public static void main(String[] args) {
        int[] array = new int[]{3, 5, -4, 8, 11, 1, -1, 6};
        int targetSum = 10;
        Arrays.stream(solution4(array, targetSum)).forEach(System.out::println);
    }

    // two loop
    // O(n^2) time | O(1) space
    public static int[] solution1(int[] array, int targetSum) {
        for(int i=0; i<array.length-1; i++) {
            int firstNum = array[i];
            for(int j=i+1; j<array.length; j++) {
                int secondNum = array[j];
                if(firstNum + secondNum == targetSum) {
                    return new int[]{firstNum, secondNum};
                }
            }
        }
        return new int[0];
    }

    // set
    // O(n) time | O(n) space
    public static int[] solution2(int[] array, int targetSum) {
        Set<Integer> nums = new HashSet<>();
        for(int i=0; i<array.length; i++) {
            int matchNum = targetSum - array[i];
            if(nums.contains(matchNum)) {
                return new int[]{array[i], matchNum};
            } else {
                nums.add(array[i]);
            }
        }
        return new int[0];
    }

    // hash
    // O(n) time | O(n) space
    public static int[] solution3(int[] array, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<array.length; i++) {
            int matchNum = targetSum - array[i];
            if(map.containsKey(matchNum)) {
                return new int[]{array[i], matchNum};
            } else {
                map.put(array[i], matchNum);
            }
        }
        return new int[0];
    }

    // hash
    // O(nlog(n)) time | O(1) space
    public static int[] solution4(int[] array, int targetSum) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length-1;
        while (left < right) {
            int currentSum = array[left] + array[right];
            if(currentSum == targetSum) {
                return new int[]{array[left], array[right]};
            } else if(currentSum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
