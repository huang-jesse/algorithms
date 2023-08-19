import java.util.Arrays;
import java.util.List;

class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(1);
        int target = 0;
        System.out.println("test: " + sol.countPairs(nums, target));
    }
}