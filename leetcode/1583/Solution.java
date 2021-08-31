class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] friendPairs = new int[n];
        for (int[] pair : pairs) {
            friendPairs[pair[0]] = pair[1];
            friendPairs[pair[1]] = pair[0];
        }

        int[][] ranks = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] friends = preferences[i];
            for (int rank = 0; rank < n-1; rank++) {
                ranks[i][friends[rank]] = rank;
            }
        }

        int ans = 0;
        for (int x = 0; x < n; x++) {
            int y = friendPairs[x];
            int rank = ranks[x][y];
            int[] friends = preferences[x];
            for (int i = rank-1; i >= 0; i--) {
                int u = friends[i];
                int v = friendPairs[u];
                // u prefers x over v
                if (ranks[u][x] < ranks[u][v]) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 4;
        int[][] preferences = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}};
        int[][] pairs = {{0,1},{2,3}};
        System.out.println("test: " + sol.unhappyFriends(n, preferences, pairs));
    }
}