package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * 是否是子列表，顺序一致
 * easy
 * link: https://www.algoexpert.io/questions/Validate%20Subsequence
 *
 * simple input
 * array = [5, 1, 22, 25, 6, -1, 8, 10]
 * sequence = [1, 6, -1, 10]
 *
 * simple output
 * true
 *
 */
public class ValidateSubsequence {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);
        System.out.println(solution2(array, sequence));
    }

    // O(n^2) time | O(n) space
    public static boolean solution1(List<Integer> array, List<Integer> sequence) {
        for (Integer integer : sequence) {
            int index = array.indexOf(integer);
            if (index == -1) return false;
            array = array.subList(index + 1, array.size());
        }
        return true;
    }

    // O(n) time | O(1) space
    public static boolean solution2(List<Integer> array, List<Integer> sequence) {
        int arrIdx = 0;
        int seqIdx = 0;
        while (arrIdx < array.size() && seqIdx < sequence.size()) {
            if(array.get(arrIdx).equals(sequence.get(seqIdx))) {
                seqIdx++;
            }
            arrIdx++;
        }
        return seqIdx == sequence.size();
    }

    // O(n) time | O(1) space
    public static boolean solution3(List<Integer> array, List<Integer> sequence) {
        int seqIdx = 0;
        for(Integer value:  array) {
            if(value.equals(sequence.get(seqIdx))) {
                seqIdx++;
            }
        }
        return seqIdx == sequence.size();
    }
}
