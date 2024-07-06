class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        long ans = 1;
        int contiguous = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] != nums[i]) {
                contiguous++;
            } else {
                contiguous = 1;
            }
            ans += contiguous;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {0,1,1,1};
        System.out.println("test: " + sol.countAlternatingSubarrays(nums));
    }
}