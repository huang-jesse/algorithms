import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionTopoSort {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        int[] indegrees = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            indegrees[edge[0]]++;
            indegrees[edge[1]]++;
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Queue<Integer> topoQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 入度为 1 的为叶子节点
            if (indegrees[i] == 1 && coins[i] == 0) {
                // 放入所有无 coin 的叶子节点
                topoQueue.offer(i);
            }
        }
        boolean[] isDeleted = new boolean[n];
        // 不断删除最外层所有无 coin 的叶子节点
        while (!topoQueue.isEmpty()) {
            int noCoinNode = topoQueue.poll();
            // 删除叶子节点
            isDeleted[noCoinNode] = true;
            for (int nextNode : graph[noCoinNode]) {
                if (isDeleted[nextNode]) {
                    continue;
                }
                indegrees[nextNode]--;
                // 入度为 1 则为叶子节点
                if (indegrees[nextNode] == 1 && coins[nextNode] == 0) {
                    topoQueue.offer(nextNode);
                }
            }
        }
        // 所有外层叶子节点均有 coin
        for (int i = 0; i < n; i++) {
            if (!isDeleted[i] && indegrees[i] == 1) {
                // 剩下的最外围未删除的叶子节点
                topoQueue.offer(i);
            }
        }
        // 删除最外面 2 层的叶子节点，收集外围两层叶子节点的 coin 可以通过访问，接下来剩下的核心树中任一叶子节点进行收集
        for (int i = 0; i < 2 && !topoQueue.isEmpty(); i++) {
            int size = topoQueue.size();
            for (int j = 0; j < size; j++) {
                int node = topoQueue.poll();
                isDeleted[node] = true;
                for (int nextNode : graph[node]) {
                    if (isDeleted[nextNode]) {
                        continue;
                    }
                    indegrees[nextNode]--;
                    if (indegrees[nextNode] == 1) {
                        topoQueue.offer(nextNode);
                    }
                }
            }
        }
        // 剩下的核心树，访问所有边则表示收集了所有 coin 即答案为 2 * 有向边数 = 2 * (节点数 - 1)
        int nodeCount = 0;
        for (boolean deleteFlag : isDeleted) {
            if (!deleteFlag) {
                nodeCount++;
            }
        }
        if (nodeCount == 0) {
            return 0;
        } else {
            return 2 * (nodeCount - 1);
        }
    }


    public static void main(String[] args) {
        SolutionTopoSort sol = new SolutionTopoSort();
        // int[] coins = {0,0,0,1,1,0,0,1};
        // int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{5,6},{5,7}};
        int[] coins = {1,0,0,0,0,1};
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5}};
        System.out.println("test: " + sol.collectTheCoins(coins, edges));
    }
}