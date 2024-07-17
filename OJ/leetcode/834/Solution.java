import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private List<Integer>[] graph;
    private int[] sizes;
    private int[] sumArr;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // if (n == 1) {
        //     return new int[]{0};
        // }
        graph = new ArrayList[n];
        sizes = new int[n];
        sumArr = new int[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs(0, 0, 0);
        dfsDP(0, 0);
        return sumArr;
    }

    /**
     * 计算节点 `0` 与其他节点的距离和，同时以节点 `0` 为根节点计算，子树根节点的节点总数（包括子树根节点本身）
     * @param parentIdx
     * @param rootIdx
     * @param depth
     * @return
     */
    private void dfs(int parentIdx, int rootIdx, int depth) {
        // 每个节点与根节点的距离就是节点深度
        sumArr[0] += depth;
        List<Integer> nodes = graph[rootIdx];
        int countOfNodes = 1;
        for (Integer nodeIdx : nodes) {
            if (parentIdx == nodeIdx) {
                // 由于无向图，因此需要跳过父节点
                continue;
            }
            dfs(rootIdx, nodeIdx, depth + 1);
            countOfNodes += sizes[nodeIdx];
        }
        sizes[rootIdx] = countOfNodes;
    }

    /**
     * 以节点 `0` 为根节点，根据 size 数组以及节点 `0` 的距离和，计算每个子节点的距离和
     * @return
     */
    private void dfsDP(int parentIdx, int rootIdx) {
        int n = sizes.length;
        List<Integer> nodes = graph[rootIdx];
        for (Integer nodeIdx : nodes) {
            if (parentIdx == nodeIdx) {
                // 由于无向图，因此需要跳过父节点
                continue;
            }
            // 与当前节点(rootIdx)连接的所有节点数量（除当前子节点外）
            int rootNodeCount = n - sizes[nodeIdx];
            int rootSumOfDistance = sumArr[rootIdx];
            // 子节点距离和 = 加上与当前节点连接的所有节点数量，减去子节点数量（远离当前节点的节点数量）
            int nodeSumOfDistance = rootSumOfDistance + rootNodeCount - sizes[nodeIdx];
            sumArr[nodeIdx] = nodeSumOfDistance;
            // 继续计算子节点的子树距离和
            dfsDP(rootIdx, nodeIdx);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 1;
        // int[][] edges = {};
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        System.out.println("test: " + Arrays.stream(sol.sumOfDistancesInTree(n, edges)).boxed().collect(Collectors.toList()));
    }
}