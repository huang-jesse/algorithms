import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionBFS {
    class Pair {
        TreeNode current;
        int maxVal;
        public Pair(TreeNode current, int maxVal) {
            this.current = current;
            this.maxVal = maxVal;
        }
    }
    private static final int INF = 0x3fffffff;
    public int goodNodes(TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, -INF));
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                TreeNode current = pair.current;
                int maxVal = Math.max(pair.current.val, pair.maxVal);
                if (maxVal <= current.val) {
                    ans++;
                }
                if (current.left != null) {
                    queue.offer(new Pair(current.left, maxVal));
                }
                if (current.right != null) {
                    queue.offer(new Pair(current.right, maxVal));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBFS sol = new SolutionBFS();
        Integer[] nodes = {3,1,4,3,null,1,5};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.goodNodes(root));
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