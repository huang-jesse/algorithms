import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<List<Integer>> results;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.results = new ArrayList<List<Integer>>();
        // 组合标记数组
        int[] combinationArr = new int[candidates.length];
        // 回溯所有组合
        backtrack(candidates, 0, target, combinationArr);
        return results;
    }

    private void backtrack(int[] candidates, int index, int target, int[] combinationArr) {
        if (target == 0) {
            // 找到一个正确的组合
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < candidates.length; i++) {
                for (int j = 0; j < combinationArr[i]; j++) {
                    ans.add(candidates[i]);
                }
            }
            this.results.add(ans);
            return;
        }
        if (index >= candidates.length || target < candidates[index]) {
            // 无效组合
            return;
        }
        // 不加入当前项
        backtrack(candidates, index + 1, target, combinationArr);
        // 加入当前项（重复不同次数）
        int candidate = candidates[index];
        int times = target / candidate;
        for (int i = 1; i <= times; i++) {
            int nextTarget = target - candidate * i;
            combinationArr[index] = i;
            backtrack(candidates, index + 1, nextTarget, combinationArr);
            combinationArr[index] = 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] candidates = {2,3,6,7};
        // int target = 7;
        int[] candidates = {2,3,5};
        int target = 8;
        System.out.println("test: " + sol.combinationSum(candidates, target));
    }
}