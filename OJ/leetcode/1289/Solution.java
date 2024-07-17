class Solution {
    private static final int INF = 100 * 200;
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        // {min, index}
        int[] firstMin = {0, -1};
        int[] secondMin = {0, -1};
        for (int i = 0; i < n; i++) {
            int[] tempFirstMin = {INF, -1};
            int[] tempSecondMin = {INF, -1};
            for (int j = 0; j < n; j++) {
                int current = grid[i][j];
                if (firstMin[1] != j) {
                    current += firstMin[0];
                } else {
                    // secondMin
                    current += secondMin[0];
                }
                if (current < tempFirstMin[0]) {
                    // 最小的移交给第二小
                    tempSecondMin[0] = tempFirstMin[0];
                    tempSecondMin[1] = tempFirstMin[1];
                    // 更新最小值
                    tempFirstMin[0] = current;
                    tempFirstMin[1] = j;
                } else if (current < tempSecondMin[0]) {
                    tempSecondMin[0] = current;
                    tempSecondMin[1] = j;
                }
            }
            firstMin = tempFirstMin;
            secondMin = tempSecondMin;
        }
        return firstMin[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("test: " + sol.minFallingPathSum(grid));
    }
}