import java.util.HashMap;
import java.util.Map;

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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> pathPreSum = new HashMap<>();
        pathPreSum.put(0L, 1);
        return dfs(root, targetSum, 0L, pathPreSum);
    }

    private int dfs(TreeNode node, int targetSum, long preSum, Map<Long, Integer> pathPreSumMap) {
        if (node == null) {
            return 0;
        }
        preSum = preSum + node.val;
        long lastPreSum = preSum - targetSum;
        int countPath = pathPreSumMap.getOrDefault(lastPreSum, 0);

        int countPreSum = pathPreSumMap.getOrDefault(preSum, 0);
        pathPreSumMap.put(preSum, countPreSum+1);
        
        // left
        countPath += dfs(node.left, targetSum, preSum, pathPreSumMap);
        // right
        countPath += dfs(node.right, targetSum, preSum, pathPreSumMap);

        pathPreSumMap.put(preSum, countPreSum);
        return countPath;
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
        TreeNode root = new TreeNode(0);
        int targetSum = 0;
        System.out.println("test: " + sol.pathSum(root, targetSum));
    }
}