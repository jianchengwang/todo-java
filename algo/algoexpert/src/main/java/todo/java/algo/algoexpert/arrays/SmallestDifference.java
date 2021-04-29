package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;

/**
 * 给定两个数组，两个数组各取一个数，使得差值最小，第一个数组的值在前面
 * medium
 * link: https://www.algoexpert.io/questions/Smallest%20Difference
 *
 * simple input
 * arrayOne = [-1, 5, 10, 20, 28, 3]
 * arrayTwo = [26, 134, 135, 15, 17]
 *
 * simple output
 * [28, 26]
 *
 */
public class SmallestDifference {
    public static void main(String[] args) {
        int[] arrayOne = new int[]{-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = new int[]{26, 134, 135, 15, 17};
        Arrays.stream(solution(arrayOne, arrayTwo)).forEach(System.out::println);
    }

    // O(nlog(n) + mlog(m)) time | O(1) space
    public static int[] solution(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        int idxOne = 0;
        int idxTwo = 0;
        int smallest = Integer.MAX_VALUE;
        int current;
        int[] smallestPair = new int[2];
        while (idxOne < arrayOne.length && idxTwo < arrayTwo.length) {
            int firstNum = arrayOne[idxOne];
            int secondNum = arrayTwo[idxTwo];
            if(firstNum < secondNum) {
                current = secondNum - firstNum;
                idxOne++;
            } else if(secondNum < firstNum) {
                current = firstNum - secondNum;
                idxTwo++;
            } else {
                return new int[]{firstNum, secondNum};
            }
            if(smallest > current) {
                smallest = current;
                smallestPair = new int[]{firstNum, secondNum};
            }
        }
        return smallestPair;
    }
}
