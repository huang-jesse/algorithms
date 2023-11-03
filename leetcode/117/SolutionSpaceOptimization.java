class SolutionSpaceOptimization {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node ans = root;
        Node cur = root;
        while (cur != null) {
            // dummy node
            Node head = new Node(-1);
            Node tail = head;
            for (Node i = cur; i != null; i = i.next) {
                if (i.left != null) {
                    tail.next = i.left;
                    tail = tail.next;
                }
                if (i.right != null) {
                    tail.next = i.right;
                    tail = tail.next;
                }
            }
            cur = head.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionSpaceOptimization sol = new SolutionSpaceOptimization();
        Node root = new Node(0);
        System.out.println("test: " + sol.connect(root));
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};