import java.util.Arrays;

class Solution {
    public boolean isGood(int[] nums) {
        int n = Arrays.stream(nums).max().getAsInt();
        int len = nums.length;
        if (len != n + 1) {
            return false;
        }
        int[] counter = new int[n + 1];
        for (int i = 1; i < n; i++) {
            counter[i] = 1;
        }
        counter[n] = 2;
        for (int num : nums) {
            counter[num]--;
        }

        // check
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {};
        System.out.println("test: " + sol.isGood(nums));
    }
}