import java.util.ArrayList;
import java.util.List;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int nextDigit = 0;
        while (l1 != null || l2 != null) {
            int val1 = 0;
            if (l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }
            int val2 = 0;
            if (l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }
            int val = val1 + val2 + nextDigit;
            int digit = val % 10;
            nextDigit = val / 10;
            ListNode nextNode = new ListNode(digit);
            cur.next = nextNode;
            cur = cur.next;
        }
        if (nextDigit > 0) {
            cur.next = new ListNode(nextDigit);
            nextDigit = 0;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.println("test: " + sol.addTwoNumbers(l1, l2));
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
    @Override
    public String toString() {
        List<Integer> vals = new ArrayList<>();
        ListNode curNode = this;
        while (curNode != null) {
            vals.add(curNode.val);
            curNode = curNode.next;
        }
        return vals.toString();
    }
}