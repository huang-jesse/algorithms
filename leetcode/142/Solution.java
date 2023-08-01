class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nodes = {3,2,0,-4};
        int pos = 1;
        ListNode head = new ListNode(nodes, pos);
        System.out.println("test: " + sol.detectCycle(head));
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
    ListNode(int[] nodes, int pos) {
        if (nodes.length == 0) return;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode cycle = null;
        for (int i = 0; i < nodes.length; i++) {
            int node = nodes[i];
            temp.next = new ListNode(node);
            temp = temp.next;
            if (i == pos) {
                cycle = temp;
            }
        }
        ListNode head = dummy.next;
        this.val = head.val;
        this.next = head.next;
        if (cycle != null) {
            temp.next = cycle;
        }
    }
}