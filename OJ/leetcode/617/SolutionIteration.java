import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionIteration {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        TreeNode merged = new TreeNode(root1.val + root2.val);
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode mergedNode = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.left != null || node2.left != null) {
                if (node1.left == null) {
                    mergedNode.left = node2.left;
                } else if (node2.left == null) {
                    mergedNode.left = node1.left;
                } else {
                    TreeNode mergedLeft = new TreeNode(node1.left.val + node2.left.val);
                    mergedNode.left = mergedLeft;
                    queue.offer(mergedLeft);
                    queue1.offer(node1.left);
                    queue2.offer(node2.left);
                }
            }
            if (node1.right != null || node2.right != null) {
                if (node1.right == null) {
                    mergedNode.right = node2.right;
                } else if (node2.right == null) {
                    mergedNode.right = node1.right;
                } else {
                    TreeNode mergedRight = new TreeNode(node1.right.val + node2.right.val);
                    mergedNode.right = mergedRight;
                    queue.offer(mergedRight);
                    queue1.offer(node1.right);
                    queue2.offer(node2.right);
                }
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        SolutionIteration sol = new SolutionIteration();
        Integer[] nodes1 = {1,3,2,5};
        TreeNode root1 = new TreeNode(nodes1);
        Integer[] nodes2 = {2,1,3,null,4,null,7};
        TreeNode root2 = new TreeNode(nodes2);
        System.out.println("test: " + sol.mergeTrees(root1, root2));
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