class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        int minV = Integer.MAX_VALUE;
        int maxV = 0;
        double minTime = (n-1) + 0.01;
        if (minTime > hour) return -1;
        for (int i = 0; i < n; i++) {
            int d = dist[i];
            minV = Math.min(minV, (int)(d/hour));
            if (i == n-1) {
                maxV = Math.max(maxV, d*100);
            } else {
                maxV = Math.max(maxV, d);
            }
        }
        minV = Math.max(minV, 1);

        // binary search
        while (minV < maxV) {
            int mid = minV + (maxV-minV)/2;
            if (check(mid, dist, hour)) {
                maxV = mid;
            } else {
                minV = mid + 1;
            }
        }
        return minV;
    }

    private boolean check(int v, int[] dist, double hour) {
        int n = dist.length;
        double totalTime = 0;
        for (int i = 0; i < n; i++) {
            int d = dist[i];
            double cur = Math.ceil((double)d/v);
            if (i == n-1) {
                cur = (double)d/v;
            }
            totalTime += cur;
        }
        return totalTime <= hour;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] dist = {1,3,2};
        double hour = 2.01;
        System.out.println("test: " + sol.minSpeedOnTime(dist, hour));
    }
}