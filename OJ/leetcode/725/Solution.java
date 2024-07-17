/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = getListLen(head);
        int partLen = len / k;
        int remaind = len % k;
        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int curLen = partLen;
            if (remaind > 0) {
                curLen++;
                remaind--;
            }
            ListNode[] splitNodes = splitList(head, curLen);
            ans[i] = splitNodes[0];
            head = splitNodes[1];
        }
        return ans;
    }

    private int getListLen(ListNode head) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    private ListNode[] splitList(ListNode head, int len) {
        if (len == 0) {
            return new ListNode[]{null, null};
        }
        ListNode temp = head;
        while (len > 1) {
            temp = temp.next;
            len--;
        }
        ListNode nextList = temp.next;
        temp.next = null;
        return new ListNode[]{head, nextList};
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        public String toString() {
            String str = " ";
            ListNode temp = this;
            while (temp != null) {
                str += String.valueOf(temp.val) + ", ";
                temp = temp.next;
            }
            return str;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        int k = 5;
        ListNode[] listNodes = sol.splitListToParts(head, k);
        int len = listNodes.length;
        String listStr = "";
        for (int i = 0; i < len; i++) {
            listStr += "[ " + listNodes[i] + " ]";
        }
        System.out.print("test: " + listStr);
    }
}