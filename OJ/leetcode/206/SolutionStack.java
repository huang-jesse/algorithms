import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class SolutionStack {
    public ListNode reverseList(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            // avoid circle
            cur.next = null;
            stack.push(cur);
        }
        ListNode dummy = new ListNode(0);
        head = dummy;
        while (!stack.isEmpty()) {
            head.next = stack.pop();
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        SolutionStack sol = new SolutionStack();
        ListNode head = new ListNode(new int[]{1,2,3,4,5});
        System.out.println("test: " + sol.reverseList(head));
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