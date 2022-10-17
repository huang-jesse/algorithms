import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    private int[] fa;
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        if (n == 1) {
            return 1;
        }
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }

        // make graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> nextNodes = graph.getOrDefault(edge[0], new ArrayList<>());
            nextNodes.add(edge[1]);
            graph.put(edge[0], nextNodes);

            List<Integer> preNodes = graph.getOrDefault(edge[1], new ArrayList<>());
            preNodes.add(edge[0]);
            graph.put(edge[1], preNodes);
        }
        // min heap order by val
        TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> indexList = tm.getOrDefault(vals[i], new ArrayList<>());
            indexList.add(i);
            // {val, indexList}
            tm.put(vals[i], indexList);
        }
        int ans = 0;
        while (!tm.isEmpty()) {
            Map.Entry<Integer, List<Integer>> entry = tm.pollFirstEntry();
            int curVal = entry.getKey();
            List<Integer> curIndexList = entry.getValue();
            // union nodes
            for (Integer curIndex : curIndexList) {
                List<Integer> nodes = graph.get(curIndex);
                for (int nodeIndex : nodes) {
                    if (vals[nodeIndex] <= curVal) {
                        merge(curIndex, nodeIndex);
                    }
                }
            }
            // count nodes of the same root index
            Map<Integer, Integer> rootIndexCounter = new HashMap<>();
            for (Integer curIndex : curIndexList) {
                int rootIndex = find(curIndex);
                rootIndexCounter.put(rootIndex, rootIndexCounter.getOrDefault(rootIndex, 0) + 1);
            }
            // count good path
            int res = 0;
            for (int count : rootIndexCounter.values()) {
                res += getCombinatorial2(count);
            }
            ans += res;
        }
        return ans + n;
    }

    private int getCombinatorial2(int num) {
        if (num < 2) {
            return 0;
        }
        return num * (num - 1) / 2;
    }

    /**
     * merge i to j
     * @param i
     * @param j
     */
    private void merge(int i, int j) {
        int root = find(i);
        fa[root] = find(j);
    }

    /**
     * find root of i, path compression
     **/
    private int find(int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa[i]);
            return fa[i];
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] vals = {1,3,2,1,3};
        int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
        System.out.println("test: " + sol.numberOfGoodPaths(vals, edges));
    }
}