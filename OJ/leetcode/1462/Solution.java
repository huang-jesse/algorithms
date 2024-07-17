import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[][] reachables;
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] diGraph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            diGraph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            diGraph[prerequisite[0]].add(prerequisite[1]);
        }
        reachables = new boolean[numCourses][numCourses];
        for (int course = 0; course < numCourses; course++) {
            dfs(diGraph, reachables[course], course);
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(reachables[query[0]][query[1]]);
        }
        return ans;
    }

    private void dfs(List<Integer>[] diGraph, boolean[] reachable, int current) {
        reachable[current] = true;
        List<Integer> adjacents = diGraph[current];
        for (int adj : adjacents) {
            if (!reachable[adj]) {
                dfs(diGraph, reachable, adj);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int numCourses = 3;
        int[][] prerequisites = {{1,2},{1,0},{2,0}};
        int[][] queries = {{1,0},{1,2}};
        System.out.println("test: " + sol.checkIfPrerequisite(numCourses, prerequisites, queries));
    }
}