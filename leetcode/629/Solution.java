import java.util.Arrays;

/**
 * 参考题解：https://leetcode-cn.com/problems/k-inverse-pairs-array/solution/gong-shui-san-xie-yi-dao-xu-lie-dp-zhuan-tm01/
 * 定义 f[i][j] 为考虑使用数值 [1,i]，凑成逆序对数量恰好为 j 的数组个数。
 * 不失一般性的考虑 f[i][j] 该如何计算，对第 i 个数（即数值为 i 的数）所在位置进行讨论，共有 i 种选择。
 * 假设第 i 个数所在位置为 k，由于数值 i 为整个数组的最大值，因此数值 i 与前面所有数均不形成逆序对，与后面的所有数均形成逆序对。
 * 因此与数值 i 直接相关的逆向对的数量为 (i - 1)- k(i−1)−k，由此也得出与 i 不相关的逆序对数量为 j - (i - 1 - k)，而与 i 不相关的逆序对数量由 f[i - 1][x] 可得出。
 */
class Solution {

    private static final int MOD = (int)1e9 + 7;
    public int kInversePairs(int n, int k) {
        int[][] f = new int[n+1][k+1];
        int[][] sum = new int[n+1][k+1];
        f[1][0] = 1;
        Arrays.fill(sum[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                f[i][j] = j < i ? sum[i-1][j] : (sum[i-1][j] - sum[i-1][j-(i-1)-1] + MOD) % MOD;
                sum[i][j] = j == 0 ? f[i][j] : (sum[i][j-1] + f[i][j]) % MOD;
            }
        }
        return f[n][k];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int k = 1;
        System.out.println("test: " + sol.kInversePairs(n, k));
    }
}