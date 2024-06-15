import java.util.Arrays;

class SolutionSort {
    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // find max subarray nums[l...r] such that nums[r] - nums[l] <= 2*k
        int res = 1;
        int l = 0;
        int r = l + 1;
        while (r < n) {
            if (nums[r] - nums[l] <= 2*k) {
                res = Math.max(res, r - l + 1);
                r++;
            } else {
                // nums[r] - nums[l] > 2*k
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionSort sol = new SolutionSort();
        int[] nums = {4,6,1,2};
        int k = 2;
        System.out.println("test: " + sol.maximumBeauty(nums, k));
    }
}