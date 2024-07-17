import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> memo;
    public int minDays(int n) {
        this.memo = new HashMap<>();
        return backtrack(n);
    }

    private int backtrack(int n) {
        if (n < 2) return n;
        if (memo.containsKey(n)) {
            return this.memo.get(n);
        }
        int res = 1 + Math.min(n % 2 + backtrack(n / 2), n % 3 + backtrack(n / 3));
        this.memo.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        System.out.println("test: " + sol.minDays(n));
    }
}