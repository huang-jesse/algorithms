import java.util.Arrays;

class Solution {
    public int matrixSum(int[][] nums) {
        int m = nums[0].length;
        int[] maxOfRow = new int[m];
        for (int[] row : nums) {
            Arrays.sort(row);
            for (int i = 0; i < m; i++) {
                // [0, m - 1] [max - > min]
                maxOfRow[i] = Math.max(maxOfRow[i], row[m - 1 - i]);
            }
        }
        return Arrays.stream(maxOfRow).sum();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] nums = {{7,2,1},{6,4,2},{6,5,3},{3,2,1}};
        System.out.println("test: " + sol.matrixSum(nums));
    }
}