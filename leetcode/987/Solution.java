import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    /**
     * Definition for a binary tree node.
     */
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

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> colListMap = new HashMap<>();
        Map<TreeNode, Integer> colIndexMap = new HashMap<>();
        Queue<TreeNode> rowQueue = new LinkedList<>();
        rowQueue.offer(root);
        colIndexMap.put(root, 0);
        while (!rowQueue.isEmpty()) {
            int size = rowQueue.size();
            Map<Integer, List<Integer>> curColListMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = rowQueue.poll();
                int colIndex = colIndexMap.get(node);
                
                List<Integer> curList = curColListMap.getOrDefault(colIndex, new ArrayList<>());
                curList.add(node.val);
                curColListMap.put(colIndex, curList);

                if (node.left != null) {
                    rowQueue.offer(node.left);
                    colIndexMap.put(node.left, colIndex-1);
                }
                if (node.right != null) {
                    rowQueue.offer(node.right);
                    colIndexMap.put(node.right, colIndex+1);
                }
            }
            for (Map.Entry<Integer, List<Integer>> entry : curColListMap.entrySet()) {
                int colIndex = entry.getKey();
                List<Integer> curColList = entry.getValue();
                Collections.sort(curColList);

                List<Integer> colList = colListMap.getOrDefault(colIndex, new ArrayList<>());
                colList.addAll(curColList);
                colListMap.put(colIndex, colList);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        colListMap.keySet().stream().sorted().forEach(colIndex -> {
            List<Integer> curColList = colListMap.get(colIndex);
            ans.add(curColList);
        });
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(0);
        System.out.println("test: " + sol.verticalTraversal(root));
    }
}