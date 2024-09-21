class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] nodeScores = new long[n];
        for (int i = 0; i < n; i++) {
            nodeScores[edges[i]] += i;
        }
        int ans = 0;
        for (int node = 0; node < n; node++) {
            if (nodeScores[node] > nodeScores[ans]) {
                ans = node;
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