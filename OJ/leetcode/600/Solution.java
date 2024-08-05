import java.util.Arrays;

class Solution {
    public int findIntegers(int n) {
        int ans = 0;
        int preBit = 0;
        for (int i = 31; i >= 0; i--) {
            int curBit = (n >> i) & 1;
            if (curBit == 1) {
                // bit match
                int[][] memo = new int[i][2];
                for (int j = 0; j < i; j++) Arrays.fill(memo[j], -1);
                ans += backtrack(memo, i - 1, 0);
            }
            if (preBit == 1 && curBit == 1) break;
            if (i == 0) ans++;
            preBit = curBit;
        }
        return ans;
    }

    private int backtrack(int[][] memo, int index, int preBit) {
        if (index == -1) return 1;
        if (memo[index][preBit] != -1) return memo[index][preBit];
        int res = 0;
        if (preBit == 0) {
            // bit-one
            res += backtrack(memo, index - 1, 1);
        }
        // bit-zero
        res += backtrack(memo, index - 1, 0);
        memo[index][preBit] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        System.out.println("test: " + sol.findIntegers(n));
    }
}