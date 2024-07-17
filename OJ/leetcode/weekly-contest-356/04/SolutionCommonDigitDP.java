import java.util.Arrays;

class SolutionCommonDigitDP {
    private final static int MOD = (int)(1e9 + 7);
    private int[][] memo;
    public int countSteppingNumbers(String low, String high) {
        int lowLen = low.length();
        boolean isLowStepNum = true;
        for (int i = 1; i < lowLen; i++) {
            if (Math.abs(low.charAt(i) - low.charAt(i - 1)) != 1) {
                isLowStepNum = false;
            }
        }
        int ans = (calc(high) - calc(low) + MOD) % MOD;
        if (isLowStepNum) {
            // 多减了 low 这个步进数字，要加回来
            ans = (ans + 1) % MOD;
        }
        return ans;
    }

    private int calc(String s) {
        int m = s.length();
        memo = new int[m][10];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], -1); // -1 表示没有计算过
        return count(s, 0, 0, true, false);
    }

    /**
     * 递归数位DP通用模板
     * @param num 字符串数字
     * @param index 当前是第index位
     * @param pre 前一个数字
     * @param isLimit 表示当前是否受到了 num 的约束（注意要构造的数字不能超过 num）。
     * @param isNum 表示 index 前面的数位是否填了数字
     * @return
     */
    private int count(String num, int index, int pre, boolean isLimit, boolean isNum) {
        if (pre >= 10 || pre < 0) {
            return 0;
        }
        int n = num.length();
        if (index == n) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[index][pre] != -1) {
            return memo[index][pre];
        }
        int res = 0;
        if (!isNum) {
            // 可以跳过当前数位
            res = count(num, index + 1, 0, false, false);
        }
        int up = isLimit ? num.charAt(index) - '0' : 9; // 如果前面填的数字都和 s 的一样，那么这一位至多填数字 s[i]（否则就超过 s 啦）
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            // 枚举要填入的数字 d
            if (!isNum || Math.abs(d - pre) == 1) {
                // 第一位数字随便填，其余必须相差 1
                res = (res + count(num, index + 1, d, isLimit && d == up, true)) % MOD;
            }
        }
        if (!isLimit && isNum) {
            memo[index][pre] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionCommonDigitDP sol = new SolutionCommonDigitDP();
        String low = "1";
        String high = "11";
        System.out.println("test: " + sol.countSteppingNumbers(low, high));
    }
}