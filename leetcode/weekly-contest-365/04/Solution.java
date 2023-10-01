import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        // topo sort
        int[] indegrees = new int[n];
        // 父节点
        List<Integer>[] parents = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            parents[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            indegrees[edges.get(i)]++;
            parents[edges.get(i)].add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 遍历移除所有环外节点
        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 邻接节点
            int adjacentNode = edges.get(node);
            indegrees[adjacentNode]--;
            if (indegrees[adjacentNode] == 0) {
                queue.offer(adjacentNode);
            }
        }
        // 遍历环内节点
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                // 环外节点或已遍历的环内节点
                continue;
            }
            // 遍历环内所有节点
            int current = i;
            Set<Integer> cycleNodes = new HashSet<>();
            int count = 0;
            while (!cycleNodes.contains(current)) {
                cycleNodes.add(current);
                indegrees[current] = 0;
                count++;

                current = edges.get(current);
            }
            // 更新环内节点答案
            for (int cycleNode : cycleNodes) {
                ans[cycleNode] = count;
            }
        }
        // 更新环外节点答案，BFS
        queue.clear();
        boolean[] visisted = new boolean[n];
        // 通过每个环内节点向外 BFS 更新环外节点的答案
        for (int i = 0; i < n; i++) {
            if (ans[i] != 0) {
                queue.offer(i);
                visisted[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int parent : parents[node]) {
                if (visisted[parent]) {
                    continue;
                }
                if (ans[parent] == 0) {
                    // 环外节点
                    ans[parent] = 1 + ans[node];
                    queue.offer(parent);
                    visisted[parent] = true;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Integer> edges = Arrays.asList(1,2,0,0);
        int[] ans = sol.countVisitedNodes(edges);
        System.out.println("test: " + Arrays.toString(ans));
    }
}