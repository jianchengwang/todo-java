package todo.java.algo.algoexpert.bst;

import java.util.List;

/**
 * 生成最小高度的二分查找树
 * medium
 * link: https://www.algoexpert.io/questions/Min%20Height%20BST
 *
 */
public class MinHeightBST {
    public static void main(String[] args) {

    }

    // O(n) time | O(n) space
    public static BST solution(List<Integer> array) {
        return constructMinHeightBST(array, 0, array.size() - 1);
    }

    public static BST constructMinHeightBST(List<Integer> array, int startIndex, int endIndex) {
        if(endIndex < startIndex) return null;
        int midIndex = (startIndex + endIndex) / 2;
        BST bst = new BST(array.get(midIndex));
        bst.left = constructMinHeightBST(array, startIndex, midIndex - 1);
        bst.right = constructMinHeightBST(array, midIndex + 1,  endIndex);
        return bst;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
