class Solution {
    public int maxSum(int[] nums) {
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num1MaxDigit = maxDigit(num1);
            for (int j = i + 1; j < n; j++) {
                int num2 = nums[j];
                int num2MaxDigit = maxDigit(num2);
                if (num1MaxDigit == num2MaxDigit) {
                    ans = Math.max(ans, num1 + num2);
                }
            }
        }
        return ans;
    }

    private int maxDigit(int num) {
        int digit = 0;
        while (num > 0) {
            digit = Math.max(num % 10, digit);
            num /= 10;
        }
        return digit;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {51,71,17,24,42};
        System.out.println("test: " + sol.maxSum(nums));
    }
}