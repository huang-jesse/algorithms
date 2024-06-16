import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> powerMap = new HashMap<>();
        for (int p : power) {
            powerMap.put(p, powerMap.getOrDefault(p, 0) + 1);
        }
        power = powerMap.keySet().stream().mapToInt(o -> o).toArray();
        int n = power.length;
        Arrays.sort(power);
        long[] damages = new long[n];
        for (int i = 0; i < n; i++) {
            damages[i] = (long)power[i] * powerMap.get(power[i]);
        }
        long[] dp = new long[n];
        dp[0] = damages[0];
        for (int i = 1; i < n; i++) {
            // can we select power[i] ?

            // select
            // dp[i - 1], dp[i - 2], dp[i - 3]
            for (int j = i - 1; j >= Math.max(0, i - 3); j--) {
                if (power[i] - power[j] > 2) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += damages[i];

            // if don't select power[i] then chose dp[i - 1]
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] power = {7,1,6,6};
        int[] power = {7,1,6,3}; // 10
        // int[] power = {2,1,4,3,1,1,1,5}; // 9
        System.out.println("test: " + sol.maximumTotalDamage(power));
    }
}