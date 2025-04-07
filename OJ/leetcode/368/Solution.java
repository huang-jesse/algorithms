import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] counter = new int[n];
        counter[0] = 1;
        int maxIndex = 0;
        int[] pre = new int[n];
        Arrays.fill(pre, -1);
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            counter[i] = 1;
            for (int j = 0; j < i; j++) {
                if (cur % nums[j] == 0 && counter[j] + 1 > counter[i]) {
                    counter[i] = counter[j] + 1;
                    pre[i] = j;
                }
            }
            if (counter[i] > counter[maxIndex]) {
                maxIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>(counter[maxIndex]);
        for (int i = maxIndex; i >= 0; i = pre[i]) {
            ans.add(nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,4,8};
        System.out.println("test: " + sol.largestDivisibleSubset(nums));
    }
}