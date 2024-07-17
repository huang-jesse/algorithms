import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SolutionOptimization {
    private int[] parents;
    private int[] nums;
    private Set<Integer> visited;
    private Map<Integer, List<Integer>> tree;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        this.parents = parents;
        this.nums = nums;
        this.visited = new HashSet<>();
        this.tree = new HashMap<>();
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int cur = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cur = i;
            }
            List<Integer> children = tree.getOrDefault(parents[i], new ArrayList<>());
            children.add(i);
            tree.put(parents[i], children);
        }
        if (cur == -1) {
            // nums[i] 不存在 1 则所有最小缺失值均为 1
            return ans;
        }
        int currentValue = 1;
        // 因为如果部分 nums[i] 超过 n 则代表在 [1, n] 的范围中一定会有缺失值
        int valueUpperBound = n + 1;
        while (cur != -1) {
            // 递归所有子节点，均设置 vsisted 为已访问
            dfs(cur);
            // 获取第一个未访问的节点的值（即最小缺失值）
            for (int value = currentValue; value <= valueUpperBound; value++) {
                if (!visited.contains(value)) {
                    // value 未访问过，代表是当前的最小缺失值
                    currentValue = value;
                    break;
                }
            }
            ans[cur] = currentValue;
            // 切换到父节点
            cur = parents[cur];
        }
        return ans;
    }

    private void dfs(int node) {
        if (visited.contains(nums[node])) {
            return;
        }
        visited.add(nums[node]);
        List<Integer> children = tree.getOrDefault(node, new ArrayList<>());
        for (int child : children) {
            dfs(child);
        }
        return;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] parents = {-1,0,0,2};
        int[] nums = {1,2,3,4};
        int[] ans = sol.smallestMissingValueSubtree(parents, nums);
        System.out.println("test: " + Arrays.toString(ans));
    }
}