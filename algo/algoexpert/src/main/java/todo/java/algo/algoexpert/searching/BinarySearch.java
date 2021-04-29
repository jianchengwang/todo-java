package todo.java.algo.algoexpert.searching;

/**
 * 给定一个有序的数组，二分查找
 * easy
 * link: https://www.algoexpert.io/questions/Binary%20Search
 *
 * simple input
 * array = [0, 1, 21, 33, 45, 45, 61, 71, 72, 73]
 * target = 33
 *
 * simple output
 * 3
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {0, 1, 21, 33, 45, 45, 61, 71, 72, 73}, 33) == 3);
    }

    // O(log(n)) time | O(1) space
    public static int solution(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
