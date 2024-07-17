import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cur = 1;
        while (cur < depth - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            cur++;
        }
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            curNode.left = new TreeNode(val, curNode.left, null);
            curNode.right = new TreeNode(val, null, curNode.right);
        }
        return root;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        int val = 1;
        int depth = 3;
        System.out.println("test: " + sol.addOneRow(root, val, depth));
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