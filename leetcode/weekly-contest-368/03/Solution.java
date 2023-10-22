import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> perGroupCountMap = new HashMap<>();
        for (int num : nums) {
            perGroupCountMap.put(num, perGroupCountMap.getOrDefault(num, 0) + 1);
        }
        if (perGroupCountMap.size() == 1) {
            return 1;
        }
        int[] ok = new int[n + 1];
        for (int count : perGroupCountMap.values()) {
            for (int i = 1; i <= count; i++) {
                if ((count % i) <= (count / i)) {
                    ok[i]++;
                }
            }
        }
        for (int i = n; i >= 1; i--) {
            if (ok[i] == perGroupCountMap.size()) {
                int ans = 0;
                for (int count : perGroupCountMap.values()) {
                    ans += (count + i) / (i + 1);
                }
                return ans;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {10,10,10,3,1,1};
        // int[] nums = {1,1,1,1,1};
        int[] nums = {2,3,3,3,2,3,2,3,2};
        System.out.println("test: " + sol.minGroupsForValidAssignment(nums));
    }
}