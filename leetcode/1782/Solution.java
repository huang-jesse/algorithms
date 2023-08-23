import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degrees = new int[n + 1];
        Map<Integer, Map<Integer, Integer>> pairCounter = new HashMap<>();
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
            int min = Math.min(edge[0], edge[1]);
            int max = Math.max(edge[0], edge[1]);
            Map<Integer, Integer> nodeCounter = pairCounter.getOrDefault(min, new HashMap<>());
            pairCounter.put(min, nodeCounter);
            nodeCounter.put(max, nodeCounter.getOrDefault(max, 0) + 1);
        }

        int[] sortedDegrees = Arrays.copyOf(degrees, n + 1);
        Arrays.sort(sortedDegrees);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int query = queries[i];
            int res = 0;
            for (int j = 1; j < n; j++) {
                // sortedDegrees[j] + x > query -> x > query - sortedDegrees[j]
                int startIndex = binarySearchLeftBoundary(sortedDegrees, j + 1, query - sortedDegrees[j]);
                res += (n - startIndex + 1);
            }
            // res 需要减去 nodePair 可能多算的一部分（比如针对 PairNode(1,2) 时如果存在 [1,2] [2,1]这样的edge, 则会导致 connectedCounter[j] 多加了 2
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : pairCounter.entrySet()) {
                int min = entry.getKey();
                Map<Integer, Integer> nodeCounter = entry.getValue();
                for (Map.Entry<Integer, Integer> nodeEntry : nodeCounter.entrySet()) {
                    int max = nodeEntry.getKey();
                    int count = nodeEntry.getValue();
                    int incidentNum = degrees[min] + degrees[max] - count;
                    if (degrees[min] + degrees[max] > query && incidentNum <= query) {
                        // 说明当前 PairNode(min, max) 再答案计算时多计算了 1 次
                        res--;
                    }
                }
            }
            ans[i] = res;
        }
        return ans;
    }

    private int binarySearchLeftBoundary(int[] sortedDegrees, int left, int target) {
        int right = sortedDegrees.length - 1 + 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortedDegrees[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] edges = {{1,5},{1,5},{3,4},{2,5},{1,3},{5,1},{2,3},{2,5}};
        int[] queries = {1,2,3,4,5};
        // int n = 4;
        // int[][] edges = {{1,2},{2,4},{1,3},{2,3},{2,1}};
        // int[] queries = {2,3};
        System.out.println("test: " + Arrays.toString(sol.countPairs(n, edges, queries)));
    }
}