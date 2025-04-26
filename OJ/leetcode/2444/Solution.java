class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        SparseTable st = new SparseTable(nums);
        // 遍历右端点
        long ans = 0;
        for (int r = 0; r < n; r++) {
            // 确定minK左右边界
            int[] minKlr = minKBoundary(st, r, minK);
            // 确定maxK左右边界
            int[] maxKlr = maxKBoundary(st, r, maxK);
            if (minKlr == null || maxKlr == null) continue;
            int low = Math.max(minKlr[0], maxKlr[0]);
            int high = Math.min(minKlr[1], maxKlr[1]);
            if (low > high) continue;
            ans += high - low + 1;
        }
        return ans;
    }

    private int[] minKBoundary(SparseTable st, int r, int minK) {
        // 确定minK左右边界
        int low = 0;
        int high = r;
        // 左边界
        int minl = r + 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (st.queryMin(mid, r) >= minK) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (st.queryMin(low, r) == minK) {
            minl = low;
        }
        
        // 右边界
        int minr = -1;
        low = 0;
        high = r;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (st.queryMin(mid, r) <= minK) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        if (st.queryMin(low, r) == minK) {
            minr = low;
        }
        return minl <= minr ? new int[]{minl, minr} : null;
    }

    private int[] maxKBoundary(SparseTable st, int r, int maxK) {
        // 确定maxK左右边界
        // 左边界
        int low = 0;
        int high = r;
        int maxl = r + 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (st.queryMax(mid, r) <= maxK) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (st.queryMax(low, r) == maxK) {
            maxl = low;
        }

        // 右边界
        low = 0;
        high = r;
        int maxr = -1;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (st.queryMax(mid, r) >= maxK) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        if (st.queryMax(low, r) == maxK) {
            maxr = low;
        }

        return maxl <= maxr ? new int[]{maxl, maxr} : null;
    }

    class SparseTable {
        private int n;
        private int[][] maxf;
        private int[][] minf;
        private int[] log2;
        public SparseTable(int[] nums) {
            this.n = nums.length;
            this.log2 = preProcessLog2(n);
            this.maxf = new int[n][log2[n] + 1];
            this.minf = new int[n][log2[n] + 1];
            for (int i = 0; i < n; i++) {
                maxf[i][0] = nums[i];
            }
            for (int j = 1; j <= log2[n]; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                    maxf[i][j] = Math.max(maxf[i][j - 1], maxf[i + (1 << (j - 1))][j - 1]);
                }
            }
            for (int i = 0; i < n; i++) {
                minf[i][0] = nums[i];
            }
            for (int j = 1; j <= log2[n]; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                    minf[i][j] = Math.min(minf[i][j - 1], minf[i + (1 << (j - 1))][j - 1]);
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
    
        public int queryMax(int l, int r) {
            int s = log2[r - l + 1];
            return Math.max(maxf[l][s], maxf[r - (1 << s) + 1][s]);
        }
    
        public int queryMin(int l, int r) {
            int s = log2[r - l + 1];
            return Math.min(minf[l][s], minf[r - (1 << s) + 1][s]);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,5,2,7,5};
        int minK= 1;
        int maxK = 5; // ans = 2
        System.out.println("test: " + sol.countSubarrays(nums, minK, maxK));
    }
}