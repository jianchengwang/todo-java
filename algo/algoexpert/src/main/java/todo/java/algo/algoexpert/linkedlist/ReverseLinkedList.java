package todo.java.algo.algoexpert.linkedlist;

/**
 * 倒置链表
 * hard
 * link: https://www.algoexpert.io/questions/Reverse%20Linked%20List
 *
 * simple input
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * simple output
 * head = 5 -> 4 -> 3 -> 2 -> 1 -> 0 
 *
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

    }

    // O(n) time | O(1) space
    public static LinkedList solution(LinkedList head) {
        LinkedList preNode = null;
        LinkedList currentNode = head;
        while (currentNode != null) {
            LinkedList nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
    }


    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
