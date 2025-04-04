import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
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

    private int[] heights = new int[1001];
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // calculate heights
        dfs(root);
        // find subtree
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null && cur.right == null) {
                return cur;
            } else if (cur.left == null) {
                cur = cur.right;
            } else if (cur.right == null) {
                cur = cur.left;
            } else if (heights[cur.left.val] == heights[cur.right.val]) {
                return cur;
            } else if (heights[cur.left.val] > heights[cur.right.val]) {
                cur = cur.left;
            } else {
                // heights[cur.left.val] < heights[cur.right.val]
                cur = cur.right;
            }
        }
        return null;
    }

    private int dfs(TreeNode node) {
        if (node == null) return -1;
        int height = Math.max(dfs(node.left), dfs(node.right)) + 1;
        this.heights[node.val] = height;
        return height;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.subtreeWithAllDeepest(root));
    }
}