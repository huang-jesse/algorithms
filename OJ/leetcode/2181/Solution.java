import java.util.ArrayList;
import java.util.List;

class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode mergeCur = dummy;
        while(head != null) {
            ListNode current = head.next;
            if (current == null) break;
            int sum = 0;
            while (current.val != 0) {
                sum += current.val;
                current = current.next;
            }
            mergeCur.next = new ListNode(sum);
            mergeCur = mergeCur.next;

            head = current;
        }
        return dummy.next;
    }

    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
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

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode head = new ListNode(new int[]{0,3,1,0,4,5,2,0});
        System.out.println("test: " + sol.mergeNodes(head));
    }
}