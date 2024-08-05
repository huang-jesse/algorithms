import java.util.Arrays;

class SolutionDigitsDP {
    private int[][] memo;
    private int n;
    public int findIntegers(int n) {
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        this.n = n;
        this.memo = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(this.memo[i], -1);
        return backtrack(m - 1, 0, true);
    }

    private int backtrack(int i, int preBit, boolean isLimit) {
        if (i < 0) return 1;
        if (!isLimit && this.memo[i][preBit] != -1) return this.memo[i][preBit];

        int curBit = (n >> i) & 1;
        int up = isLimit ? curBit : 1;
        // fill with zero-bit
        int res = backtrack(i - 1, 0, isLimit && curBit == 0);
        if (preBit == 0 && up == 1) {
            // fill with one-bit
            res += backtrack(i - 1, 1, isLimit);
        }
        if (!isLimit) {
            this.memo[i][preBit] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionDigitsDP sol = new SolutionDigitsDP();
        int n = 5;
        System.out.println("test: " + sol.findIntegers(n));
    }
}