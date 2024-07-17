import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
public class Codec {
    private static final String COMMA = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> treeList = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                treeList.add(null);
            } else {
                treeList.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return treeList.stream().map(num -> String.valueOf(num)).collect(Collectors.joining(COMMA));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        List<Integer> treeNodes = Arrays.stream(data.split(COMMA))
                                    .map(str -> "null".equals(str) ? null : Integer.parseInt(str))
                                    .collect(Collectors.toList());
        Queue<TreeNode> queue = new LinkedList<>();
        int n = treeNodes.size();
        TreeNode root = new TreeNode(treeNodes.get(0));
        queue.offer(root);
        int i = 1;
        while (i < n) {
            TreeNode curNode = queue.poll();
            Integer leftVal = treeNodes.get(i++);
            if (leftVal != null) {
                TreeNode leftNode = new TreeNode(leftVal);
                curNode.left = leftNode;
                queue.offer(leftNode);
            }
            if (i == n) {
                break;
            }
            Integer rightVal = treeNodes.get(i++);
            if (rightVal != null) {
                TreeNode rightNode = new TreeNode(rightVal);
                curNode.right = rightNode;
                queue.offer(rightNode);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec sol = new Codec();
        Integer[] nums = {1,2,3,null,null,4,5};
        TreeNode root = new TreeNode(nums);
        String serializeStr = sol.serialize(root);
        System.out.printf("serialize: %s\n", serializeStr);
        System.out.printf("deserialize: %s\n", sol.deserialize(serializeStr));
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