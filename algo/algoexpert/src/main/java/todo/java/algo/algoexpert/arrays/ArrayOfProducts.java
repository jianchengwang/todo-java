package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;

/**
 * 给定一个数组，返回一个数组，返回数组的值是给定数组其他元素的乘积，长度跟给定数组一样
 * medium
 *
 * simple input
 * array = [5, 1, 4, 2]
 *
 * simple output
 * [8, 40, 10, 20]
 * // 8 = 1 x 4 x 2
 * // 40 = 5 x 4 x 2
 * // 10 = 5 x 1 x 2
 * // 20 = 5 x 1 x 4
 *
 */
public class ArrayOfProducts {
    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 4, 2};
        Arrays.stream(solution2(array)).forEach(System.out::println);
    }

    // O(n^2) time | O(n) space
    public static int[] solution1(int[] array) {
        int[] products = new int[array.length];
        for(int i=0; i<array.length; i++) {
            int runningProduct = 1;
            for (int j=0; j<array.length; j++) {
                if(i != j) {
                    runningProduct *= array[j];
                }
            }
            products[i] = runningProduct;
        }
        return products;
    }

    // O(n) time | O(n) space
    public static int[] solution2(int[] array) {
        int[] products = new int[array.length];

        int leftRunningProduct = 1;
        for(int i=0; i<array.length; i++) {
            products[i] = leftRunningProduct;
            leftRunningProduct *= array[i];
        }

        int rightRunningProduct = 1;
        for(int i=array.length-1; i>=0; i--) {
            products[i] *= rightRunningProduct;
            rightRunningProduct *= array[i];
        }

        return products;
    }
}
