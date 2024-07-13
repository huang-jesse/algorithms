import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            Set<Integer> bits = new HashSet<>();
            for (int j = i; j >= 0; j--) {
                bits.add(Integer.bitCount(nums[j]));
                if (nums[i] < nums[j] && bits.size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {8,4,2,30,15}; // true
        // int[] nums = {3,16,8,4,2}; // false
        System.out.println("test: " + sol.canSortArray(nums));
    }
}