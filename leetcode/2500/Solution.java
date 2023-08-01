import java.util.Arrays;

class Solution {
    public int deleteGreatestValue(int[][] grid) {
        int m = grid[0].length;
        for (int[] row : grid) {
            Arrays.sort(row);
        }
        int ans = 0;
        for (int colIdx = m - 1; colIdx >= 0; colIdx--) {
            int greatestValue = 0;
            for (int[] row : grid) {
                greatestValue = Math.max(greatestValue, row[colIdx]);
            }
            ans += greatestValue;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,2,4},{3,3,1}};
        System.out.println("test: " + sol.deleteGreatestValue(grid));
    }
}