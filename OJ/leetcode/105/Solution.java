import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    private int[] preorder;
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(0, preorder.length-1, 0, inorder.length-1);
    }

    /**
     * [根节点, [左子树前序遍历结果], [右子树前序遍历结果]]
     * [[左子树中序遍历结果], 根节点, [右子树中序遍历结果]]
     * len([左子树前序遍历结果]) == len([左子树中序遍历结果])
     */
    private TreeNode buildTree(int preStart, int preEnd, int inStart, int inEnd) {
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inorderMap.get(root.val);
        int leftChildLen = rootIndex - inStart;
        if (preStart+1 <= preStart+leftChildLen) {
            root.left = buildTree(preStart+1, preStart+leftChildLen, inStart, inStart+leftChildLen-1);

        }
        if (preStart+leftChildLen+1 <= preEnd) {
            root.right = buildTree(preStart+leftChildLen+1, preEnd, rootIndex+1, inEnd);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] preorder = {1,2};
        // int[] inorder = {2,1};
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println("test: " + sol.buildTree(preorder, inorder));
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