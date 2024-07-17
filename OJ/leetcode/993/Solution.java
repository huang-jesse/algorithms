import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        // add root
        queue.offer(new Node(0, 0, root));
        Node tempNode = null;
        // bfs
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode current = node.current;
            int parentVal = node.parentVal;
            int deepth = node.deepth;
            if (current.val == x || current.val == y) {
                if (tempNode != null) {
                    if (tempNode.parentVal != parentVal && tempNode.deepth == deepth) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // find one
                    tempNode = node;
                }
            }

            if (current.left != null) {
                queue.offer(new Node(current.val, deepth+1, current.left));
            }
            if (current.right != null) {
                queue.offer(new Node(current.val, deepth+1, current.right));
            }
        }
        return false;
    }

    private class Node {
        int parentVal;
        int deepth;
        TreeNode current;
        Node(int parentVal, int deepth, TreeNode current) {
            this.parentVal = parentVal;
            this.deepth = deepth;
            this.current = current;
        }
    }

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

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);

        System.out.println("test: " + sol.isCousins(root, 4, 5));
    }
}