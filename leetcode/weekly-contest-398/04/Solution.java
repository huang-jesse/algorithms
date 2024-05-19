import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Long, Integer> memo;
    public int waysToReachStair(int k) {
        int jump = 0;
        int i = 1;
        this.memo = new HashMap<>();
        return backtrack(i, jump, 0, k);
    }

    private int backtrack(int i, int jump, int isConsecutive, int k) {
        if (i > k + 1) return 0;
        long status = ((long) i << 32) | jump << 1 | isConsecutive; // 用一个 long 表示状态
        if (this.memo.containsKey(status)) {
            return this.memo.get(status);
        }

        int res = k == i ? 1 : 0;
        if (isConsecutive == 0 && i > 0) {
            res += backtrack(i - 1, jump, 1, k);
        }
        res += backtrack(i + (1 << jump), jump + 1, 0, k);
        this.memo.put(status, res);
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 1;// 4
        // int k = 8388598;
        System.out.println("test: " + sol.waysToReachStair(k));
    }
}