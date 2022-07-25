import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    @Override
    public String toString() {
        List<String> treeNodeStrList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                treeNodeStrList.add("null");
            } else {
                treeNodeStrList.add(String.valueOf(current.val));
                if (current.right != null) {
                    queue.offer(current.left);
                    queue.offer(current.right);
                } else {
                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                }
            }
        }
        return treeNodeStrList.toString();
    }
}

class CBTInserter {
    private TreeNode root;
    private Queue<TreeNode> candidateNodes = new LinkedList<>();
    public CBTInserter(TreeNode root) {
        this.root = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left == null || node.right == null) {
                candidateNodes.offer(node);
            }
        }
    }

    public int insert(int val) {
        TreeNode currentNode = candidateNodes.peek();
        TreeNode nextNode = new TreeNode(val);
        if (currentNode.left == null) {
            currentNode.left = nextNode;
        } else {
            currentNode.right = nextNode;
            candidateNodes.poll();
        }
        candidateNodes.offer(nextNode);
        return currentNode.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        CBTInserter sol = new CBTInserter(root);
        sol.insert(3);
        sol.insert(4);
        System.out.println("test: " + sol.get_root());
    }
}