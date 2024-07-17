import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<Integer, List<int[]>> routes = new HashMap<>();
    private int[][] memo;
    private static final int INF = 0x3f3f3f3f;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.memo = new int[n][k+1];
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            List<int[]> nextList = this.routes.getOrDefault(from, new ArrayList<>());
            nextList.add(new int[]{to, price});
            this.routes.put(from, nextList);
        }
        int ans = cheapestPrice(src, dst, k);
        return ans == INF ? -1 : ans;
    }

    private int cheapestPrice(int src, int dst, int k) {
        if (src == dst) {
            return 0;
        }
        if (k < 0) {
            return INF;
        }
        if (this.memo[src][k] != 0) {
            return this.memo[src][k];
        }
        List<int[]> nextList = this.routes.getOrDefault(src, new ArrayList<>());
        int minPrice = INF;
        for (int[] next : nextList) {
            int to = next[0];
            int price = next[1];
            minPrice = Math.min(minPrice, price + cheapestPrice(to, dst, k-1));
        }
        this.memo[src][k] = minPrice;
        return minPrice;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println("test: " + sol.findCheapestPrice(n, flights, src, dst, k));
    }
}