import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    private int[] allAnswer;
    private int nodeCount;
    class CustTreeNode {
        private TreeNode node;
        private int high;
        private CustTreeNode left;
        private CustTreeNode right;
        CustTreeNode(TreeNode node) {
            this.node = node;
        }
    }
    public int[] treeQueries(TreeNode root, int[] queries) {
        CustTreeNode custRoot = new CustTreeNode(root);
        dfsCalTreeHigh(custRoot);
        this.allAnswer = new int[nodeCount + 1];
        dfsQuery(custRoot, 0, 0);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = allAnswer[queries[i]];
        }
        return ans;
    }

    private void dfsQuery(CustTreeNode custNode, int depth, int highOfRemainTree) {
        int nodeVal = custNode.node.val;
        int curAnswer = highOfRemainTree;
        allAnswer[nodeVal] = curAnswer;

        highOfRemainTree = Math.max(highOfRemainTree, depth);
        if (custNode.left != null) {
            int nextHightOfRemainTree = highOfRemainTree;
            if (custNode.right != null) {
                nextHightOfRemainTree = Math.max(nextHightOfRemainTree, custNode.right.high + 1 + depth);
            }
            dfsQuery(custNode.left, depth + 1, nextHightOfRemainTree);
        }
        if (custNode.right != null) {
            int nextHightOfRemainTree = highOfRemainTree;
            if (custNode.left != null) {
                nextHightOfRemainTree = Math.max(nextHightOfRemainTree, custNode.left.high + 1 + depth);
            }
            dfsQuery(custNode.right, depth + 1, nextHightOfRemainTree);
        }
    }

    private int dfsCalTreeHigh(CustTreeNode custNode) {
        nodeCount++;
        int high = 0;
        TreeNode node = custNode.node;
        if (node.left != null) {
            CustTreeNode leftNode = new CustTreeNode(node.left);
            custNode.left = leftNode;
            high = dfsCalTreeHigh(leftNode) + 1;
        }
        if (node.right != null) {
            CustTreeNode rightNode = new CustTreeNode(node.right);
            custNode.right = rightNode;
            high = Math.max(high, dfsCalTreeHigh(rightNode) + 1);
        }
        custNode.high = high;
        return high;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {5,8,9,2,1,3,7,4,6};
        TreeNode root = new TreeNode(nodes);
        int[] queries = {3,2,4,8};
        System.out.println("test: " + Arrays.stream(sol.treeQueries(root, queries)).boxed().collect(Collectors.toList()));
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