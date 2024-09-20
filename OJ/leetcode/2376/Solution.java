import java.util.Arrays;

class Solution {
    private int[][] memo;
    public int countSpecialNumbers(int n) {
        int digits = 0;
        int cur = n;
        while (cur > 0) {
            cur /= 10;
            digits++;
        }
        int[] digitArr = new int[digits];
        cur = n;
        for (int i = digits - 1; i >= 0; i--) {
            digitArr[i] = cur % 10;
            cur /= 10;
        }
        this.memo = new int[digits][1 << 10];
        for (int i = 0; i < digits; i++) Arrays.fill(this.memo[i], -1);
        return backtrack(digitArr, digits, 0, 0, true, false);
    }

    private int backtrack(int[] digitArr, int digits, int index, int mask, boolean isLimit, boolean isNum) {
        if (index == digits) return isNum ? 1 : 0;

        if (!isLimit && isNum && this.memo[index][mask] != -1) {
            return this.memo[index][mask];
        }

        int res = 0;
        // skip current index
        if (!isNum) {
            res += backtrack(digitArr, digits, index + 1, mask, false, false);
        }

        int digitUp = isLimit ? digitArr[index] : 9;
        // digit: 0 ~ 9
        for (int digit = isNum ? 0 : 1; digit <= digitUp; digit++) {
            if (((mask >> digit) & 1) != 0) continue;
            res += backtrack(digitArr, digits, index + 1, mask | (1 << digit), isLimit && digitArr[index] == digit, true);
        }
        if (!isLimit && isNum) {
            this.memo[index][mask] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 135; // res: 110
        // int n = 20; // res: 19
        System.out.println("test: " + sol.countSpecialNumbers(n));
    }
}