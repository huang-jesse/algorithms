import java.util.Arrays;

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        // ans = (sum - neg) - neg
        int sum = Arrays.stream(stones).sum();
        int maxOfTwiceNeg = sum / 2;
        // f[n][maxOfTwiceNeg] = neg
        // if stones[i-1] > j, then f[i][j] = f[i-1][j]
        // else f[i][j] = max(f[i-1][j], f[i-1][j - stones[i-1]] + stones[i-1])
        // initial f[0][0] = 0;
        int[][] f = new int[n+1][maxOfTwiceNeg+1];
        for (int i = 1; i <= n; i++) {
            int cur = stones[i-1];
            for (int j = 0; j <= maxOfTwiceNeg; j++) {
                if (cur > j) {
                    f[i][j] = f[i-1][j];
                } else {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-cur] + cur);
                }
            }
        }
        return sum - 2*f[n][maxOfTwiceNeg];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] stones = {2,7,4,1,8,1};
        System.out.println("test: " + sol.lastStoneWeightII(stones));
    }
}