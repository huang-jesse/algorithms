import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int amountOfTime(TreeNode root, int start) {
        // 构造 graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            List<Integer> neighbors = graph.getOrDefault(node.val, new ArrayList<Integer>());
            graph.put(node.val, neighbors);
            if (node.left != null) {
                queue.offer(node.left);
                neighbors.add(node.left.val);

                // 添加 node.left 的父节点 node 到 graph 中
                List<Integer> leftNodeNeighbors = graph.getOrDefault(node.left.val, new ArrayList<Integer>());
                leftNodeNeighbors.add(node.val);
                graph.put(node.left.val, leftNodeNeighbors);
            }
            if (node.right != null) {
                queue.offer(node.right);
                neighbors.add(node.right.val);

                // 添加 node.left 的父节点 node 到 graph 中
                List<Integer> rightNodeNeighbors = graph.getOrDefault(node.right.val, new ArrayList<Integer>());
                rightNodeNeighbors.add(node.val);
                graph.put(node.right.val, rightNodeNeighbors);
            }
        }

        // bfs
        Queue<int[]> infectedQueue = new LinkedList<>();
        infectedQueue.offer(new int[]{start, -1}); // {node, source}
        int count = 0;
        while (!infectedQueue.isEmpty()) {
            count++;
            int size = infectedQueue.size();
            for (int i = 0; i < size; i++) {
                int[] currentPair = infectedQueue.poll();
                List<Integer> neighbors = graph.getOrDefault(currentPair[0], new ArrayList<>());
                for (int next : neighbors) {
                    // 跳过当前节点的 source 节点，避免死循环
                    if (next != currentPair[1]) {
                        infectedQueue.offer(new int[]{next, currentPair[0]});
                    }
                }
            }
        }
        // 感染时间从 0 开始，因此要减 1
        return count - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {1,5,3,null,4,10,6,9,2};
        TreeNode treeNode = new TreeNode(nodes);
        int start = 3;
        System.out.println("test: " + sol.amountOfTime(treeNode, start));
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