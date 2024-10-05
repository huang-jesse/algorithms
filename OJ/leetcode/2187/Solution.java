import java.util.Arrays;

class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int minTime = Arrays.stream(time).min().getAsInt();
        long maxLimit = (long)minTime * totalTrips;

        long l = 1;
        long r = maxLimit;
        while (l < r) {
            long mid = l + ((r - l) >> 1);
            if (check(time, totalTrips, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] time, int totalTrips, long totalTime) {
        long totalCount = 0;
        for (int t : time) {
            long count = totalTime / t;
            totalCount += count;
        }
        return totalCount >= totalTrips;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] time = {1,2,3};
        int totalTrips = 5;
        System.out.println("test: " + sol.minimumTime(time, totalTrips));
    }
}