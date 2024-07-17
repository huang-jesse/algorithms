import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private int[] landStartIndexes;
    private int[] islandCounter;
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        this.landStartIndexes = new int[n * n];
        this.islandCounter = new int[n * n];
        Arrays.fill(landStartIndexes, -1);
        // land counter
        for (int i = 0; i < n * n; i++) {
            if (landStartIndexes[i] != -1) {
                // this land is already counted by the land of start index
                islandCounter[i] = islandCounter[landStartIndexes[i]];
            } else {
                islandCounter[i] = backtrack(grid, i, i);
            }
        }
        int ans = 1;
        // find the largest land
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int islandCount = islandCounter[encodeIndex(n, row, col)];
                if (grid[row][col] == 0) {
                    islandCount = newIslandCount(n, row, col);
                }
                ans = Math.max(ans, islandCount);
            }
        }
        return ans;
    }

    private int newIslandCount(int n, int row, int col) {
        int islandCount = 1;
        Set<Integer> countedIndexes = new HashSet<>();
        // connect top
        if (row > 0) {
            islandCount += newConnectCount(n, encodeIndex(n, row - 1, col), countedIndexes);
        }
        // connect bottom
        if (row < n - 1) {
            islandCount += newConnectCount(n, encodeIndex(n, row + 1, col), countedIndexes);
        }
        // connect left
        if (col > 0) {
            islandCount += newConnectCount(n, encodeIndex(n, row, col - 1), countedIndexes);
        }
        // connect right
        if (col < n - 1) {
            islandCount += newConnectCount(n, encodeIndex(n, row, col + 1), countedIndexes);
        }
        return islandCount;
    }

    private int newConnectCount(int n, int newConnectIndex, Set<Integer> countedIndexes) {
        int count = 0;
        if (!countedIndexes.contains(landStartIndexes[newConnectIndex])) {
            count += islandCounter[newConnectIndex];
            countedIndexes.add(landStartIndexes[newConnectIndex]);
        }
        return count;
    }

    private int backtrack(int[][] grid, int startIndex, int curIndex) {
        int n = grid.length;
        if (landStartIndexes[curIndex] != -1) {
            // visited
            return 0;
        }
        int[] indexArr = decodeIndex(n, curIndex);
        int curRow = indexArr[0];
        int curCol = indexArr[1];
        if (grid[curRow][curCol] == 0) {
            landStartIndexes[curIndex] = curIndex;
            return 0;
        }
        landStartIndexes[curIndex] = startIndex;
        int islandCount = 1;
        if (curRow > 0) {
            // top
            islandCount += backtrack(grid, startIndex, encodeIndex(n, curRow - 1, curCol));
        }
        if (curRow < n - 1) {
            // bottom
            islandCount += backtrack(grid, startIndex, encodeIndex(n, curRow + 1, curCol));
        }
        if (curCol > 0) {
            // left
            islandCount += backtrack(grid, startIndex, encodeIndex(n, curRow, curCol - 1));
        }
        if (curCol < n - 1) {
            // right
            islandCount += backtrack(grid, startIndex, encodeIndex(n, curRow, curCol + 1));
        }
        return islandCount;
    }

    private int encodeIndex(int n, int row, int col) {
        return row * n + col;
    }

    private int[] decodeIndex(int n, int indexCode) {
        // [row, col]
        return new int[] {indexCode / n, indexCode % n};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{1,1},{1,1}};
        System.out.println("test: " + sol.largestIsland(grid));
    }
}