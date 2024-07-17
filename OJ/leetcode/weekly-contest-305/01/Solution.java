class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ans++;
                        continue;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {0,1,4,6,7,10};
        int diff = 3;
        System.out.println("test: " + sol.arithmeticTriplets(nums, diff));
    }
}