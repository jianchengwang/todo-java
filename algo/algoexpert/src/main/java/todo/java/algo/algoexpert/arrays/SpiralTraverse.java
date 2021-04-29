package todo.java.algo.algoexpert.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺时针遍历二维数组，数组行列不固定
 * medium
 * link: https://www.algoexpert.io/questions/Spiral%20Traverse
 *
 * simple input
 * array = [
 *   [1,   2,  3, 4],
 *   [12, 13, 14, 5],
 *   [11, 16, 15, 6],
 *   [10,  9,  8, 7],
 * ]
 *
 * simple output
 * array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 *
 */
public class SpiralTraverse {
    public static void main(String[] args) {
//        int[][] array = new int[][]{{1, 2, 3, 4},
//                {12, 13, 14, 5},
//                {11, 16, 15, 6},
//                {10, 9, 8, 7}};
//        int[][] array = new int[][]{{1, 2, 3},
//                {8, 9, 4},
//                {7, 6, 5}};
//        int[][] array = new int[][]{{1, 2},
//                {4, 3}};
        int[][] array = new int[][]{{1, 2, 3, 4},
                                    {10, 11, 12, 5},
                                    {9, 8, 7, 6}};
        solution(array).stream().forEach(System.out::println);

    }

    // O(n) time | O(n) space
    public static List<Integer> solution(int[][] array) {
        List<Integer> result = new ArrayList<>();
        int startRow = 0;
        int endRow = array.length - 1;
        int startCol = 0;
        int endCol = array[0].length - 1;

        while (startRow<=endRow && startCol<=endCol) {
            for(int col = startCol; col <= endCol; col++) {
                result.add(array[startRow][col]);
            }
            for(int row = startRow + 1; row <= endRow; row++) {
                result.add(array[row][endCol]);
            }
            for(int col = endCol - 1; col >= startCol; col--) {
                if(startRow == endRow) break;
                result.add(array[endRow][col]);
            }
            for(int row = endRow - 1; row > startRow; row--) {
                if(startCol == endCol) break;
                result.add(array[row][startCol]);
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return result;
    }
}
