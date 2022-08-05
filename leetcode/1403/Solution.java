import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sumOfSeq = Arrays.stream(nums).sum();
        int sumOfHalf = sumOfSeq / 2;
        int acc = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length-1; i >= 0; i--) {
            acc += nums[i];
            ans.add(nums[i]);
            if (acc > sumOfHalf) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,4,7,6,7};
        System.out.println("test: " + sol.minSubsequence(nums));
    }
}