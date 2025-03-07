import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        Set<Integer> seen = new HashSet<>();
        outer:for (int mask = 1; mask < (1 << n); mask++) {
            seen.clear();
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    if (seen.contains(nums[i] - k)) {
                        continue outer;
                    }
                    seen.add(nums[i]);
                }
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,4,6};
        int k = 2; //ans: 4
        System.out.println("test: " + sol.beautifulSubsets(nums, k));
    }
}