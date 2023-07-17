import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionNew {
    private int numOfMoves = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return numOfMoves;
    }

    private int[] dfs(TreeNode root) {
        int sumOfCoins = root.val;
        int countOfNodes = 1;
        if (root.left != null) {
            int[] leftArr = dfs(root.left);
            sumOfCoins += leftArr[0];
            countOfNodes += leftArr[1];
        }
        if (root.right != null) {
            int[] rightArr = dfs(root.right);
            sumOfCoins += rightArr[0];
            countOfNodes += rightArr[1];
        }
        numOfMoves += Math.abs(sumOfCoins - countOfNodes);
        return new int[]{sumOfCoins, countOfNodes};
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        Integer[] nodes = {0,3,0,0,0,3,0,null,null,0,null,null,null,null,3};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.distributeCoins(root));
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