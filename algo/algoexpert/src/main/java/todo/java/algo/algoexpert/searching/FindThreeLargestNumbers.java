package todo.java.algo.algoexpert.searching;

import java.util.Arrays;

/**
 * 给定一个数组，找出数组最大的三个数
 * easy
 * link: https://www.algoexpert.io/questions/Find%20Three%20Largest%20Numbers
 *
 * simple input
 * array = [141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7]
 *
 * simple output
 * [18, 141, 541]
 *
 *
 */
public class FindThreeLargestNumbers {

    public static void main(String[] args) {
        Arrays.stream(solution(new int[]{141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7})).forEach(System.out::println);
    }

    public static int[] solution(int[] array) {
        int[] result = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int num: array) {
            updateLargest(result, num);
        }
        return result;
    }

    public static void updateLargest(int[] result, int num) {
        if(num > result[2]) {
            shiftAndUpdate(result, num, 2);
        } else if(num > result[1]) {
            shiftAndUpdate(result, num, 1);
        } else {
            shiftAndUpdate(result, num, 0);
        }
    }

    public static void shiftAndUpdate(int[] array, int num, int idx) {
        for(int i=0; i<=idx; i++) {
            if(i == idx) {
                array[i] = num;
            } else {
                array[i] = array[i + 1];
            }
        }
    }

}
