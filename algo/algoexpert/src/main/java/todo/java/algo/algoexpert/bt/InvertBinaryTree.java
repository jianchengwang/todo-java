package todo.java.algo.algoexpert.bt;

/**
 * 二分树左右节点对换
 * medium
 * link: https://www.algoexpert.io/questions/Invert%20Binary%20Tree
 *
 * simple input
 * tree =    1
 *        /     \
 *       2       3
 *     /   \   /   \
 *    4     5 6     7
 *  /   \
 * 8     9
 *
 * simple output
 *        1
 *     /     \
 *    3       2
 *  /   \   /   \
 * 7     6 5     4
 *             /   \
 *            9     8
 *
 */
public class InvertBinaryTree {
    public static void main(String[] args) {

    }

    // O(n) time | O(d) space
    public static void invertBinaryTree(BinaryTree node) {
        if(node == null) return;
        swapLeftAndRight(node);
        invertBinaryTree(node.left);
        invertBinaryTree(node.right);
    }

    public static void swapLeftAndRight(BinaryTree node) {
        BinaryTree left = node.left;
        node.left = node.right;
        node.right = left;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
