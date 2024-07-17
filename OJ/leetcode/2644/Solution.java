class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int n = divisors.length;
        int maxDivisor = Integer.MAX_VALUE;
        int maxDivScore = 0;
        for (int i = 0; i < n; i++) {
            int divisor = divisors[i];
            int divScore = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    divScore++;
                }
            }
            if (maxDivScore < divScore) {
                maxDivScore = divScore;
                maxDivisor = divisor;
            } else if (maxDivScore == divScore) {
                maxDivisor = Math.min(maxDivisor, divisor);
            }
        }
        return maxDivisor;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {20,14,21,10};
        int[] divisors = {5,7,5};
        System.out.println("test: " + sol.maxDivScore(nums, divisors));
    }
}