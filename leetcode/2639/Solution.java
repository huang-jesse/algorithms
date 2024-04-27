import java.util.Arrays;

class Solution {
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 由于数字的绝对值越大，数字的长度就越长，所以只需要对每一列的最小值或最大值求长度。
            int mn = 0;
            int mx = 0;
            for (int j = 0; j < m; j++) {
                mn = Math.min(mn, grid[j][i]);
                mx = Math.max(mx, grid[j][i]);
            }
            // 最终长度需要额外加1（mx / 10 与负数的绝对值进行对比，因为负数默认 '-' 为1位）
            int num = Math.max(mx / 10, -mn);
            ans[i] = this.length(num) + 1;
        }
        return ans;
    }

    private int length(int num) {
        int digitCount = 0;
        while(num > 0) {
            num = num / 10;
            digitCount++;
        }
        return digitCount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{-15,1,3},{15,7,12},{5,6,-2}};
        int[] ans = sol.findColumnWidth(grid);
        System.out.println("test: " + Arrays.toString(ans));
    }
}