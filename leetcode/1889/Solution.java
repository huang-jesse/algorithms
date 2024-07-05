import java.util.Arrays;

class Solution {
    private static final int MOD = (int)1e9 + 7;
    private static final long INF = Long.MAX_VALUE;
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages);
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + packages[i];
        }

        long ans = INF;
        for (int[] box : boxes) {
            Arrays.sort(box);
            long res = 0;
            int m = box.length;
            // can not packaged all packages
            if (box[m - 1] < packages[n - 1]) continue;
            int leftLimit = 0;
            for (int i = 0; i < m; i++) {
                int index = binarySearch(packages, leftLimit, box[i]);
                if (index == -1) continue;
                long numOfBoxes = index - leftLimit + 1;
                long totalBoxesSpace = numOfBoxes * box[i];
                long totalPackagesSize = prefixSum[index + 1] - prefixSum[leftLimit];
                long totalWasted = totalBoxesSpace - totalPackagesSize;
                res = res + totalWasted;

                leftLimit = index + 1;
                // all packaged
                if (leftLimit == n) break;
            }
            if (leftLimit == n) {
                // this box can packaged all packages
                ans = Math.min(ans, res);
            }
        }
        if (ans == INF) return -1;
        return (int)(ans % MOD);
    }

    private int binarySearch(int[] packages, int leftLimit, int target) {
        int n = packages.length;
        if (target < packages[leftLimit]) return -1;
        // if (packages[n - 1] <= target) return n - 1;
        int l = leftLimit;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (packages[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] packages = {3,5,8,10,11,12};
        int[][] boxes = {{12},{11,9},{10,5,14}};
        System.out.println("test: " + sol.minWastedSpace(packages, boxes));
    }
}