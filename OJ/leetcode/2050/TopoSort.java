import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSort {
    // 有向无环图
    private List<Integer>[] graph;
    // 所有顶点的入度
    private int[] indegrees;
    // 顶点数量
    private int n;
    public TopoSort(List<Integer>[] graph, int[] indegrees) {
        this.graph = graph;
        this.indegrees = indegrees;
        this.n = graph.length;
    }

    public List<Integer> topoSort() {
        Queue<Integer> s = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                s.offer(i);
            }
        }
        List<Integer> l = new ArrayList<>();
        while (!s.isEmpty()) {
            int u = s.poll();
            l.add(u);
            for (int v : graph[u]) {
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    s.offer(v);
                }
            }
        }
        // 判断是否图有环
        if (l.size() == n) {
            // 如果 l 的大小和图的顶点数量一致，则说明无环，返回拓扑排序后的列表
            return l;
        } else {
            // 图至少有一个环路
            throw new IllegalStateException("Graph has at least one cycle");
        }
    }

    public static void main(String[] args) {
        int n = 13;
        int[][] edges = {{0,6},{0,1},{0,5},{2,0},{2,3},{3,5},{5,4},{6,4},{6,9},{7,6},{8,7},{9,10},{9,11},{9,12},{11,12}};
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegrees = new int[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            indegrees[edge[1]]++;
        }
        TopoSort topoSort = new TopoSort(graph, indegrees);
        System.out.println("test: " + topoSort.topoSort());
    }
}
