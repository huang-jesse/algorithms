import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> blockedSet = Arrays.stream(restricted).boxed().collect(Collectors.toSet());
        Map<Integer, List<Integer>> graphs = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (blockedSet.contains(from) || blockedSet.contains(to)) {
                continue;
            }
            List<Integer> list1 = graphs.getOrDefault(from, new ArrayList<>());
            list1.add(to);
            graphs.put(from, list1);

            List<Integer> list2 = graphs.getOrDefault(to, new ArrayList<>());
            list2.add(from);
            graphs.put(to, list2);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                List<Integer> nextNodes = graphs.getOrDefault(node, new ArrayList<>());
                for (int nextNode : nextNodes) {
                    if (visited[nextNode]) {
                        continue;
                    }
                    queue.offer(nextNode);
                    visited[nextNode] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 7;
        int[][] edges = {{0,1},{0,2},{0,5},{0,4},{3,2},{6,5}};
        int[] restricted = {4,2,1};
        System.out.println("test: " + sol.reachableNodes(n, edges, restricted));
    }
}