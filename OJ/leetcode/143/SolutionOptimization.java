import java.util.ArrayList;
import java.util.List;

class SolutionOptimization {
    public void reorderList(ListNode head) {
        // 用快慢指针，找到中间节点
        ListNode middle = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = middle.next;
        middle.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    private void mergeList(ListNode l1, ListNode l2) {
        while (l2 != null) {
            ListNode temp = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
            l2 = temp;
        }
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
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