import java.util.Arrays;

class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] scoreCounter = new long[n];
        for (int i = 0; i < n; i++) {
            int target = edges[i];
            scoreCounter[target] += i;
        }
        long maxScore = Arrays.stream(scoreCounter).max().getAsLong();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long score = scoreCounter[i];
            if (score == maxScore) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] edges = {1,0,0,0,0,7,7,5};
        System.out.println("test: " + sol.edgeScore(edges));
    }
}