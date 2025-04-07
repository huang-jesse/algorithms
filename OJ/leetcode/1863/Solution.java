class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int mask = (1 << n) - 1;
        int xorSum = 0;
        for (int i = 1; i <= mask; i++) {
            int subXorSum = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    subXorSum ^= nums[j];
                }
            }
            xorSum += subXorSum;
        }
        return xorSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,4,5,6,7,8};
        System.out.println("test: " + sol.subsetXORSum(nums));
    }
}