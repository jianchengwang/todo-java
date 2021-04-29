package todo.java.algo.algoexpert.bst;

/**
 * 校验二分查找树是否合法
 * medium
 * link: https://www.algoexpert.io/questions/Validate%20BST
 *
 * simple input
 *  tree  =   10
 *          /     \
 *         5      15
 *       /   \   /   \
 *      2     5 13   22
 *    /           \
 *   1            14
 *
 *  simple output
 *  true
 *
 */
public class ValidateBST {
    public static void main(String[] args) {
        System.out.println(solution(null));
    }

    // O(n) time | O(1) space
    public static boolean solution(BST tree) {
        return validateNode(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateNode(BST node, int minValue, int maxValue) {
        if(node.value < minValue || node.value >= maxValue) {
            return false;
        }
        if(node.left != null && !validateNode(node.left, minValue, node.value)) {
            return false;
        }
        if(node.right != null && !validateNode(node.right, node.value, maxValue)) {
            return false;
        }
        return true;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
