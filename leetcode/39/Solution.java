import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] candidates;
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        this.candidates = candidates;
        backtrack(0, target, new int[n]);
        return ans;
    }

    private void backtrack(int index, int target, int[] comb) {
        int n = candidates.length;
        if (index == n) {
            if (target == 0) {
                ans.add(getRes(comb));
            }
            return;
        }

        int curNum = candidates[index];
        int times = 0;
        while (target >= curNum * times) {
            comb[index] = times;
            int nextTarget = target - curNum * times;
            backtrack(index+1, nextTarget, comb);
            comb[index] = 0;
            times++;
        }
    }

    private List<Integer> getRes(int[] comb) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < comb.length; i++) {
            int times = comb[i];
            while (times > 0) {
                int cur = candidates[i];
                res.add(cur);
                times--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println("test: " + sol.combinationSum(candidates, target));
    }
}