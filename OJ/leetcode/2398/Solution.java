class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long[] preSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + runningCosts[i];
        }
        SparseTable stMax = new SparseTable(chargeTimes);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (chargeTimes[i] + runningCosts[i] > budget) continue;
            int l = i;
            int r = n - 1;
            while (l < r) {
                int mid = l + ((r - l + 1) >> 1);
                if (calculateTotalCost(i, mid, preSums, stMax) <= budget) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans = Math.max(ans, l - i + 1);
        }
        return ans;
    }

    private long calculateTotalCost(int l, int r, long[] preSums, SparseTable stMax) {
        long chargeCost = stMax.query(l, r);
        long runningCost = (preSums[r + 1] - preSums[l]) * (r - l + 1);
        return chargeCost + runningCost;
    }

    static class SparseTable {
        private int n;
        private int[][] f;
        private int[] log2;
        public SparseTable(int[] nums) {
            this.n = nums.length;
            this.log2 = preProcessLog2(n);
            this.f = new int[n][log2[n] + 1];
            for (int i = 0; i < n; i++) {
                f[i][0] = nums[i];
            }
            for (int j = 1; j <= log2[n]; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                    f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
    
        private int[] preProcessLog2(int n) {
            int[] log2 = new int[n + 1];
            log2[1] = 0;
            for (int i = 2; i <= n; i++) {
                log2[i] = log2[i / 2] + 1;
            }
            return log2;
        }
    
        public int query(int l, int r) {
            int s = log2[r - l + 1];
            return Math.max(f[l][s], f[r - (1 << s) + 1][s]);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] chargeTimes = {1,6,7,1,1};
        int[] runningCosts = {1,9,9,1,1};
        long budget = 5; // res: 2
        // int[] chargeTimes = {3,6,1,3,4};
        // int[] runningCosts = {2,1,3,4,5};
        // long budget = 25; // res: 3
        System.out.println("test: " + sol.maximumRobots(chargeTimes, runningCosts, budget));
    }
}