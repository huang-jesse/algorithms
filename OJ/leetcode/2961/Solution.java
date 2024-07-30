import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        int n = variables.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] variable = variables[i];
            int a = variable[0];
            int b = variable[1];
            int c = variable[2];
            int m = variable[3];
            int num = qpow(qpow(a, b, 10), c, m);
            if (num == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int qpow(int a, int n, int mod) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 当前二进制最后一位为1
                ans = (ans * a) % mod;
            }
            // a累乘
            a = (a * a) % mod;
            // n右移一位
            n = n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] variables = {{2,3,3,10},{3,3,3,1},{6,1,1,4}};
        int target = 2;
        System.out.println("test: " + sol.getGoodIndices(variables, target));
    }
}