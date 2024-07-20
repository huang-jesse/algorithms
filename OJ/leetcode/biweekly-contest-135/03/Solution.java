import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int INF = 0x3fffffff;
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int half = n / 2;
        Map<Integer, Integer> diffCounter = new HashMap<>();
        for (int i = 0; i < half; i++) {
            int diff = Math.abs(nums[i] - nums[n - i - 1]);
            diffCounter.put(diff, diffCounter.getOrDefault(diff, 0) + 1);
        }
        int[] maxDiffs = new int[half];
        for (int i = 0; i < half; i++) {
            maxDiffs[i] = Math.max(Math.max(nums[i], Math.abs(k - nums[i])), Math.max(nums[n - 1 - i], Math.abs(k - nums[n - 1 - i])));
        }
        Arrays.sort(maxDiffs);
        int all = half;
        int ans = INF;
        for (int x = 0; x <= k; x++) {
            // 左边界
            int index = binarySearch(maxDiffs, x);
            int matchCount = half - index;
            if (index == -1) {
                matchCount = 0;
            }
            int diffCount = diffCounter.getOrDefault(x, 0);
            int res = matchCount - diffCount + (all - matchCount) * 2;
            ans = Math.min(ans, res);
        }
        return ans;
    }

    private int binarySearch(int[] maxDiffs, int target) {
        int l = 0;
        int r = maxDiffs.length - 1;
        if (maxDiffs[r] < target) return -1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (maxDiffs[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,0,1,2,4,3};
        // int k = 4; // 2
        // int[] nums = {0,1,2,3,3,6,5,4};
        // int k = 6; // 2
        int[] nums = {0,1,2,3,3,3,3,6,5,4};
        int k = 6; // 3
        System.out.println("test: " + sol.minChanges(nums, k));
    }
}