class Solution {
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA;
        ListNode pB = headB;
        // n = length of A; m = length of B;
        // n = a + c1; m = b + c2
        // if c1 == c2 then c1 == c2 == c, a + c + b == b + c + a, so pA == pB
        // else c1 != c2 then a + c1 + b + c2 = n + m; b + c2 + a + c1 == m + n, so pA == pB == null
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode headA = sol.new ListNode(4);
        headA.next = sol.new ListNode(1);

        ListNode headB = sol.new ListNode(5);
        headB.next = sol.new ListNode(0);
        headB.next.next = sol.new ListNode(1);

        ListNode sameNodes = sol.new ListNode(8);
        sameNodes.next = sol.new ListNode(4);

        headA.next.next = sameNodes;
        headB.next.next.next = sameNodes;
        System.out.println("test: " + sol.getIntersectionNode(headA, headB));
    }
}