package todo.java.algo.algoexpert.bt;

/**
 * 求所有节点到根节点的距离
 * easy
 * link: https://www.algoexpert.io/questions/Node%20Depths
 *
 */
public class NodeDepths {
    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.left = new BinaryTree(4);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right = new BinaryTree(5);
        root.right = new BinaryTree(3);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        int actual = solution(root);
        System.out.println(actual);
    }

    public static int solution(BinaryTree root) {
        return nodeDepths(root, 0);
    }

    // O(n) time | O(h) space
    public static int nodeDepths(BinaryTree node, int depth) {
        if(node == null) return 0;
        return depth + nodeDepths(node.left, depth + 1) + nodeDepths(node.right, depth + 1);
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
