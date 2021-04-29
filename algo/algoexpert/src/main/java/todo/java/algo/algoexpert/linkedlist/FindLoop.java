package todo.java.algo.algoexpert.linkedlist;

/**
 * 找到链表的闭合子链表
 * hard
 * link: https://www.algoexpert.io/questions/Find%20Loop
 *
 * simple input
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *                            ^         v
 *                            9 <- 8 <- 7
 *
 * simple output
 * 4 -> 5 -> 6
 *  ^         v
 *  9 <- 8 <- 7
 *
 */
public class FindLoop {
    public static void main(String[] args) {

    }

    // O(n) time | O(1) space
    public static LinkedList solution(LinkedList head) {
        LinkedList first = head.next;
        LinkedList second = head.next.next;
        while (first != second) {
            first = first.next;
            second = second.next.next;
        }
        first = head;
        while(first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
