import java.util.Arrays;

class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] differenceArr = new int[max + 1];
        for (int num : nums) {
            int left = Math.max(0, num - k);
            differenceArr[left]++;
            if (num + k + 1 <= max) {
                differenceArr[num + k + 1]--;
            }
        }
        int preSum = 0;
        int ans = 0;
        for (int i = 0; i < max + 1; i++) {
            preSum += differenceArr[i];
            ans = Math.max(ans, preSum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,6,1,2};
        int k = 2;
        System.out.println("test: " + sol.maximumBeauty(nums, k));
    }
}