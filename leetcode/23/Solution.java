import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> compare = (o1, o2) -> o1.val - o2.val;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(compare);
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode pivot = dummy;
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            ListNode nextNode = temp.next;
            if (nextNode != null) {
                pq.offer(nextNode);
            }

            temp.next = null;
            pivot.next = temp;
            pivot = pivot.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        ListNode[] lists = new ListNode[] {node1,node2};
        System.out.println("test: " + sol.mergeKLists(lists));
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    // @Override
    // public int compareTo(ListNode node) {
    //     if (this.val == node.val) {
    //         return 0;
    //     } else if (this.val > node.val) {
    //         return 1;
    //     } else {
    //         return -1;
    //     }
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode temp = this;
        while (temp != null) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(String.valueOf(temp.val));
            temp = temp.next;
        }
        return sb.toString();
    }
}