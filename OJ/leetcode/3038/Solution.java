class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int score = nums[0] + nums[1];
        int count = 1;
        for (int i = 3; i < n && nums[i] + nums[i - 1] == score; i += 2) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,2,1,4,5};
        System.out.println("test: " + sol.maxOperations(nums));
    }
}