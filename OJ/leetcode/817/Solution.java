import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        ListNode temp = head;
        while (temp != null) {
            if (set.contains(temp.val)) {
                while (temp != null && set.contains(temp.val)) {
                    temp = temp.next;
                }
                ans++;
            } else {
                temp = temp.next;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nodes = {0,1,2,3,4};
        int[] nums = {0,3,1,4};
        ListNode head = new ListNode(nodes);
        System.out.println("test: " + sol.numComponents(head, nums));
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