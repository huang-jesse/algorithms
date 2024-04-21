import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<Integer>> results;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.results = new ArrayList<List<Integer>>();
        int index = 0;// [1...9] 取值范围的 index , index + 1 即为值本身。
        int mask = 0; // 组合标记掩码，二进制标记 [1...9] 中每一位是否选中。
        backtrack(k, n, index, mask);
        return results;
    }

    private void backtrack(int k, int target, int index, int mask) {
        if (k == 0 && target == 0) {
            // 找到一个有效组合
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                // 判断第 i 位是否选中
                if (((mask >> i) & 1) == 1) {
                    ans.add(i + 1);
                }
            }
            this.results.add(ans);
            return;
        }
        if (9 - index < k || k == 0 || target < 0) {
            // 9 - index < k 表示剩余 (9 - index) 位不足以组合 k 位。
            // 无效组合
            return;
        }

        // 不选当前位
        backtrack(k, target, index + 1, mask);
        // 选中当前位
        int currentNum = index + 1;
        mask = mask | (1 << index); // 标记选中位
        backtrack(k - 1, target - currentNum, index + 1, mask);
        mask = mask & (1 << index); // 移除选中位标记
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 3;
        int n = 9;
        System.out.println("test: " + sol.combinationSum3(k, n));
    }
}