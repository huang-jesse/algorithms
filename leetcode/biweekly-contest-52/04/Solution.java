import java.util.HashMap;
import java.util.Map;

class Solution {
    static final int MOD = (int)1e9 + 7;
    public int sumOfFlooredPairs(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (map.containsKey(cur)) {
                ans = (ans + map.get(cur)) % MOD;
            } else {
                int temp = 0;
                int j = 0;
                while (j < nums.length) {
                    int curNum = nums[j];
                    int val = cur / curNum;
                    temp = (temp + val) % MOD;
                    j++;
                }
                map.put(cur, temp);
                ans = (ans + temp) % MOD;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{2,5,9};
        System.out.println("test: " + sol.sumOfFlooredPairs(nums));
    }
}