class Solution {
    static final int DIGITS = 30;
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int digit = 0; digit < DIGITS; digit++) {
            int countOfBitOne = 0;
            for (int i = 0; i < n; i++) {
                int bit = getBit(nums[i], digit);
                if (bit == 1) {
                    countOfBitOne++;
                }
            }
            ans += countOfBitOne * (n - countOfBitOne);
        }

        return ans;
    }

    private int getBit(int num, int digit) {
        return (num >> digit) & 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4, 14, 2};
        System.out.println("test: " + sol.totalHammingDistance(nums));
        System.out.println(1e9 < Math.pow(2, 30));
    }
}