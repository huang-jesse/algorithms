import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int distinctCount = Arrays.stream(nums).boxed().collect(Collectors.toSet()).size();
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() >= distinctCount) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,1,2,2};
        System.out.println("test: " + sol.countCompleteSubarrays(nums));
    }
}