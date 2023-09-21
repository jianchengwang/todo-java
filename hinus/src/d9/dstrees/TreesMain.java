package d9.dstrees;

public class TreesMain {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(5);
        bst.insert(4);
        bst.insert(6);
        System.out.println("preOrder: ");
        bst.preOrder(bst.root);
        System.out.println();
        System.out.println("midOrder: ");
        bst.midOrder(bst.root);
        System.out.println();
        System.out.println("postOrder: ");
        bst.postOrder(bst.root);
        System.out.println();
        System.out.println("levelOrder: ");
        bst.levelOrder(bst.root);
        System.out.println();
        System.out.println(bst.contains(8));
    }
}
