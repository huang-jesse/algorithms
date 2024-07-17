import java.util.Arrays;

class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] diffs = new int[max + 2];
        for (int num : nums) {
            diffs[Math.max(0, num - k)]++;
            diffs[Math.min(max, num + k) + 1]--;
        }
        for (int i = 1; i <= max; i++) {
            diffs[i] += diffs[i - 1];
        }
        int resIndex = 0;
        for (int i = 1; i <= max; i++) {
            if (diffs[i] > diffs[resIndex]) {
                resIndex = i;
            }
        }
        return diffs[resIndex];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,6,1,2};
        int k = 2;
        System.out.println("test: " + sol.maximumBeauty(nums, k));
    }
}