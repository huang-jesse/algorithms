import java.util.Arrays;

class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int totalLen = n * n;
        int[] counter = new int[totalLen + 1];
        for (int[] row : grid) {
            for (int num : row) {
                counter[num]++;
            }
        }
        int repeatedValue = -1;
        int missingValue = -1;
        for (int i = 1; i <= totalLen; i++) {
            if (counter[i] == 0) {
                missingValue = i;
            } else if (counter[i] == 2) {
                repeatedValue = i;
            }
        }
        return new int[]{repeatedValue, missingValue};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {{9,1,7},{8,9,2},{3,4,6}};
        System.out.println("test: " + Arrays.toString(sol.findMissingAndRepeatedValues(grid)));
    }
}