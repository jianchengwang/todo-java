package todo.java.algo.algoexpert.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 从数组找出第一个重复的数值，数组值范围1到n，所以都是整数
 * medium
 * link: https://www.algoexpert.io/questions/First%20Duplicate%20Value
 *
 * simple input
 * array = [2, 1, 5, 2, 3, 3, 4]
 *
 * simple output
 * 2
 *
 */
public class FirstDuplicateValue {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 5, 2, 3, 3, 4};
        System.out.println(solution2(array));
    }

    // O(n) time | O(n) space
    public static int solution1(int[] array) {
        Set<Integer> seen = new HashSet<>();
        for (int value: array) {
            if (seen.contains(value)) {
                return value;
            }
            seen.add(value);
        }
        return -1;
    }

    // O(n) time | O(1) space
    public static int solution2(int[] array) {
        for (int value: array) {
            int absValue = Math.abs(value);
            if(array[absValue - 1] < 0) return absValue;
            array[absValue - 1] *= -1;
        }
        return -1;
    }

}
