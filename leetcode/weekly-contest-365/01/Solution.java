class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;

        int[] maxThirds = new int[n];
        maxThirds[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxThirds[i] = Math.max(maxThirds[i + 1], nums[i]);
        }
        // max
        long ans = 0;
        int maxFirst = Integer.MIN_VALUE;
        for (int j = 1; j < n - 1; j++) {
            maxFirst = Math.max(maxFirst, nums[j - 1]);
            int second = nums[j];
            ans = Math.max(ans, (long)(maxFirst - second) * maxThirds[j + 1]);
        }
        return Math.max(0, ans);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {12,6,1,2,7};
        System.out.println("test: " + sol.maximumTripletValue(nums));
    }
}