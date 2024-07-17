class Solution {
    public long minimumTime(int[] times, int totalTrips) {
        int maxTime = 0;
        for (int curTime : times) {
            maxTime = Math.max(maxTime, curTime);
        }
        int n = times.length;
        long left = 1;
        long right = maxTime * (long)(Math.ceil((double)totalTrips / n));
        while (left < right) {
            long mid = left + ((right - left) >> 1);
            if (check(mid, times, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean check(long time, int[] times, int totalTrips) {
        long countTrips = 0;
        for (int curTime : times) {
            countTrips += (long)(time / curTime);
        }
        return countTrips >= totalTrips;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] time = {2};
        int totalTrips =1;
        System.out.println("test: " + sol.minimumTime(time, totalTrips));
    }
}