import java.util.LinkedList;
import java.util.Queue;

class Solution {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
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
    }
 

    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null)
            return -1;
        int min = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Integer ans = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val > min) {
                    ans = ans != null ? Math.min(ans, node.val) : node.val;
                } else if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        return ans != null ? ans : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode node = new TreeNode();
        System.out.println("test: " + sol.findSecondMinimumValue(node));
    }
}