import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        int[] degrees = new int[n + 1];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;

            Set<Integer> firstAdjacents = graph.getOrDefault(edge[0], new HashSet<>());
            firstAdjacents.add(edge[1]);
            graph.put(edge[0], firstAdjacents);
            Set<Integer> secondAdjacents = graph.getOrDefault(edge[1], new HashSet<>());
            secondAdjacents.add(edge[0]);
            graph.put(edge[1], secondAdjacents);
        }
        int ans = Integer.MAX_VALUE;
        for (int first = 1; first <= n; first++) {
            Set<Integer> firstAdjacents = graph.getOrDefault(first, new HashSet<>());
            for (int second = first + 1; second <= n; second++) {
                if (!firstAdjacents.contains(second)) {
                    continue;
                }
                Set<Integer> secondAdjacents = graph.getOrDefault(second, new HashSet<>());
                for (int third = second + 1; third <= n; third++) {
                    if (!secondAdjacents.contains(third) || !firstAdjacents.contains(third)) {
                        continue;
                    }
                    // find a trio
                    ans = Math.min(ans, degrees[first] + degrees[second] + degrees[third] - 6);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] edges = {{1,2},{1,3},{3,2},{4,1},{5,2},{3,6}};
        System.out.println("test: " + sol.minTrioDegree(n, edges));
    }
}