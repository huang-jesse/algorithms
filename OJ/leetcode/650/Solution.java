class Solution {
    private static final int INF = 1000;
    private int[][] memo;
    public int minSteps(int n) {
        this.memo = new int[n+1][n+1];
        return remainSteps(n, 1, 0);
    }

    private int remainSteps(int n, int current, int copies) {
        if (n == current) {
            return 0;
        }
        if (n < current) {
            return INF;
        }

        if (this.memo[current][copies] != 0) {
            return this.memo[current][copies];
        }

        int paste = INF;
        if (copies > 0) {
            paste = remainSteps(n, current + copies, copies) + 1;
        }
        int copyAndPaste = remainSteps(n, current*2, current) + 2;
        int result = Math.min(paste, copyAndPaste);
        this.memo[current][copies] = result;
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 1000;
        System.out.println("test: " + sol.minSteps(n));
    }
}