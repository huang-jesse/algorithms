import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public ListNode doubleIt(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        int carryDigit = 0;
        while (!stack.isEmpty()) {
            ListNode currentNode = stack.pop();
            carryDigit += currentNode.val * 2;
            int digit = carryDigit % 10;
            currentNode.val = digit;
            carryDigit /= 10;
        }
        if (carryDigit > 0) {
            ListNode newHead = new ListNode(carryDigit);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nodes = {9,9,9};
        ListNode head = new ListNode(nodes);
        System.out.println("test: " + sol.doubleIt(head));
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