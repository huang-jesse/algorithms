import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private int[] fa;
    private int[] parents;
    private int[] nums;
    private Map<Integer, List<Integer>> tree;
    private Map<Integer, Integer> valueToNodeMap;
    private static final int INF = 100001;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        this.parents = parents;
        this.nums = nums;
        this.fa = new int[parents.length];
        this.valueToNodeMap = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            valueToNodeMap.put(nums[i], i);
            fa[i] = i;
        }
        this.tree = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] >= 0) {
                List<Integer> children = tree.getOrDefault(parents[i], new ArrayList<>());
                children.add(i);
                tree.put(parents[i], children);
            }
        }
        int[] ans = new int[parents.length];
        dfs(0, ans);
        return ans;
    }

    private int dfs(int node, int[] ans) {
        List<Integer> children = tree.getOrDefault(node, new ArrayList<>());
        if (children.isEmpty()) {
            int res = nums[node] == 1 ? nums[node] + 1 : 1;
            ans[node] = res;
            return res;
        }
        int res = 0;
        for (int child : children) {
            int childRes = dfs(child, ans);
            res = Math.max(childRes, res);
            union(child, node);
        }
        int root = find(node);
        for (int value = res; value <= INF; value++) {
            int nodeIndex = valueToNodeMap.getOrDefault(value, -1);
            if (nodeIndex == -1 || find(nodeIndex) != root) {
                res = value;
                break;
            }
        }
        ans[node] = res;
        return res;
    }

    private int find(int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa[i]);
            return fa[i];
        }
    }

    private void union(int i, int j) {
        int rooti = find(fa[i]);
        int rootj = find(fa[j]);
        if (rooti != rootj) {
            fa[rooti] = rootj;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] parents = {-1,0,0,2};
        int[] nums = {1,2,3,4};
        int[] ans = sol.smallestMissingValueSubtree(parents, nums);
        System.out.println("test: " + Arrays.toString(ans));
    }
}