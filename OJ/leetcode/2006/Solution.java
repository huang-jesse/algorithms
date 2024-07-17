class Solution {
    public int countKDifference(int[] nums, int k) {
        int[] counter = new int[101];
        int ans = 0;
        for (int num : nums) {
            if (num + k <= 100) {
                ans = ans + counter[num+k];
            }
            if (num - k >= 0) {
                ans = ans + counter[num-k];
            }
            counter[num]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,2,1,5,4};
        int k = 2;
        System.out.println("test: " + sol.countKDifference(nums, k));
    }
}