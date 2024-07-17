import java.util.Arrays;

class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        int firstNum = nums[0];
        int digitLen = 0;
        while (firstNum > 0) {
            digitLen++;
            firstNum /= 10;
        }
        int[][] digitsCounter = new int[digitLen][10];
        for (int num : nums) {
            for (int i = 0; i < digitLen; i++) {
                int digitNum = num % 10;
                digitsCounter[i][digitNum]++;
                num /= 10;
            }
        }
        long ans = 0L;
        for (int num : nums) {
            for (int i = 0; i < digitLen; i++) {
                int digitNum = num % 10;
                digitsCounter[i][digitNum]--;
                num /= 10;
                int diffrence = Arrays.stream(digitsCounter[i]).sum() - digitsCounter[i][digitNum];
                ans += diffrence;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {13,23,12,35};
        // int[] nums = {13,23,12};
        System.out.println("test: " + sol.sumDigitDifferences(nums));
    }
}