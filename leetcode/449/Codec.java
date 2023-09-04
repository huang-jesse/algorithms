import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private static final String COMMA_SYMBOL = ",";
    private static final String NULL_STR = "null";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            StringBuilder currentSb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                currentSb.append(COMMA_SYMBOL);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                    currentSb.append(currentNode.left.val);
                } else {
                    currentSb.append(NULL_STR);
                }
                currentSb.append(COMMA_SYMBOL);
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                    currentSb.append(currentNode.right.val);
                } else {
                    currentSb.append(NULL_STR);
                }
            }
            if (!queue.isEmpty() && currentSb.length() > 0) {
                sb.append(currentSb);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(COMMA_SYMBOL);
        int n = nodes.length;
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        int index = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (index < n) {
            int size = queue.size();
            for (int i = 0; i < size && index < n; i++) {
                TreeNode currentNode = queue.poll();
                if (!nodes[index].equals(NULL_STR)) {
                    currentNode.left = new TreeNode(Integer.valueOf(nodes[index]));
                    queue.offer(currentNode.left);
                }
                index++;
                if (index < n && !nodes[index].equals(NULL_STR)) {
                    currentNode.right = new TreeNode(Integer.valueOf(nodes[index]));
                    queue.offer(currentNode.right);
                }
                index++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec ser = new Codec();
        // Integer[] nodes = {2,1,3,null,null,4};
        Integer[] nodes = {1,null,2,null,3,null,4,null,5};
        TreeNode root = new TreeNode(nodes);
        String tree = ser.serialize(root);
        System.out.printf("tree str: %s\n", tree);
        TreeNode ans = ser.deserialize(tree);
        System.out.printf("treeNode: %s\n", ans);
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