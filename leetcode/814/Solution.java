import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 *
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
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        return treeNodeStrList.toString();
    }
}

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left = pruneTree(root.left);
        }
        if (root.right != null) {
            root.right = pruneTree(root.right);
        }
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        System.out.println("test: " + sol.pruneTree(root));
    }
}