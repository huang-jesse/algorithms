import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 将 start 节点作为树的子节点，计算树的任意节点（除了 start 子树）到 start 节点的最大树直径 d ;
 * 同时计算 start 子树的最大深度 depth ，则答案 ans = max(d, depth)
 */
class SolutionCalculateTreeDiameter {
    private int ans;
    public int amountOfTime(TreeNode root, int start) {
        dfs(root, start);
        return ans;
    }

    /**
     * dfs
     * @param node
     * @param start
     * @return {resDepth, isStartFound}
     */
    private int[] dfs(TreeNode node, int start) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] leftRes = dfs(node.left, start);
        int[] rightRes = dfs(node.right, start);
        int leftLen = leftRes[0];
        int leftFound = leftRes[1];
        int rightLen = rightRes[0];
        int rightFound = rightRes[1];
        if (node.val == start) {
            // 找到 start 节点
            // 更新答案，计算 start 子树的最大深度 depth
            ans = Math.max(leftLen, rightLen);
            return new int[]{1, 1};
        } else if (leftFound == 1 || rightFound == 1) {
            // 子树包含 start 节点，可以计算树直径
            // 更新答案，计算到以 start 为子节点的树直径
            ans = Math.max(ans, leftLen + rightLen);
            // 到子节点 start 的深度
            int startDepth = leftFound == 1 ? leftLen : rightLen;
            return new int[]{startDepth + 1, 1};
        } else {
            // 当前子树的最大深度
            return new int[]{Math.max(leftLen, rightLen) + 1, 0};
        }
    }

    public static void main(String[] args) {
        SolutionCalculateTreeDiameter sol = new SolutionCalculateTreeDiameter();
        Integer[] nodes = {1,5,3,null,4,10,6,9,2};
        TreeNode treeNode = new TreeNode(nodes);
        int start = 3;
        System.out.println("test: " + sol.amountOfTime(treeNode, start));
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