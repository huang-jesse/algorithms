import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int n = nums.length;
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode dummy = new ListNode(-1, head);
        ListNode current = dummy;
        while (current != null && current.next != null) {
            if (numsSet.contains(current.next.val)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
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
        int[] nums = {1,2,3};
        int[] nodes = {1,2,3,4,5};
        ListNode head = new ListNode(nodes);
        System.out.println("test: " + sol.modifiedList(nums, head));
    }
}