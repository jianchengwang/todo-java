package todo.java.algo.algoexpert.arrays;

/**
 * 给定一个数组，求先单调递增再单调递增再单调递减的最长匹配
 * link: https://www.algoexpert.io/questions/Longest%20Peak
 * medium
 *
 * simple input
 * array = [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
 *
 * simple output
 * 6 // 0, 10, 6, 5, -1, -3
 *
 */
public class LongestPeak {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        System.out.println(solution(array));
    }

    // O(n) time | O(1) space
    public static int solution(int[] array) {
        int longestPeakLength = 0;
        int idx = 1;
        while (idx < array.length - 1) {
            boolean isPeak = array[idx] > array[idx-1] && array[idx] > array[idx+1];
            if(!isPeak) {
                idx++;
                continue;
            }
            int leftIdx = idx - 2;
            while (leftIdx > 0 && array[leftIdx] < array[leftIdx + 1]) {
                leftIdx--;
            }
            int rightIdx = idx + 2;
            while (rightIdx < array.length && array[rightIdx] < array[rightIdx - 1]) {
                rightIdx++;
            }

            int currentPeakLength = rightIdx - leftIdx - 1;
            if(currentPeakLength > longestPeakLength) {
                longestPeakLength = currentPeakLength;
            }

            idx = rightIdx;
        }
        return longestPeakLength;
    }
}
