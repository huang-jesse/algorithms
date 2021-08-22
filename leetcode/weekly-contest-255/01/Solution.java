import java.util.Arrays;

class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0];
        int max = nums[n - 1];
        return getGreatestCommonDivisor(min, max);
    }

    public int getGreatestCommonDivisor(int numberA, int numberB) {
        int result = 1;
        if(numberA > numberB)
            result = gcd(numberA,numberB);
        else
            result = gcd(numberB,numberA);
        return result;
    }

    private int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        else
            return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {7,5,6,8,3};
        System.out.println("test: " + sol.findGCD(nums));
    }
}