import java.util.Arrays;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    private int[][] memo;
    public int countSteppingNumbers(String low, String high) {
        return (count(high) + MOD - count(low) + (isValid(low) ? 1 : 0)) % MOD;
    }

    private boolean isValid(String str) {
        int n = str.length();
        for (int i = 1; i < n; i++) {
            if (Math.abs(str.charAt(i) - str.charAt(i - 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    private int count(String str) {
        int n = str.length();
        memo = new int[n][10];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return backtrack(str, 0, 0, true, false);
    }

    private int backtrack(String str, int index, int preDigit, boolean isLimit, boolean isNum) {
        int n = str.length();
        if (index == n) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[index][preDigit] != -1) {
            return memo[index][preDigit];
        }
        int res = 0;
        int currentDigit = str.charAt(index) - '0';
        if (!isNum) {
            // 可以跳过当前数位
            res = backtrack(str, index + 1, 0, false, false);
        }
        // 选当前数位
        int upper = isLimit ? currentDigit : 9;
        int lower = isNum ? 0 : 1;
        for (int digit = lower; digit <= upper; digit++) {
            if (!isNum || Math.abs(digit - preDigit) == 1) {
                if (isLimit && digit == upper) {
                    res = (res + backtrack(str, index + 1, digit, true, true)) % MOD;
                } else {
                    res = (res + backtrack(str, index + 1, digit, false, true)) % MOD;
                }
            }
        }
        if (!isLimit && isNum) {
            memo[index][preDigit] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String low = "1";
        String high = "11";
        System.out.println("test: " + sol.countSteppingNumbers(low, high));
    }
}