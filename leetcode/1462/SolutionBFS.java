import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SolutionBFS {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] diGraph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            diGraph[i] = new ArrayList<>();
        }
        int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            diGraph[prerequisite[0]].add(prerequisite[1]);
            indegrees[prerequisite[1]]++;
        }
        boolean[][] reachables = new boolean[numCourses][numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int adj : diGraph[current]) {
                reachables[current][adj] = true;
                for (int i = 0; i < numCourses; i++) {
                    reachables[i][adj] = reachables[i][current] | reachables[i][adj];
                }
                indegrees[adj]--;
                if (indegrees[adj] == 0) {
                    queue.offer(adj);
                }
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(reachables[query[0]][query[1]]);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionBFS sol = new SolutionBFS();
        int numCourses = 3;
        int[][] prerequisites = {{1,2},{1,0},{2,0}};
        int[][] queries = {{1,0},{1,2}};
        System.out.println("test: " + sol.checkIfPrerequisite(numCourses, prerequisites, queries));
    }
}