import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionTopoSort {
    private List<Integer>[] graph;
    private int[] indegrees;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.indegrees = new int[numCourses];
        this.graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            indegrees[prerequisite[1]]++;
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        boolean isFinish = topoSort();
        return isFinish;
    }

    public boolean topoSort() {
        int n = indegrees.length;
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
            // 如果 l 的大小和图的顶点数量一致，则说明无环
            return true;
        } else {
            // 图至少有一个环路
            return false;
        }
    }

    public static void main(String[] args) {
        SolutionTopoSort sol = new SolutionTopoSort();
        int numCourses = 2;
        // int[][] prerequisites = {{1,0},{0,1}};
        int[][] prerequisites = {{1,0}};
        System.out.println("test: " + sol.canFinish(numCourses, prerequisites));
    }
}