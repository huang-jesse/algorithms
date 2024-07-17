import java.util.Arrays;

class Solution {
    public int minCapability(int[] nums, int k) {
        int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt();
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int maxNum, int k) {
        int n = nums.length;
        int i = 0;
        int count = 0;
        while (i < n) {
            if (nums[i] <= maxNum) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,7,9,3,1};
        int k = 2;
        System.out.println("test: " + sol.minCapability(nums, k));
    }
}