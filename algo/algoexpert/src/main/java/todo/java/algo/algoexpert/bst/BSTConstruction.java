package todo.java.algo.algoexpert.bst;

/**
 * 二分查找树构建
 * medium
 * link: https://www.algoexpert.io/questions/BST%20Construction
 *
 * simple input
 *   10
 *  /     \
 *  5      15
 *  /   \   /   \
 *  2     5 13   22
 *  /           \
 *  1            14
 *
 * insert(12):   10
 *             /     \
 *            5      15
 *          /   \   /   \
 *         2     5 13   22
 *       /        /  \
 *      1        12  14
 *
 * remove(10):   12
 *             /     \
 *            5      15
 *          /   \   /   \
 *         2     5 13   22
 *       /           \
 *      1            14
 *
 *  contains(15): true
 *
 */
public class BSTConstruction {

    public static void main(String[] args) {
        BST root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);

        root.insert(12);
        System.out.println(root.right.left.left.value == 12);

        root.remove(10);
        System.out.println(root.contains(10) == false);
        System.out.println(root.value == 12);
        System.out.println(root.contains(15));
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        // avg O(log(n)) time | O(1) space
        // worst O(n) time | O(1) space
        public BST insert(int value) {
            if(value < this.value) {
                if(this.left == null) {
                    this.left = new BST(value);
                } else {
                    return left.insert(value);
                }
            } else {
                if(this.right == null) {
                    this.right = new BST(value);
                } else {
                    return right.insert(value);
                }
            }
            return this;
        }

        // avg O(log(n)) time | O(1) space
        // worst O(n) time | O(1) space
        public boolean contains(int value) {
            if(value < this.value) {
                if(this.left == null) {
                    return false;
                } else {
                    return left.contains(value);
                }
            } else if(value > this.value) {
                if(this.right == null) {
                    return false;
                } else {
                    return right.contains(value);
                }
            } else {
                return true;
            }
        }

        // avg O(log(n)) time | O(1) space
        // worst O(n) time | O(1) space
        public BST remove(int value) {
            remove(value, null);
            return this;
        }

        private void remove(int value, BST parentNode) {
            BST currentNode = this;
            while (currentNode != null) {
                if(value < currentNode.value) {
                    parentNode = currentNode;
                    currentNode = currentNode.left;
                } else if(value > currentNode.value) {
                    parentNode = currentNode;
                    currentNode = currentNode.right;
                } else {
                    if(currentNode.left == null && currentNode.right == null) {
                        // 叶子节点直接移除
                        currentNode = null;
                    } else if(currentNode.left != null && currentNode.right == null) {
                        // 只有一个叶子节点，直接等于叶子节点
                        currentNode = currentNode.left;
                    } else if(currentNode.left == null && currentNode.right != null) {
                        // 同上
                        currentNode = currentNode.right;
                    } else {
                        if(parentNode == null) {
                            // 根节点移除，要取得右边节点的最小值，作为根节点
                            currentNode.value = currentNode.right.getMinValue();
                            currentNode.right.remove(currentNode.value, currentNode);
                        } else {
                            // 不是根节点，等于右边子节点，然后再把左节点挂过去即可
                            BST childLeft = currentNode.left;
                            currentNode = currentNode.right;
                            currentNode.left = childLeft;
                        }
                    }
                }
            }
        }

        public int getMinValue() {
            if(left == null) {
                return value;
            } else {
                return left.getMinValue();
            }
        }
    }
}
