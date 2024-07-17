import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null)
            return false;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        int level = 0;
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            boolean isEvenLevel = level % 2 == 0;
            int preVal = isEvenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int value = node.val;
                if (level % 2 == value % 2) {
                    return false;
                }
                if ((isEvenLevel && value <= preVal) || (!isEvenLevel && value >= preVal)) {
                    return false;
                }
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                }
                preVal = node.val;
            }
            level++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        System.out.println("test: " + sol.isEvenOddTree(root));
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
}