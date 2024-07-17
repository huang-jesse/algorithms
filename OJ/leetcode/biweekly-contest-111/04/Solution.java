import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    private int[][][] memo;
    private int k;
    private List<Integer> digits;
    private int n;
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        return calculateBeautifulIntegers(high) - calculateBeautifulIntegers(low) + (isValid(low, k) ? 1 : 0);
    }

    private boolean isValid(int num, int k) {
        if (num % k != 0) {
            return false;
        }
        int oddCount = 0;
        while (num > 0) {
            int digit = num % 10;
            if (digit % 2 == 0) {
                oddCount--;
            } else {
                oddCount++;
            }
            num /= 10;
        }
        return oddCount == 0;
    }

    private int calculateBeautifulIntegers(int num) {
        this.digits = new ArrayList<>();
        int temp = num;
        while (temp > 0) {
            digits.add(temp % 10);
            temp /= 10;
        }
        Collections.reverse(digits);
        this.n = digits.size();
        memo = new int[n][11][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        // oddCount 取值范围偏移5 {-5, 5} -> {0, 10}
        return backtrack(0, 5, 0, false, true);
    }

    private int backtrack(int index, int oddCount, int remainder, boolean isNum, boolean isLimit) {
        if (oddCount > 10 || oddCount < 0) {
            // 超过五位为奇数或偶数，该情况下奇数和偶数位数不可能相同
            return 0;
        }
        if (index == n) {
            if (isNum && oddCount == 5 && remainder == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (isNum && !isLimit && memo[index][oddCount][remainder] != -1) {
            return memo[index][oddCount][remainder];
        }
        int lower = isNum ? 0 : 1;
        int upper = isLimit ? digits.get(index) : 9;
        int res = 0;
        if (!isNum) {
            // 可以选择跳过该项
            res = backtrack(index + 1, oddCount, remainder, false, false);
        }
        for (int i = lower; i <= upper; i++) {
            int nextCount = i % 2 == 0 ? oddCount - 1 : oddCount + 1;
            int nextRemainder = (remainder * 10 + i) % k;
            res += backtrack(index + 1, nextCount, nextRemainder, true, isLimit && i == upper);
        }
        if (isNum && !isLimit) {
            memo[index][oddCount][remainder] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int low = 3;
        // int high = 31;
        // int k = 16;
        // int low = 10;
        // int high = 20;
        // int k = 3;
        int low = 47;
        int high = 100;
        int k = 18;
        System.out.println("test: " + sol.numberOfBeautifulIntegers(low, high, k));
    }
}