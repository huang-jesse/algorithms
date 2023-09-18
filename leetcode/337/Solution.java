import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Node {
        TreeNode node;
        int id;
        Node left;
        Node right;

        public Node(TreeNode node, int id) {
            this.id = id;
            this.node = node;
        }
    }
    private int[][] memo;
    public int rob(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        int index = 0;
        Node nodeRoot = new Node(root, index);
        queue.offer(nodeRoot);
        index++;
        // List<Node> nodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.node.left != null) {
                    current.left = new Node(current.node.left, index);
                    index++;
                    queue.offer(current.left);
                }
                if (current.node.right != null) {
                    current.right = new Node(current.node.right, index);
                    index++;
                    queue.offer(current.right);
                }
            }
        }
        this.memo = new int[2][index];
        Arrays.fill(memo[0], -1);
        Arrays.fill(memo[1], -1);
        return dfs(nodeRoot, 1);
    }

    private int dfs(Node root, int canRob) {
        if (root == null) {
            return 0;
        }
        int id = root.id;
        if (memo[canRob][id] != -1) {
            return memo[canRob][id];
        }
        int res = 0;
        if (canRob == 1) {
            // rob
            res += root.node.val + dfs(root.left, 0) + dfs(root.right, 0);
        }
        res = Math.max(res, dfs(root.left, 1) + dfs(root.right, 1));
        memo[canRob][id] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {3,4,5,1,3,null,1};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.rob(root));
    }
}


/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    TreeNode(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return;
        }
        int n = nodes.length;
        this.val = nodes[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        int index = 1;
        while (index < n) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize && index < n; i++) {
                TreeNode node = queue.poll();
                Integer leftVal = nodes[index++];
                if (leftVal != null) {
                    node.left = new TreeNode(leftVal);
                    queue.offer(node.left);
                }
                if (index == n) {
                    break;
                }
                Integer rightVal = nodes[index++];
                if (rightVal != null) {
                    node.right = new TreeNode(rightVal);
                    queue.offer(node.right);
                }
            }
        }
    }

    @Override
    public String toString() {
        List<Integer> treeNodeStrList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> preLevelList = new ArrayList<>();
        queue.offer(this);
        preLevelList.add(this.val);
        while(!queue.isEmpty()) {
            if (!preLevelList.isEmpty()) {
                treeNodeStrList.addAll(preLevelList);
                preLevelList.clear();
            }
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                    preLevelList.add(current.left.val);
                } else {
                    preLevelList.add(null);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                    preLevelList.add(current.right.val);
                } else {
                    preLevelList.add(null);
                }
            }
        }
        return treeNodeStrList.toString();
    }
}