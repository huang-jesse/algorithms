import java.util.HashMap;
import java.util.Map;

class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> productCounter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = nums[i] * nums[j];
                productCounter.put(product, productCounter.getOrDefault(product, 0) + 1);
            }
        }
        int ans = 0;
        for (Integer count : productCounter.values()) {
            ans += (count * (count - 1)) / 2;
        }
        return ans * 8;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,4,5,10};
        System.out.println("test: " + sol.tupleSameProduct(nums));
    }
}