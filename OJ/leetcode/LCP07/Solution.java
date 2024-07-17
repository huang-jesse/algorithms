import java.util.ArrayList;
import java.util.List;

class Solution {
    public int numWays(int n, int[][] relation, int k) {
        List<Integer>[] neighborPlayers = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            neighborPlayers[i] = new ArrayList<>();
        }
        for (int[] player : relation) {
            int numberOfPlayer = player[0];
            int neighbor = player[1];
            neighborPlayers[numberOfPlayer].add(neighbor);
        }
        Integer[][] memo = new Integer[n][k+1];
        return dfs(neighborPlayers, 0, k, memo);
    }

    private int dfs(List<Integer>[] neighborPlayers, int index, int k, Integer[][] memo) {
        int n = neighborPlayers.length;
        if (k == 0) {
            if (index == n-1) {
                return 1;
            }
            return 0;
        }
        if (memo[index][k] != null) {
            return memo[index][k];
        }
        List<Integer> neighbors = neighborPlayers[index];
        int ans = 0;
        for (Integer neighbor : neighbors) {
            ans += dfs(neighborPlayers, neighbor, k-1, memo);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int k = 3;
        System.out.println("test: " + sol.numWays(n, relation, k));
    }
}