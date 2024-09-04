import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int countWays(List<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        int ans = 0;
        if (nums.get(0) > 0) ans++;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < i + 1 && nums.get(i + 1) > i + 1) ans++;
        }
        // select all
        ans++;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> nums = Arrays.asList(6,0,3,3,6,7,2,7);
        System.out.println("test: " + sol.countWays(nums));
    }
}