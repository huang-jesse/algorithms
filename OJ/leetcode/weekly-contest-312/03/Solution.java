import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        boolean[] beforeNonIncreasing = new boolean[n];
        boolean[] afterNonDecreasing = new boolean[n];
        for (int i = k, j = 0; i < n - k; i++) {
            if (j < i - k) {
                j = i - k;
            }
            while (j < i - 1 && nums[j] >= nums[j + 1]) {
                j++;
            }
            if (j == i - 1) {
                // is good
                beforeNonIncreasing[i] = true;
            }
        }

        for (int i = k, j = k + 1; i < n - k; i++) {
            if (j < i + 1) {
                j = i + 1;
            }
            while (j + 1 < n && j - i < k && nums[j] <= nums[j + 1]) {
                j++;
            }
            if (j - i == k) {
                // is good
                afterNonDecreasing[i] = true;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = k; i < n - k; i++) {
            if (beforeNonIncreasing[i] && afterNonDecreasing[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,1,1,1,3,4,1};
        int k = 2;
        // int[] nums = {478184,863008,716977,921182,182844,350527,541165,881224};
        // int k = 1;
        System.out.println("test: " + sol.goodIndices(nums, k));
    }
}