import java.util.Arrays;

class SolutionDigitBacktrack {
    private int[] dp;
    private char[] numDigits;
    private String[] digits;
    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.numDigits = String.valueOf(n).toCharArray();
        this.digits = digits;
        int m = numDigits.length;
        dp = new int[m];
        Arrays.fill(dp, -1);
        return backtrack(0, true, false);
    }

    private int backtrack(int index, boolean isLimit, boolean isNum) {
        if (index == numDigits.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && dp[index] != -1) {
            return dp[index];
        }

        int res = 0;
        if (!isNum) {
            // No digits were chose
            res += backtrack(index + 1, false, false);
        }
        char upperBound = isLimit ? numDigits[index] : '9';
        for (String digit : digits) {
            if (digit.charAt(0) > upperBound) {
                break;
            }
            boolean isNextLimit = isLimit && digit.charAt(0) == upperBound;
            res += backtrack(index + 1, isNextLimit, true);
        }
        if (!isLimit && isNum) {
            dp[index] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionDigitBacktrack sol = new SolutionDigitBacktrack();
        // String[] digits = {"1","3","5","7"};
        // int n = 100; // ans = 20
        String[] digits = {"1","4","9"};
        int n = 1000000000; // ans = 29523
        System.out.println("test: " + sol.atMostNGivenDigitSet(digits, n));
    }
}