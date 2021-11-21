import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};


class Solution {
    public int maxDepth(Node root) {
        return dfs(root);
    }

    private int dfs(Node node) {
        if (node == null)
            return 0;
        int count = 0;
        if (node.children != null) {
            for (Node child : node.children) {
                count = Math.max(count, dfs(child));
            }
        }
        return 1 + count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Node root = new Node(1);
        System.out.println("test: " + sol.maxDepth(root));
    }
}