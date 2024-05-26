import java.util.HashMap;
import java.util.Map;

class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n = nums2.length;
        Map<Integer, Integer> nums2Map = new HashMap<>();
        for (int j = 0; j < n; j++) nums2Map.put(nums2[j], nums2Map.getOrDefault(nums2[j], 0) + 1);
        long ans = 0;
        for (int num : nums1) {
            if (num % k != 0) continue;
            num /= k;
            for (int i = 1; i * i <= num; i++) {
                if (num % i != 0) continue;
                int t = num / i;
                if (nums2Map.containsKey(i)) ans += nums2Map.get(i);
                if (i != t && nums2Map.containsKey(t)) ans += nums2Map.get(t);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1,2,4,12};
        int[] nums2 = {2,4};
        int k = 3;
        System.out.println("test: " + sol.numberOfPairs(nums1, nums2, k));
    }
}