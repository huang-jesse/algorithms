import java.util.Arrays;
import java.util.List;
/**
 * reference: https://leetcode.cn/problems/maximize-value-of-function-in-a-ball-passing-game/solutions/2413322/bei-zeng-suan-fa-ye-shi-yi-ge-tao-lu-ti-6ilak/
 */
class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size();
        // k 的二进制长度
        int m = 64 - Long.numberOfLeadingZeros(k);
        int[][] f = new int[n][m];
        long[][] w = new long[n][m];
        for (int i = 0; i < n; i++) {
            f[i][0] = receiver.get(i);
            w[i][0] = i;
        }

        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                f[i][j] = f[f[i][j - 1]][j - 1];
                // 累加所有路径上的权值（除了终点外）
                w[i][j] = w[i][j - 1] + w[f[i][j - 1]][j - 1];
            }
        }

        long ans = 0;
        // 累加权值
        for (int i = 0; i < n; i++) {
            long cur = 0;
            int position = i;
            for (int j = 0; j < m; j++) {
                // 将 k 拆为二进制为，逐步累加每个二进制位的权值
                if (((k >> j) & 1) == 1) {
                    // 累加当前二进制位的权值
                    cur += w[position][j];
                    // 将球传递到下一位置
                    position = f[position][j];
                }
            }
            // 加上终点的权值
            ans = Math.max(ans, cur + position);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> receiver = Arrays.asList(1,1,1,2,3);
        long k = 3;
        System.out.println("test: " + sol.getMaxFunctionValue(receiver, k));
    }
}