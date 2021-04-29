package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;

/**
 * 左上角到右下角依次遍历二维数组
 * hard
 * link: https://www.algoexpert.io/questions/Zigzag%20Traverse
 *
 * simple input
 * array = [
 *   [1,  3,  4, 10],
 *   [2,  5,  9, 11],
 *   [6,  8, 12, 15],
 *   [7, 13, 14, 16],
 * ]
 *
 * simple output
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 *
 */
public class ZigzagTraverse {
    public static void main(String[] args) {
        int[][] array = new int[][]{
            {1,  3,  4, 10},
            {2,  5,  9, 11},
            {6,  8, 12, 15},
            {7, 13, 14, 16}
            // 00 | 10 01 | 02 11 20 | 30 21 12 03 | 13 22 31 | 32 23 | 33
        };
        Arrays.stream(solution(array)).forEach(System.out::println);
    }

    // O(n) time | O(n) space
    public static int[] solution(int[][] array) {
        int[] result = new int[array.length * array[0].length];
        int height = array.length-1;
        int width = array[0].length-1;
        int row = 0;
        int col = 0;
        boolean goingDown = true;
        int idx = 0;
        while(!isOutOfBounds(row, col, height, width)) {
            result[idx++] = array[row][col];
            if(goingDown) {
                if(col==0 || row==height) {
                    goingDown = false;
                    if(row == height) {
                        col++;
                    } else {
                        row++;
                    }
                } else {
                    row++;
                    col--;
                }
            } else {
                if(row == 0 || col==width) {
                    goingDown = true;
                    if(col == width) {
                        row++;
                    } else {
                        col++;
                    }
                } else {
                    row--;
                    col++;
                }
            }
        }
        return result;
    }

    private static boolean isOutOfBounds(int row, int col, int height, int width) {
        return row < 0 || row > height || col < 0 || col > width;
    }
}
