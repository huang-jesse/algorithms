import java.util.Arrays;

class Solution {
    private final static int INF = 100000;
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = INF;
        for (int i = 0; i < n - 2; i++) {
            int firstNum = nums[i];
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int secondNum = nums[l];
                int thirdNum = nums[r];
                int currentSum = firstNum + secondNum + thirdNum;
                if (currentSum == target) {
                    return currentSum;
                }
                if (Math.abs(currentSum - target) < Math.abs(ans - target)) {
                    ans = currentSum;
                }
                if (currentSum < target) {
                    l++;
                } else {
                    // currentSum > currentTarget
                    r--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {-1,2,1,-4};
        int target = 1;
        System.out.println("test: " + sol.threeSumClosest(nums, target));
    }
}