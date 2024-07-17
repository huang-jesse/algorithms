import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class SolutionOptimization {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        // topo sort
        int[] indegrees = new int[n];
        for (int i = 0; i < n; i++) {
            indegrees[edges.get(i)]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 环外节点
        List<Integer> unCycleNodes = new ArrayList<>();
        // 遍历移除所有环外节点
        while (!queue.isEmpty()) {
            int node = queue.poll();
            unCycleNodes.add(node);
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
        // DFS 更新环外节点答案
        for (int node : unCycleNodes) {
            dfs(node, ans, edges);
        }
        return ans;
    }

    private int dfs(int node, int[] ans, List<Integer> edges) {
        if (ans[node] != 0) {
            // 环内节点或已计算完成的环外节点直接返回答案
            return ans[node];
        }
        int res = 1 + dfs(edges.get(node), ans, edges);
        ans[node] = res;
        return res;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        List<Integer> edges = Arrays.asList(1,2,0,0);
        int[] ans = sol.countVisitedNodes(edges);
        System.out.println("test: " + Arrays.toString(ans));
    }
}