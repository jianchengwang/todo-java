package todo.java.algo.algoexpert.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 二分查找树的三种遍历方式
 * medium
 * link: https://www.algoexpert.io/questions/BST%20Traversal
 *
 * simple input
 * tree  =  10
 *        /     \
 *       5      15
 *     /   \       \
 *    2     5       22
 *  /
 * 1
 *
 * simple output
 * inOrderTraverse: [1, 2, 5, 5, 10, 15, 22]
 * preOrderTraverse: [10, 5, 2, 1, 5, 15, 22]
 * postOrderTraverse: [1, 2, 5, 5, 22, 15, 10]
 *
 */
public class BSTTraversal {
    public static void main(String[] args) {
        BST root = buildBST();
//        List<Integer> inOrder = inOrderTraverse(root, new ArrayList<>());
//        List<Integer> preOrder = preOrderTraverse(root, new ArrayList<>());
//        List<Integer> postOrder = postOrderTraverse(root, new ArrayList<>());
        List<Integer> inOrder = inOrderTraverse(root);
        List<Integer> preOrder = preOrderTraverse(root);
        List<Integer> postOrder = postOrderTraverse(root);
        System.out.print("中序遍历:");
        inOrder.stream().forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.print("先序遍历:");
        preOrder.stream().forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.print("后序遍历:");
        postOrder.stream().forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    // O(n) time | O(n) space
    public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        if(tree.left != null) {
            inOrderTraverse(tree.left, array);
        }
        array.add(tree.value);
        if(tree.right != null) {
            inOrderTraverse(tree.right, array);
        }
        return array;
    }

    // 非递归中序遍历
    public static List<Integer> inOrderTraverse(BST root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<BST> stack = new Stack();
        BST cur = root;
        while(cur!=null || !stack.empty()){
            while(cur!=null){
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            resultList.add(cur.value);
            cur = cur.right;
        }
        return resultList;
    }

    // O(n) time | O(n) space
    public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
        array.add(tree.value);
        if(tree.left != null) {
            preOrderTraverse(tree.left, array);
        }
        if(tree.right != null) {
            preOrderTraverse(tree.right, array);
        }
        return array;
    }

    // 非递归先序遍历
    public static List<Integer> preOrderTraverse(BST root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<BST> treeStack = new Stack<>();
        if(root==null) //如果为空树则返回
            return resultList;
        treeStack.push(root);
        while(!treeStack.isEmpty()){
            BST tempNode = treeStack.pop();
            if(tempNode!=null){
                resultList.add(tempNode.value);//访问根节点
                treeStack.push(tempNode.right); //入栈右孩子
                treeStack.push(tempNode.left);//入栈左孩子
            }
        }
        return resultList;
    }

    // O(n) time | O(n) space
    public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
        if(tree.left != null) {
            postOrderTraverse(tree.left, array);
        }
        if(tree.right != null) {
            postOrderTraverse(tree.right, array);
        }
        array.add(tree.value);
        return array;
    }

    // 非递归后续遍历，取巧就是先序遍历倒置
    public static List<Integer> postOrderTraverse(BST root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<BST> treeStack = new Stack<>();
        if(root==null) //如果为空树则返回
            return resultList;
        treeStack.push(root);
        while(!treeStack.isEmpty()){
            BST tempNode = treeStack.pop();
            if(tempNode!=null){
                resultList.add(tempNode.value);//访问根节点
                treeStack.push(tempNode.left); //入栈左孩子
                treeStack.push(tempNode.right);//入栈右孩子
            }
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private static BST buildBST() {
        BST root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
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
