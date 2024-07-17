import java.util.ArrayList;
import java.util.Collections;
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
    private List<List<Integer>> pathSumList = new ArrayList<>();
    private int targetSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return pathSumList;
        }
        this.targetSum = targetSum;
        dfs(0, root, new ArrayList<>());
        return pathSumList;
    }

    private void dfs(int preSum, TreeNode currentNode, List<Integer> pathList) {
        preSum = preSum + currentNode.val;
        pathList.add(currentNode.val);
        if (currentNode.left == null && currentNode.right == null && preSum == targetSum) {
            List<Integer> newPathList = new ArrayList<>(pathList);
            pathSumList.add(newPathList);
        }

        if (currentNode.left != null) {
            dfs(preSum, currentNode.left, pathList);
        }
        if (currentNode.right != null) {
            dfs(preSum, currentNode.right, pathList);
        }

        // remove currentNode of pathList
        pathList.remove(pathList.size()-1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int targetSum = 3;
        System.out.println("test: " + sol.pathSum(root, targetSum));
    }
}