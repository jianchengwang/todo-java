package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * 指定的数值移动到列表的最后
 * medium
 * link: https://www.algoexpert.io/questions/Move%20Element%20To%20End
 *
 * simple input
 * array = [2, 1, 2, 2, 2, 3, 4, 2]
 * toMove = 2
 *
 * simple output
 * [1, 3, 4, 2, 2, 2, 2, 2]
 *
 */
public class MoveElementToEnd {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2);
        Integer toMove = 2;
        solution(array, toMove).forEach(System.out::println);
    }

    // O(n) time | O(1) space
    public static List<Integer> solution(List<Integer> array, Integer toMove) {
        int left = 0;
        int right = array.size() - 1;
//        while (left < right) {
//            if(array.get(right) == toMove) {
//                right--;
//            } else if(array.get(left) == toMove) {
//                swap(left, right, array);
//                left++;
//                right--;
//            } else {
//                left++;
//            }
//        }
        while (left < right) {
            while (left < right && array.get(right) == toMove) right--;
            if (array.get(left) == toMove) swap(right, left, array);
            left++;
        }
        return array;
    }

    private static void swap(int left, int right, List<Integer> array) {
        int temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }
}
