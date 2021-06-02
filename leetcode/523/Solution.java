import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < n; i++) {
            // pre[p] - pre[q] = k  =>  pre[p] % k = pre[q] % k
            // pre[i] % k = (a0 + a1 + ... + ai) % k = (a0 % k + a1 % k + ... ai % k ) % k 
            remainder = (remainder + nums[i]) % k;
            if (remainderMap.containsKey(remainder)) {
                int preIndex = remainderMap.get(remainder);
                if (i - preIndex >= 2) {
                    return true;
                }
            } else {
                remainderMap.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {23,2,4,6,7};
        int k = 6;
        System.out.println("test: " + sol.checkSubarraySum(nums, k));
    }
}