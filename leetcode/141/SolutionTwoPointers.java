class SolutionTwoPointers {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        // 快慢指针，每次快指针前进两步，慢指针前进一步
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (slow != null) {
                slow = slow.next;
            }
            if (fast != null) {
                fast = fast.next;
            }
            if (fast != null) {
                fast = fast.next;
            }
        }
        // 当快指针与慢指针相遇时，如果没有环，则他们相遇在null（快指针先到）；
        // 否则他们快指针会领先一圈后在某个节点追上慢指针
        if (slow == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        SolutionTwoPointers sol = new SolutionTwoPointers();
        int[] nodes = {3,2,0,-4};
        int pos = 1;
        ListNode head = new ListNode(nodes, pos);
        System.out.println("test: " + sol.hasCycle(head));
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
            if (pos == i) {
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