package d9.dstrees;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable> {
    public TreeNode<T> root;

    public boolean insert(T i) {
        if(root == null) {
            root = new TreeNode<>(i);
            return true;
        }
        TreeNode<T> currentNode = root;
        while (true) {
            if(i.compareTo(currentNode.value) < 0) {
                if(currentNode.left == null) {
                    currentNode.left = new TreeNode<>(i);
                    return true;
                }
                currentNode = currentNode.left;
            } else if(i.compareTo(currentNode.value) > 0) {
                if(currentNode.right == null) {
                    currentNode.right = new TreeNode<>(i);
                    return true;
                }
                currentNode = currentNode.right;
            } else {
                return false;
            }
        }
    }

    public boolean contains(T value) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            if(value.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else if(value.compareTo(currentNode.value) > 0) {
                currentNode = currentNode.right;
            } else {
                return true;
            }
        }
        return false;
    }


    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void midOrder(TreeNode node) {
        if (node != null) {
            midOrder(node.left);
            System.out.print(node.value + " ");
            midOrder(node.right);
        }
    }

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void levelOrder(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print(currentNode.value + " ");
            if(currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if(currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }
}
