import java.util.ArrayList;
import java.util.List;

class SolutionNew {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int nextDigit = 0;
        while (l1 != null || l2 != null) {
            int cur = nextDigit;
            if (l1 != null) {
                cur += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                cur += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(cur % 10);
            current = current.next;
            nextDigit = cur / 10;
        }
        if (nextDigit > 0) {
            current.next = new ListNode(nextDigit);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
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