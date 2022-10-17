import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    class Node {
        private TreeNode treeNode;
        private int index;
        private Node left;
        private Node right;
        public Node(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
    private int[][][] memo;
    public int closeLampInTree(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        int nodeIndex = 0;
        Node rootNode = new Node(root, nodeIndex++);
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                TreeNode curTreeNode = curNode.treeNode;
                if (curTreeNode.left != null) {
                    Node leftNode = new Node(curTreeNode.left, nodeIndex++);
                    curNode.left = leftNode;
                    queue.offer(leftNode);
                }
                if (curTreeNode.right != null) {
                    Node rightNode = new Node(curTreeNode.right, nodeIndex++);
                    curNode.right = rightNode;
                    queue.offer(rightNode);
                }
            }
        }
        // [nodeIndex][allSwitchTimes][curSwitch]
        this.memo = new int[nodeIndex][2][2];
        for (int i = 0; i < nodeIndex; i++) {
            for (int j = 0; j < 2; j++) {
                memo[i][j][0] = -1;
                memo[i][j][1] = -1;
            }
        }
        return backtrack(rootNode, 0, 0);
    }

    private int backtrack(Node root, int allSwitchTimes, int curSwitch) {
        if (root == null) {
            return 0;
        }
        int rootVal = root.treeNode.val;
        int rootIndex = root.index;
        if (memo[rootIndex][allSwitchTimes][curSwitch] != -1) {
            return memo[rootIndex][allSwitchTimes][curSwitch];
        }
        int curState = rootVal^allSwitchTimes^curSwitch;
        int res = Integer.MAX_VALUE;
        if (curState == 0) {
            // off
            // none switch
            res = backtrack(root.left, allSwitchTimes, 0) + backtrack(root.right, allSwitchTimes, 0);
            // switch 1,2
            res = Math.min(res, 2 + backtrack(root.left, allSwitchTimes^1, 0) + backtrack(root.right, allSwitchTimes^1, 0));
            // switch 1,3
            res = Math.min(res, 2 + backtrack(root.left, allSwitchTimes, 1) + backtrack(root.right, allSwitchTimes, 1));
            // switch 2,3
            res = Math.min(res, 2 + backtrack(root.left, allSwitchTimes^1, 1) + backtrack(root.right, allSwitchTimes^1, 1));
        } else {
            // on
            // switch 1
            res = 1 + backtrack(root.left, allSwitchTimes, 0) + backtrack(root.right, allSwitchTimes, 0);
            // switch 2
            res = Math.min(res, 1 + backtrack(root.left, allSwitchTimes^1, 0) + backtrack(root.right, allSwitchTimes^1, 0));
            // switch 3
            res = Math.min(res, 1 + backtrack(root.left, allSwitchTimes, 1) + backtrack(root.right, allSwitchTimes, 1));
            // switch 1,2,3
            res = Math.min(res, 3 + backtrack(root.left, allSwitchTimes^1, 1) + backtrack(root.right, allSwitchTimes^1, 1));
        }
        memo[rootIndex][allSwitchTimes][curSwitch] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nums = {1,1,1,1,null,null,1};
        TreeNode root = new TreeNode(nums);
        System.out.println("test: " + sol.closeLampInTree(root));
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
}