import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraphs(root, graph);
        int ans = -1;
        boolean[] visited = new boolean[100010];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curNode = queue.poll();
                List<Integer> nextNodes = graph.get(curNode);
                for (int nextNode : nextNodes) {
                    if (!visited[nextNode]) {
                        queue.offer(nextNode);
                        visited[nextNode] = true;
                    }
                }
            }
            ans++;
        }
        return ans;
    }

    private void buildGraphs(TreeNode root, Map<Integer, List<Integer>> graph) {
        if (root == null) {
            return;
        }

        int val = root.val;
        List<Integer> rootEdges = graph.getOrDefault(val, new ArrayList<>());
        graph.put(val, rootEdges);
        if (root.left != null) {
            int leftVal = root.left.val;
            rootEdges.add(leftVal);
            List<Integer> leftEdges = graph.getOrDefault(leftVal, new ArrayList<>());
            leftEdges.add(val);
            graph.put(leftVal, leftEdges);
            buildGraphs(root.left, graph);
        }
        if (root.right != null) {
            int rightVal = root.right.val;
            rootEdges.add(rightVal);
            List<Integer> rightValEdges = graph.getOrDefault(rightVal, new ArrayList<>());
            rightValEdges.add(val);
            graph.put(rightVal, rightValEdges);
            buildGraphs(root.right, graph);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nums = {1,5,3,null,4,10,6,9,2};
        TreeNode root = new TreeNode(nums);
        int start = 3;
        System.out.println("test: " + sol.amountOfTime(root, start));
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