package todo.java.algo.algoexpert.linkedlist;

/**
 * 两个有序链表合成一个有序链表
 * hard
 * link: https://www.algoexpert.io/questions/Merge%20Linked%20Lists
 *
 * simple input
 * headOne = 2 -> 6 -> 7 -> 8
 * headTwo = 1 -> 3 -> 4 -> 5 -> 9 -> 10
 *
 * simple output
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 
 *
 */
public class MergeLinkedLists {

    // O(n + m) time | O(1) space
    public static LinkedList solution(LinkedList headOne, LinkedList headTwo) {
        LinkedList p1 = headOne;
        LinkedList p1Prev = null;
        LinkedList p2 = headTwo;
        while(p1!=null && p2!=null) {
            if(p1.value < p2.value) {
                p1Prev = p1;
                p1 = p1.next;
            } else {
                if(p1Prev != null) p1Prev.next = p2;
                p1Prev = p2;
                p2 = p2.next;
                p1Prev.next = p1;
            }
        }
        if(p1 == null) p1Prev.next = p2;
        return headOne.value < headTwo.value ? headOne: headTwo;
    }

    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
