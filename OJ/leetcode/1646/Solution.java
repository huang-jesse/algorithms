class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int[] nums = new int[n+1];
        nums[1] = 1;
        int ans = 1;
        for (int i = 1; i < n+1; i++) {
            int doubleIndex = 2*i;
            if (doubleIndex >= 2 && doubleIndex <= n) {
                nums[doubleIndex] = nums[i];
                ans = Math.max(ans, nums[doubleIndex]);
            }
            if (doubleIndex + 1 >= 2 && doubleIndex + 1 <= n) {
                nums[doubleIndex+1] = nums[i] + nums[i+1];
                ans = Math.max(ans, nums[doubleIndex+1]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 7;
        System.out.println("test: " + sol.getMaximumGenerated(n));
    }
}