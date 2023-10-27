import java.util.Arrays;

class Solution {
    private static final int MOD = (int)(1e9 + 7);
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int n = horizontalCuts.length;
        int maxHeight = Math.max(horizontalCuts[0], h - horizontalCuts[n - 1]);
        for (int i = 1; i < n; i++) {
            maxHeight = Math.max(maxHeight, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        int m = verticalCuts.length;
        int maxWidth = Math.max(verticalCuts[0], w - verticalCuts[m - 1]);
        for (int i = 1; i < m; i++) {
            maxWidth = Math.max(maxWidth, verticalCuts[i] - verticalCuts[i - 1]);
        }
        return (int)((long)maxHeight * maxWidth % MOD);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int h = 5;
        // int w = 4;
        // int[] horizontalCuts = {1,2,4};
        // int[] verticalCuts = {1,3};
        // int h = 5;
        // int w = 4;
        // int[] horizontalCuts = {3,1};
        // int[] verticalCuts = {1};
        int h = 6;
        int w = 5;
        int[] horizontalCuts = {5};
        int[] verticalCuts = {2,1,4};
        System.out.println("test: " + sol.maxArea(h, w, horizontalCuts, verticalCuts));
    }
}