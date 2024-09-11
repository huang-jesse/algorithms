class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] firstSegments = new int[n];
        for (int i = 0; i < n; i++) {
            int target = Math.max(prizePositions[i] - k, 0);
            int l = 0;
            int r = i;
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                if (prizePositions[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            firstSegments[i] = i - l + 1;
        }
        for (int i = 1; i < n; i++) {
            firstSegments[i] = Math.max(firstSegments[i], firstSegments[i - 1]);
        }
        int ans = firstSegments[0];
        for (int i = 1; i < n; i++) {
            int firstSeg = firstSegments[i - 1];
            int target = prizePositions[i] + k;
            int l = i;
            int r = n - 1;
            while (l < r) {
                int mid = l + ((r - l + 1) >> 1);
                if (prizePositions[mid] <= target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int secondSeg = r - i + 1;
            ans = Math.max(ans, firstSeg + secondSeg);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] prizePositions = {1,1,2,2,3,3,5};
        int k = 2; // res: 7
        // int[] prizePositions = {1};
        // int k = 0; // res: 1
        System.out.println("test: " + sol.maximizeWin(prizePositions, k));
    }
}