class Solution {
    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        long maxDiff = 0;
        long preMax = 0;
        // enumerate k
        for (int num : nums) {
            ans = Math.max(ans, maxDiff * num);
            maxDiff = Math.max(maxDiff, preMax - num);
            preMax = Math.max(preMax, num);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {12,6,1,2,7};
        System.out.println("test: " + sol.maximumTripletValue(nums));
    }
}