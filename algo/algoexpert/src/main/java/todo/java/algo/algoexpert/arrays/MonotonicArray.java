package todo.java.algo.algoexpert.arrays;

/**
 * 判断数组元素是否单调递增或者单调递减
 * medium
 * link: https://www.algoexpert.io/questions/Monotonic%20Array
 *
 * simple input
 * array = [-1, -5, -10, -1100, -1100, -1101, -1102, -9001]
 *
 * simple output
 * true
 *
 */
public class MonotonicArray {
    public static void main(String[] args) {
        int[] array = new int[]{-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        System.out.println(solution2(array));
    }

    // O(n) time | O(1) space
    public static boolean solution1(int[] array) {
        if(array.length < 2) return true;
        int direction = array[1] - array[0];
        for(int i=2; i<array.length; i++) {
            if(direction == 0) {
                direction = array[i] - array[i-1];
                continue;
            }
            if(direction > 0 && array[i-1] > array[i]) {
                return false;
            } else if(direction < 0 && array[i-1] < array[i]) {
                return false;
            }
        }
        return true;
    }

    // O(n) time | O(1) space
    public static boolean solution2(int[] array) {
        if(array.length < 2) return true;
        boolean isNonIncreasing = true;
        boolean isNonDecreasing = true;
        for(int i=1; i<array.length; i++) {
            if(array[i] < array[i-1]) {
                isNonDecreasing = false;
            }
            if(array[i] > array[i-1]) {
                isNonIncreasing = false;
            }
        }
        return isNonIncreasing || isNonDecreasing;
    }


}
