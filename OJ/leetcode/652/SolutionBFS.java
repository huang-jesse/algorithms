import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class SolutionBFS {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> seriesOfNodes = new HashMap<>();
        List<TreeNode> duplicateSubtrees = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    String series = getNodeSeries(cur.left);
                    if (seriesOfNodes.getOrDefault(series, 0) == 1) {
                        duplicateSubtrees.add(cur.left);
                    }
                    seriesOfNodes.put(series, seriesOfNodes.getOrDefault(series, 0) + 1);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                    String series = getNodeSeries(cur.right);
                    if (seriesOfNodes.getOrDefault(series, 0) == 1) {
                        duplicateSubtrees.add(cur.right);
                    }
                    seriesOfNodes.put(series, seriesOfNodes.getOrDefault(series, 0) + 1);
                }
            }
        }
        return duplicateSubtrees;
    }

    private String getNodeSeries(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sb.append(",");
                if (cur.left == null) {
                    sb.append("null");
                } else {
                    sb.append(cur.left.val);
                    queue.offer(cur.left);
                }

                sb.append(",");
                if (cur.right == null) {
                    sb.append("null");
                } else {
                    sb.append(cur.right.val);
                    queue.offer(cur.right);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SolutionBFS sol = new SolutionBFS();
        Integer[] nodes = {};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.findDuplicateSubtrees(root));
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