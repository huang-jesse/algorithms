import java.util.Arrays;

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;
        int sumOfSlideWindow = k;
        for (int i = n-1; i >= 0; i--) {
            // process slide window
            for (int j = i-count; j >= 0; j--) {
                sumOfSlideWindow += nums[j];
                int sumOfTotalNeeds = nums[i] * (count+1);
                // System.out.println("sumOfSlideWindow " + sumOfSlideWindow);
                // System.out.println("sumOfTotalNeeds " + sumOfTotalNeeds);
                if (sumOfTotalNeeds > sumOfSlideWindow) {
                    // move slide window to left
                    sumOfSlideWindow -= nums[i];
                    break;
                }
                count++;
            }

            // arrived the longest slide window
            if (i - count + 1 <= 0) break;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{100000};
        int k = 100000;
        System.out.println("maxFrequency: " + sol.maxFrequency(nums, k));
    }
}