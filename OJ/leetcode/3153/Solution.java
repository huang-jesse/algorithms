import java.util.Arrays;

class Solution {
    public long sumDigitDifferences(int[] nums) {
        int num = nums[0];
        int digits = 0;
        while (num > 0) {
            num /= 10;
            digits++;
        }
        long ans = 0;
        int n = nums.length;
        int[] counter = new int[10];
        for (int d = 0; d < digits; d++) {
            Arrays.fill(counter, 0);
            for (int i = 0; i < n; i++) {
                int digit = nums[i] % 10;
                nums[i] /= 10;
                counter[digit]++;
            }
            for (int count : counter) {
                ans += (long)count * (n - count);
            }
        }
        return ans / 2;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {13,23,12};
        System.out.println("test: " + sol.sumDigitDifferences(nums));
    }
}