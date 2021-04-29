package todo.java.algo.algoexpert.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分树从左到右节点和
 * easy
 * link: https://www.algoexpert.io/questions/Branch%20Sums
 *
 * simple input
 * tree  =    1
 *         /     \
 *        2       3
 *      /   \    /  \
 *     4     5  6    7
 *   /   \  /
 *  8    9 10
 *
 * simple output
 * array = [15, 16, 18, 10, 11]
 * // 15 = 1 + 2 + 4 + 8
 * // 16 = 1 + 2 + 4 + 9
 * // 18 = 1 + 2 + 5 + 10
 * // 10 = 1 + 3 + 6
 * // 11 = 1 + 3 + 7
 *
 */
public class BranchSums {
    public static void main(String[] args) {
    }

    public static List<Integer> solution(BinaryTree root) {
        List<Integer> result = new ArrayList<>();
        calBinaryTreeSums(root, 0, result);
        return result;
    }

    public static void calBinaryTreeSums(BinaryTree node, int runningSum, List<Integer> sums) {
        if(node == null) return;
        int newRunningSum = runningSum + node.value;
        if(node.left == null && node.right == null) {
            sums.add(newRunningSum);
            return;
        }
        calBinaryTreeSums(node.left, newRunningSum, sums);
        calBinaryTreeSums(node.right, newRunningSum, sums);
    }

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
