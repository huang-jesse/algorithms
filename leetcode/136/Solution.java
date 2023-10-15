class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,1,2,1,2};
        // int[] nums = {2,2,1};
        System.out.println("test: " + sol.singleNumber(nums));
    }
}