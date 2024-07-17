import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 24;
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(hours[0] % MOD, 1);
        long res = 0;
        for (int i = 1; i < n; i++) {
            int hour = hours[i] % MOD;
            int remain = (MOD - hour) % MOD;
            res += counter.getOrDefault(remain, 0);

            counter.put(hour, counter.getOrDefault(hour, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] hours = {12,12,30,24,24};
        System.out.println("test: " + sol.countCompleteDayPairs(hours));
    }
}