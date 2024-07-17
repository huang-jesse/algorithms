import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    class Pair {
        private TreeNode node;
        private int row;
        private int col;
        public Pair(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
    public List<List<String>> printTree(TreeNode root) {
        // calculate height
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int h = -1;
        while (!queue.isEmpty()) {
            h++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
        }

        int m = h + 1;
        // pow(2, h + 1)
        int n = (1 << (h + 1)) - 1;
        Integer[][] metrix = new Integer[m][n];
        Queue<Pair> treeQueue = new LinkedList<>();
        treeQueue.offer(new Pair(root, 0, n / 2));
        while (!treeQueue.isEmpty()) {
            int size = treeQueue.size();
            for (int i = 0; i < size; i++) {
                Pair cur = treeQueue.poll();
                TreeNode curNode = cur.node;
                int row = cur.row;
                int col = cur.col;
                metrix[row][col] = curNode.val;
                int offset = 1 << (h - row - 1);
                if (curNode.left != null) {
                    treeQueue.offer(new Pair(curNode.left, row + 1, col - offset));
                }
                if (curNode.right != null) {
                    treeQueue.offer(new Pair(curNode.right, row + 1, col + offset));
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> rowList = Arrays.stream(metrix[i]).map(num -> {
                return num == null ? "" : String.valueOf(num);
            }).collect(Collectors.toList());
            ans.add(rowList);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Integer[] nodes = {1,2,3,null,4};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.printTree(root));
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
        // Integer[] nodes = {1,2,3,4,5,null,6,7,null,null,null,null,8};
        Integer[] nodes = {0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,1,2,3,4,5,6};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + root);
    }
}