import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0;  i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
            indegrees[prerequisite[0]]++;
        }
        return topoSort(graph, indegrees);
    }

    private int[] topoSort(List<Integer>[] graph, int[] indegrees) {
        int n = graph.length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v : graph[u]) {
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
            res[index++] = u;
        }
        return index == n ? res : new int[]{};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1}};
        System.out.println("test: " + Arrays.toString(sol.findOrder(numCourses, prerequisites)));
    }
}