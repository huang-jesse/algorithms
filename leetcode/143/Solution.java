import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public void reorderList(ListNode head) {
        ArrayDeque<ListNode> deque = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            deque.offerLast(current);
            current = current.next;
        }
        ListNode dummy = new ListNode();
        current = dummy;
        boolean isOdd = true;
        while (!deque.isEmpty()) {
            if (isOdd) {
                current.next = deque.pollFirst();
            } else {
                current.next = deque.pollLast();
            }
            current = current.next;
            isOdd = !isOdd;
        }
        current.next = null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nodes = {1,2,3,4,5};
        ListNode head = new ListNode(nodes);
        sol.reorderList(head);
        System.out.println("test: " + head);
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
}