import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int curVal = root.val;
        int leftSameNodeCount = 0;
        int rightSameNodeCount = 0;
        int count = dfs(root.left);
        if (root.left != null && curVal == root.left.val) {
            leftSameNodeCount = count;
        }
        count = dfs(root.right);
        if (root.right != null && curVal == root.right.val) {
            rightSameNodeCount = count;
        }
        // calculate the length of the path
        ans = Math.max(ans, leftSameNodeCount + rightSameNodeCount + 1 - 1);
        // count the same nodes
        int sameNodeCount = Math.max(leftSameNodeCount, rightSameNodeCount) + 1;
        return sameNodeCount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Integer[] nums = {5,4,5,1,1,5};
        // Integer[] nums = {5};
        Integer[] nums = {1,4,5,4,4,5};
        // Integer[] nums = {1,null,1,1,1,1,1,1};
        // Integer[] nums = {};
        TreeNode root = new TreeNode(nums);
        System.out.println("test: " + sol.longestUnivaluePath(root));
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

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(1);
        // root.right = new TreeNode(0);
        // root.right.left = new TreeNode(0);
        // root.right.right = new TreeNode(1);
        // System.out.println("test: " + root);
        Integer[] nodes = {1,2,3,4,5,null,6,7,null,null,null,null,8};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + root);
    }
}