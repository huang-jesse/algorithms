import java.util.Arrays;

class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int n = items.length;
        int m = queries.length;
        Arrays.sort(items, (o1, o2) -> (o1[0] - o2[0]));
        int[] preMax = new int[n];
        preMax[0] = items[0][1];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], items[i][1]);
        }
        int[] answers = new int[m];
        for (int i = 0; i < m; i++) {
            // binary search right boundary
            int index = binarySearchRightBound(items, queries[i]);
            if (index >= 0) {
                answers[i] = preMax[index];
            }
        }
        return answers;
    }

    private int binarySearchRightBound(int[][] items, int target) {
        if (items[0][0] > target) return -1;
        int n = items.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (items[mid][0] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] items = {{1,2},{3,2},{2,4},{5,6},{3,5}};
        int[] queries = {1,2,3,4,5,6}; // ans: {2,4,5,5,6,6}
        System.out.println("test: " + Arrays.toString(sol.maximumBeauty(items, queries)));
    }
}