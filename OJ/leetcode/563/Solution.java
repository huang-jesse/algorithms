class Solution {
    private int totalTilt = 0;
    public int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root);
        return totalTilt;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int val = node.val;
        int leftVal = dfs(node.left);
        int rightVal = dfs(node.right);
        int curTilt = Math.abs(leftVal - rightVal);
        totalTilt += curTilt;
        return val + leftVal + rightVal;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println("test: " + sol.findTilt(root));
    }
}

/* *
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
