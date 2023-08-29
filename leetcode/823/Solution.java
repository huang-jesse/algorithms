import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    public int numFactoredBinaryTrees(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        long[] dp = new long[n];
        // {arr[i], i}
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                int pre = arr[j];
                if ((long)pre * pre < cur) {
                    break;
                }
                if ((long)pre * pre == cur) {
                    // 子节点为两个 arr[j]
                    dp[i] = (dp[i] + dp[j] * dp[j] % MOD) % MOD;
                } else if (cur % pre == 0 && visited.containsKey(cur / pre)) {
                    // 子节点为一个 arr[j] 和 visited 中的一个 arr[k]
                    int k = visited.get(cur / pre);
                    // 左右子节点不同时，可以互换位置，所以 * 2
                    dp[i] = (dp[i] + (dp[j] * dp[k] % MOD) * 2 % MOD) % MOD;
                }
            }
            visited.put(cur, i);
        }
        int ans = 0;
        for (long current : dp) {
            ans = (int)((ans + current) % MOD);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] arr = {46,144,5040,4488,544,380,4410,34,11,5,3063808,5550,34496,12,540,28,18,13,2,1056,32710656,31,91872,23,26,240,18720,33,49,4,38,37,1457,3,799,557568,32,1400,47,10,20774,1296,9,21,92928,8704,29,2162,22,1883700,49588,1078,36,44,352,546,19,523370496,476,24,6000,42,30,8,16262400,61600,41,24150,1968,7056,7,35,16,87,20,2730,11616,10912,690,150,25,6,14,1689120,43,3128,27,197472,45,15,585,21645,39,40,2205,17,48,136};
        int[] arr = {2,4,5,10};
        System.out.println("test: " + sol.numFactoredBinaryTrees(arr));
    }
}