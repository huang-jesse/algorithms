import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stackL1 = new ArrayDeque<>();
        Deque<Integer> stackL2 = new ArrayDeque<>();
        while (l1 != null) {
            stackL1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stackL2.push(l2.val);
            l2 = l2.next;
        }

        ListNode dummy = new ListNode();
        int carry = 0;
        while (!stackL1.isEmpty() || !stackL2.isEmpty() || carry != 0) {
            if (!stackL1.isEmpty()) {
                carry += stackL1.pop();
            }
            if (!stackL2.isEmpty()) {
                carry += stackL2.pop();
            }
            int digit = carry % 10;
            carry = carry / 10;
            ListNode current = new ListNode(digit);
            current.next = dummy.next;
            dummy.next = current;
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