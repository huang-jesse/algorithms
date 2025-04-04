import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionOptimization {
    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
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

    static class Pair {
        private int height;
        private TreeNode node;
        Pair(int height, TreeNode node) {
            this.height = height;
            this.node = node;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Pair dfs(TreeNode node) {
        if (node == null) return new Pair(0, null);
        Pair left = dfs(node.left);
        Pair right = dfs(node.right);
        if (left.height == right.height) {
            return new Pair(left.height + 1, node);
        } else if (left.height > right.height) {
            left.height++;
            return left;
        } else {
            // left.height < right.height
            right.height++;
            return right;
        }
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        Integer[] nodes = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.subtreeWithAllDeepest(root));
    }
}