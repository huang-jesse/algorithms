class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long ans = 0L;
        int preNum = 0;
        int subseqIndex = 0;
        int i = 0;
        while (i < n) {
            if (subseqIndex % 2 == 0) {
                // Even index
                while (i < n && preNum <= nums[i]) {
                    preNum = nums[i];
                    i++;
                }
                ans += preNum;
                subseqIndex++;
            } else {
                // Odd index
                while (i < n && preNum >= nums[i]) {
                    preNum = nums[i];
                    i++;
                }
                ans -= preNum;
                subseqIndex++;
            }
        }
        if (subseqIndex % 2 == 0) {
            // The last subseqIndex is odd, we can do not select this
            ans += preNum;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,2,3,7,2,1,4};// ans = 12
        System.out.println("test: " + sol.maxAlternatingSum(nums));
    }
}