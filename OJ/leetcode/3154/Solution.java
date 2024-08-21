import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Long, Integer> memo;
    public int waysToReachStair(int k) {
        this.memo = new HashMap<>();
        return backtrack(k, 1, 0, 0);
    }

    private int backtrack(int k, int i, int jump, int isConsecutive) {
        if (i > k + 1 || jump > 30) return 0;
        long hash = ((long)i << 32) | jump << 1 | isConsecutive;
        if (this.memo.containsKey(hash)) return this.memo.get(hash);
        int res = 0;
        if (i == k) res++;
        if (i > 0 && isConsecutive == 0) {
            res += backtrack(k, i - 1, jump, 1);
        }
        res += backtrack(k, i + (1 << jump), jump + 1, 0);
        this.memo.put(hash, res);
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 2; // res: 4
        System.out.println("test: " + sol.waysToReachStair(k));
    }
}