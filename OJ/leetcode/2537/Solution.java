import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> counter = new HashMap<>();
        int l = 0;
        int r = 0;
        long pairCount = 0;
        long ans = 0;
        // 双指针滑动窗口
        while (l < n - 1) {
            // 移动右指针
            while (r < n && pairCount < k) {
                int numPairCount = counter.getOrDefault(nums[r], 0);
                pairCount += numPairCount;
                counter.put(nums[r], numPairCount + 1);
                r++;
            }
            if (pairCount >= k) {
                ans += n - r + 1;
            }
            // 移除左指针
            int numPairCount = counter.get(nums[l]) - 1;
            counter.put(nums[l], numPairCount);
            pairCount -= numPairCount;
            l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,1,4,3,2,2,4};
        int k = 2;
        System.out.println("test: " + sol.countGood(nums, k));
    }
}