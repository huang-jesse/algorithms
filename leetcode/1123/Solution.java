import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Pair {
        int high;
        TreeNode node;
        public Pair(int high, TreeNode node) {
            this.high = high;
            this.node = node;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair res = dfs(root);
        return res.node;
    }

    private Pair dfs(TreeNode root) {
        if (root == null) {
            return new Pair(0, null);
        }
        Pair resLeft = dfs(root.left);
        Pair resRight = dfs(root.right);
        if (resLeft.high == resRight.high) {
            // 此时 root 为 lca , high 为 resLeft 和 resRight 的 high
            return new Pair(resLeft.high + 1, root);
        } else if (resLeft.high > resRight.high) {
            resLeft.high++;
            return resLeft;
        } else {
            //resLeft.high < resRight.high
            resRight.high++;
            return resRight;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {3,5,1,6,2,0,8,null,null,7,4};
        // Integer[] nodes = {3,5,1,6,2,0,8,null,null,7,4,9};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.lcaDeepestLeaves(root));
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