package todo.java.algo.algoexpert.bst;

/**
 * 输入一个值，在二分查找树查找跟这个值最接近的值
 * easy
 * link: https://www.algoexpert.io/questions/Find%20Closest%20Value%20In%20BST
 *
 * simple input
 * tree  =   10
 *        /     \
 *       5      15
 *     /   \   /   \
 *    2     5 13   22
 *  /           \
 * 1            14
 * target = 12
 *
 * simple output
 * 13
 *
 *
 */
public class FindClosestValueInBST {
    public static void main(String[] args) {
        BST bst = buildBST();
        int target = 12;
        System.out.println(solution(bst, target));
    }

    // avg O(log(n)) time | O(n) space
    // worst O(n) time | O(1) space
    public static int solution(BST tree, int target) {
        while (tree != null) {
            if(tree.value == target) {
                return tree.value;
            } else if(target < tree.value) {
                if(tree.left == null) {
                    return tree.value;
                }
                tree = tree.left;
            } else {
                if(tree.right == null) {
                    return tree.value;
                }
                tree = tree.right;
            }
        }
        return -1;
    }

    public static BST buildBST() {
        BST root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);
        return root;
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
