package todo.java.algo.algoexpert.linkedlist;

/**
 * 移除链表重复元素，链表是有序的
 * easy
 * link: https://www.algoexpert.io/questions/Remove%20Duplicates%20From%20Linked%20List
 *
 * simple input
 * linkedList = 1 -> 1 -> 3 > 4 -> 4 -> 4 -> 5 -> 6 -> 6
 *
 * simple output
 * 1 -> 3 -> 4 -> 5 -> 6
 *
 */
public class RemoveDuplicatesFromLinkedList {
    public static void main(String[] args) {

    }

    // O(n) tome | O(1) space
    public static LinkedList solution(LinkedList linkedList) {
        LinkedList currentNode = linkedList;
        while (currentNode != null) {
            LinkedList nextDistinctNode = currentNode.next;
            while(nextDistinctNode!=null && nextDistinctNode.value == currentNode.value) {
                nextDistinctNode = nextDistinctNode.next;
            }
            currentNode.next = nextDistinctNode;
            currentNode = nextDistinctNode;
        }
        return linkedList;
    }

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
