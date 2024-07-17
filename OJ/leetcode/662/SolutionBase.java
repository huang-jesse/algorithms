import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionBase {
    class Pair {
        private TreeNode node;
        private int index;
        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        Pair rootPair = new Pair(root, 1);
        List<Pair> levelList = new ArrayList<>();
        levelList.add(rootPair);
        int ans = 0;
        while (!levelList.isEmpty()) {
            int size = levelList.size();
            int leftIndex = levelList.get(0).index;
            int rightIndex = levelList.get(size - 1).index;
            ans = Math.max(ans, rightIndex - leftIndex + 1);
            int base = leftIndex - 1;
            List<Pair> newLevelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Pair cur = levelList.get(i);
                int index = cur.index - base;
                TreeNode curNode = cur.node;
                if (curNode.left != null) {
                    newLevelList.add(new Pair(curNode.left, 2 * index));
                }
                if (curNode.right != null) {
                    newLevelList.add(new Pair(curNode.right, 2 * index + 1));
                }
            }
            levelList = newLevelList;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBase sol = new SolutionBase();
        // Integer[] nodes = new Integer[]{1,3,2,5};
        Integer[] nodes = new Integer[]{1,3,2,5,null,null,9,6,null,7};
        TreeNode root = new TreeNode(nodes);
        System.out.println("test: " + sol.widthOfBinaryTree(root));
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