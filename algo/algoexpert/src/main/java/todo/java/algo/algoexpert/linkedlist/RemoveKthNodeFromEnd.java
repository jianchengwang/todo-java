package todo.java.algo.algoexpert.linkedlist;

/**
 * 移除链表距离尾巴第k个节点
 * easy
 * link: https://www.algoexpert.io/questions/Remove%20Kth%20Node%20From%20End
 * 
 * simple input
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
 * k = 4
 *
 * simple output
 *
 * 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9
 * 
 */
public class RemoveKthNodeFromEnd {

    public static void main(String[] args) {

    }

    // O(n) time | O(1) space
    public static void solution(LinkedList head, int k) {
        int counter = 1;
        LinkedList first = head;
        LinkedList second = head;
        while(counter <= k) {
            second = second.next;
            counter++;
            System.out.println("counter:" + counter);
        }
        if(second == null) {
            System.out.println("remove head value:" + head.value);
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }
        while(second.next != null) {
            second = second.next;
            first = first.next;
        }
        System.out.println("first:" + first.value);
        first.next = first.next.next;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
