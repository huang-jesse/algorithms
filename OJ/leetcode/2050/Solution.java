import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private Integer[] memo;
    private List<Integer>[] graph;
    private int[] time;
    public int minimumTime(int n, int[][] relations, int[] time) {
        this.memo = new Integer[n + 1];
        this.graph = new ArrayList[n + 1];
        this.time = time;
        Set<Integer> starters = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            starters.add(i);
            graph[i] = new ArrayList<>();
        }
        for (int[] relation : relations) {
            int preCourse = relation[0];
            int nextCourse = relation[1];
            graph[preCourse].add(nextCourse);
            if (starters.contains(nextCourse)) {
                starters.remove(nextCourse);
            }
        }
        int ans = 0;
        for (Integer course : starters) {
            ans = Math.max(ans, dfs(course));
        }
        return ans;
    }

    private int dfs(int currentCourse) {
        if (this.memo[currentCourse] != null) {
            return this.memo[currentCourse];
        }
        int takenTime = time[currentCourse - 1];
        List<Integer> nextNodes = graph[currentCourse];
        int nextTakenTime = 0;
        for (Integer nextCourse : nextNodes) {
            nextTakenTime = Math.max(nextTakenTime, dfs(nextCourse));
        }
        takenTime += nextTakenTime;
        this.memo[currentCourse] = takenTime;
        return takenTime;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] relations = {{1,5},{2,5},{3,5},{3,4},{4,5}};
        int[] time = {1,2,3,4,5};
        System.out.println("test: " + sol.minimumTime(n, relations, time));
    }
}