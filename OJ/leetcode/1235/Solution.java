import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Integer[] indexes = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(indexes, (o1, o2) -> endTime[o1] - endTime[o2]);
        int[] dp = new int[n];
        dp[0] = profit[indexes[0]];
        for (int i = 1; i < n; i++) {
            // binary search
            int prei = rightBoundaryBinarySearch(endTime, indexes, startTime[indexes[i]], i - 1);
            if (prei != -1) {
                dp[i] = Math.max(dp[i - 1], profit[indexes[i]] + dp[prei]);
            } else {
                dp[i] = Math.max(dp[i - 1], profit[indexes[i]]);
            }
        }
        return dp[n - 1];
    }

    /**
     * endi <= endTimeTarget
     * @param endTime
     * @param indexes
     * @param endTimeTarget
     * @param r
     * @return
     */
    public int rightBoundaryBinarySearch(int[] endTime, Integer[] indexes, int endTimeTarget, int r) {
        if (endTime[indexes[0]] > endTimeTarget) {
            return -1;
        }
        int l = 0;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (endTime[indexes[mid]] <= endTimeTarget) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        // int[] startTime = {1,1,1};
        // int[] endTime = {2,3,4};
        // int[] profit = {5,6,4};
        System.out.println("test: " + sol.jobScheduling(startTime, endTime, profit));
    }
}