import java.util.HashMap;
import java.util.Map;

class Solution {
    public static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Map<Node, Node> nodeMap = new HashMap<>();
        Node headOfCopy = new Node(Integer.MIN_VALUE);
        Node headOfTemp = new Node(Integer.MIN_VALUE);
        headOfTemp.next = head;
        Node cur = headOfTemp;
        Node curOfCopy = headOfCopy;
        while (cur.next != null) {
            cur = cur.next;
            Node newNode = new Node(cur.val);
            curOfCopy.next = newNode;

            curOfCopy = curOfCopy.next;
            nodeMap.put(cur, newNode);
        }
        cur = headOfTemp;
        curOfCopy = headOfCopy;
        while (cur.next != null) {
            cur = cur.next;
            curOfCopy = curOfCopy.next;
            if (cur.random != null) {
                curOfCopy.random = nodeMap.get(cur.random);
            }
        }
        return headOfCopy.next;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        Node first = new Node(3);
        Node second = new Node(3);
        Node third = new Node(3);

        Node head = first;
        head.next = second;
        second.random = first;
        second.next = third;
        System.out.println("test: " + sol.copyRandomList(head));
    }
}