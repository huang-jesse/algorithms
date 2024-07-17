import java.util.Arrays;
import java.util.List;
class SolutionNew {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        int n = receiver.size();
        int[][] f = new int[n][36];
        long[][] weights = new long[n][36];
        for (int i = 0; i < n; i++) {
            f[i][0] = receiver.get(i);
            weights[i][0] = i;
        }
        for (int j = 1; j < 36; j++) {
            for (int i = 0; i < n; i++) {
                f[i][j] = f[f[i][j - 1]][j - 1];
                // 累加除了终点外的权值
                weights[i][j] = weights[i][j - 1] + weights[f[i][j - 1]][j - 1];
            }
        }
        long ans = 0;
        // 遍历每一项
        for (int i = 0; i < n; i++) {
            int sender = i;
            long res = 0;
            for (int j = 0; j < 36; j++) {
                // 将 k 拆为二进制为，逐步累加每个二进制位的权值
                if (((k >> j) & 1) == 1) {
                    // k 的第 j 位二进制为 1
                    int currentReceiver = f[sender][j];
                    res += weights[sender][j];
                    // 将球传递到下一位置
                    sender = currentReceiver;
                }
            }
            // 加上终点
            res += sender;
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        List<Integer> receiver = Arrays.asList(1,1,1,2,3);
        long k = 3;
        System.out.println("test: " + sol.getMaxFunctionValue(receiver, k));
    }
}