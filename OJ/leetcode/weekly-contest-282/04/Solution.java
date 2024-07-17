import java.util.Arrays;

class Solution {
    // Max laps without change tires, when laps large than 20 "fi * ri^(x-1) >= changeTime + fi"
    private static final int MAX_LAPS = 1 << 20;
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        // precompute the minimum time to go around the track x times without changing tires
        int[] minTimesOfXLaps = new int[20+1];
        Arrays.fill(minTimesOfXLaps, -1);
        for (int[] tire : tires) {
            int fi = tire[0];
            int ri = tire[1];
            int t = fi;
            int total = fi;
            for (int i = 1; i < 21; i++) {
                if (minTimesOfXLaps[i] == -1 || minTimesOfXLaps[i] > total) {
                    minTimesOfXLaps[i] = total;
                }
                if (t * ri > MAX_LAPS || total + t*ri > MAX_LAPS) {
                    break;
                }
                t = t * ri;
                total = total + t;
            }
        }

        int[] dp = new int[numLaps+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < numLaps+1; i++) {
            for (int j = 1; j < 21 && i-j >= 0; j++) {
                if (minTimesOfXLaps[j] == -1) {
                    break;
                }
                int temp = dp[i-j] + minTimesOfXLaps[j] + changeTime;
                if (dp[i] == -1 || dp[i] > temp) {
                    dp[i] = temp;
                }
            }
        }
        return dp[numLaps] - changeTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] tires = {{1,10},{2,2},{3,4}};
        int changeTime = 6;
        int numLaps = 5;
        System.out.println("test: " + sol.minimumFinishTime(tires, changeTime, numLaps));
    }
}