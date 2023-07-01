import java.util.Arrays;

class Solution {
    public int maximumSum(int[] arr) {
        int ans = Integer.MIN_VALUE, n = arr.length;
        var f = new int[n + 1][2];
        Arrays.fill(f[0], Integer.MIN_VALUE / 2); // 除 2 防止负数相加溢出
        for (int i = 0; i < n; i++) {
            f[i + 1][0] = Math.max(f[i][0], 0) + arr[i];
            f[i + 1][1] = Math.max(f[i][1] + arr[i], f[i][0]);
            ans = Math.max(ans, Math.max(f[i + 1][0], f[i + 1][1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1,-2,0,3};
        // int[] arr = {-1,-1,-1,-1};
        System.out.println("test: " + sol.maximumSum(arr));
    }
}