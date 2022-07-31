import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    @Override
    public String toString() {
        List<String> treeNodeStrList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                treeNodeStrList.add("null");
            } else {
                treeNodeStrList.add(String.valueOf(current.val));
                if (current.right != null) {
                    queue.offer(current.left);
                    queue.offer(current.right);
                } else {
                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                }
            }
        }
        return treeNodeStrList.toString();
    }
}

class Solution {
    private static final int MIN_VAL = -100010;
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int maxLevelSum = MIN_VAL;
        int ans = 1;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            int currentLevelSum = 0;
            while (currentLevelSize > 0) {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
                currentLevelSize--;
            }
            if (currentLevelSum > maxLevelSum) {
                maxLevelSum = currentLevelSum;
                ans = level;
            }
            level++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        System.out.println("test: " + sol.maxLevelSum(root));
    }
}