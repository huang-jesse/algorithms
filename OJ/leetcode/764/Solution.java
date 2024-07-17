import java.util.HashSet;
import java.util.Set;

class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> banned = new HashSet<>();
        for (int[] mine : mines) {
            int x = mine[0];
            int y = mine[1];
            banned.add(x * n + y);
        }
        int[][] axisXContinuous = new int[n][n];
        for (int r = 0; r < n; r++) {
            int leftCount = 0;
            int rightCount = 0;
            for (int c = 0; c < n; c++) {
                // left
                int lc = c;
                if (banned.contains(r * n + lc)) {
                    leftCount = 0;
                } else {
                    leftCount++;
                }
                axisXContinuous[r][lc] = axisXContinuous[r][lc] == 0 ? leftCount : Math.min(axisXContinuous[r][lc], leftCount);
                // right
                int rc = n - 1 - c;
                if (banned.contains(r * n + rc)) {
                    rightCount = 0;
                } else {
                    rightCount++;
                }
                axisXContinuous[r][rc] = axisXContinuous[r][rc] == 0 ? rightCount : Math.min(axisXContinuous[r][rc], rightCount);
            }
        }
        int[][] axisYContinuous = new int[n][n];
        for (int c = 0; c < n; c++) {
            int topCount = 0;
            int bottomCount = 0;
            for (int r = 0; r < n; r++) {
                // top
                int topr = r;
                if (banned.contains(topr * n + c)) {
                    topCount = 0;
                } else {
                    topCount++;
                }
                axisYContinuous[topr][c] = axisYContinuous[topr][c] == 0 ? topCount : Math.min(axisYContinuous[topr][c], topCount);
                // bottom
                int bottomr = n - 1 - r;
                if (banned.contains(bottomr * n + c)) {
                    bottomCount = 0;
                } else {
                    bottomCount++;
                }
                axisYContinuous[bottomr][c] = axisYContinuous[bottomr][c] == 0 ? bottomCount : Math.min(axisYContinuous[bottomr][c], bottomCount);
            }
        }
        int ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int res = Math.min(axisXContinuous[r][c], axisYContinuous[r][c]);
                ans = Math.max(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] mines = {{4,2}};
        System.out.println("test: " + sol.orderOfLargestPlusSign(n, mines));
    }
}