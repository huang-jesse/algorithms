import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

            pivot.next = temp;
            pivot = pivot.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        ListNode[] lists = new ListNode[n];
        int[] nodes1 = {1,4,5};
        lists[0] = new ListNode(nodes1);
        int[] nodes2 = {1,3,4};
        lists[1] = new ListNode(nodes2);
        int[] nodes3 = {2,6};
        lists[2] = new ListNode(nodes3);
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
    ListNode(int[] nodes) {
        if (nodes.length == 0) return;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        for (int i = 0; i < nodes.length; i++) {
            int node = nodes[i];
            temp.next = new ListNode(node);
            temp = temp.next;
        }
        ListNode head = dummy.next;
        this.val = head.val;
        this.next = head.next;
    }

    @Override
    public String toString() {
        ListNode temp = this;
        List<Integer> valList = new ArrayList<>();
        while (temp != null) {
            valList.add(temp.val);
            temp = temp.next;
        }
        return valList.toString();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,3,4,5});
        System.out.println("head: " + head);
    }
}