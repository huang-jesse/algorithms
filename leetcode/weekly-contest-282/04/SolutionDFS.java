import java.util.Arrays;

class SolutionDFS {
    // Max laps without change tires, when laps large than 20 "fi * ri^(x-1) >= changeTime + fi"
    private static final int MAX_LAPS = 1 << 20;
    private int[] memo;
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        // precompute the minimum time to go around the track x times without changing tires
        int[] minTimesOfXLaps = new int[20+1];
        memo = new int[numLaps+1];
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
        return dfs(tires, changeTime, minTimesOfXLaps, numLaps) - changeTime;
    }

    private int dfs(int[][] tires, int changeTime, int[] minTimesOfXLaps, int numLaps) {
        if (numLaps == 0) {
            return 0;
        }
        if (memo[numLaps] != 0) {
            return memo[numLaps];
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < Math.min(21, numLaps+1); i++) {
            if (minTimesOfXLaps[i] == -1) {
                break;
            }
            res = Math.min(res, minTimesOfXLaps[i] + changeTime + dfs(tires, changeTime, minTimesOfXLaps, numLaps-i));
        }
        memo[numLaps] = res;
        return res;
    }

    public static void main(String[] args) {
        SolutionDFS sol = new SolutionDFS();
        int[][] tires = {{1,10},{2,2},{3,4}};
        int changeTime = 6;
        int numLaps = 5;
        // int[][] tires = {{99, 7}};
        // int changeTime = 85;
        // int numLaps = 95;
        System.out.println("test: " + sol.minimumFinishTime(tires, changeTime, numLaps));
    }
}