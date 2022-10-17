class SolutionSegmentTree {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        SparseTableMax stMax = new SparseTableMax(nums);
        SparseTableMin stMin = new SparseTableMin(nums);
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int maxLeft = maxLeftBoundary(i, n - 1, maxK, stMax);
            int maxRight = maxRightBoundary(i, n - 1, maxK, stMax);
            if (maxLeft == -1 || maxRight == -1) {
                continue;
            }
            int minLeft = minLeftBoundary(i, n - 1, minK, stMin);
            int minRight = minRightBoundary(i, n - 1, minK, stMin);
            if (minLeft == -1 || minRight == -1) {
                continue;
            }
            int left = Math.max(maxLeft, minLeft);
            int right = Math.min(maxRight, minRight);
            if (left <= right) {
                ans += (right - left) + 1;
            }
        }
        return ans;
    }

    private int maxLeftBoundary(int l, int r, int target, SparseTableMax stMax) {
        if (stMax.query(l, r) < target) {
            return -1;
        }
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (stMax.query(l, mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int maxRightBoundary(int l, int r, int target, SparseTableMax stMax) {
        if (stMax.query(l, l) > target) {
            return -1;
        }
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (stMax.query(l, mid) <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private int minLeftBoundary(int l, int r, int target, SparseTableMin stMin) {
        if (stMin.query(l, r) > target) {
            return -1;
        }
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (stMin.query(l, mid) <= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int minRightBoundary(int l, int r, int target, SparseTableMin stMin) {
        if (stMin.query(l, l) < target) {
            return -1;
        }
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (stMin.query(l, mid) >= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        SolutionSegmentTree sol = new SolutionSegmentTree();
        int[] nums = {1,3,5,2,7,5};
        int minK = 1;
        int maxK = 5; // ans = 2
        System.out.println("test: " + sol.countSubarrays(nums, minK, maxK));
    }
}

class SparseTableMin {
    private int n;
    private int[][] f;
    private int[] log2;
    public SparseTableMin(int[] nums) {
        this.n = nums.length;
        this.log2 = preProcessLog2(n);
        this.f = new int[n][log2[n] + 1];
        for (int i = 0; i < n; i++) {
            f[i][0] = nums[i];
        }
        for (int j = 1; j <= log2[n]; j++) {
            for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                f[i][j] = Math.min(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
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
        return Math.min(f[l][s], f[r - (1 << s) + 1][s]);
    }
}


class SparseTableMax {
    private int n;
    private int[][] f;
    private int[] log2;
    public SparseTableMax(int[] nums) {
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