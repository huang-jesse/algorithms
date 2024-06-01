class Solution {
    private static final int NUM_OF_CHILD = 3;
    private int[][] memo;
    public int distributeCandies(int n, int limit) {
        this.memo = new int[NUM_OF_CHILD][n + 1];
        if (NUM_OF_CHILD * limit < n) {
            return 0;
        }
        return backtrack(limit, n, 0);
    }

    private int backtrack(int limit, int remain, int index) {
        if (index == NUM_OF_CHILD - 1) {
            return 1;
        }
        if (this.memo[index][remain] != 0) {
            return this.memo[index][remain];
        }
        int lowerBound = Math.max(remain - limit * (NUM_OF_CHILD - index - 1), 0);
        int res = 0;
        for (int num = lowerBound; num <= Math.min(limit, remain); num++) {
            res += backtrack(limit, remain - num, index + 1);
        }
        this.memo[index][remain] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int limit = 2;
        System.out.println("test: " + sol.distributeCandies(n, limit));
    }
}