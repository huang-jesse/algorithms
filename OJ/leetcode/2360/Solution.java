import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] indegrees = new int[n];
        for (int i = 0; i < n; i++) {
            if (edges[i] == -1) continue;
            indegrees[edges[i]]++;
        }

        // topo sort
        Queue<Integer> s = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                s.offer(i);
            }
        }
        boolean[] visited = new boolean[n];
        int cnt = 0;
        while (!s.isEmpty()) {
            int node = s.poll();
            visited[node] = true;
            cnt++;
            if (edges[node] == -1) continue;
            indegrees[edges[node]]--;
            if (indegrees[edges[node]] == 0) {
                s.offer(edges[node]);
            }
        }
        if (cnt == n) {
            // there is no cycle exists
            return -1;
        }

        int ans = 0;
        // find cycle
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int node = i;
            int cycleLen = 0;
            while (!visited[node]) {
                visited[node] = true;
                cycleLen++;
                node = edges[node];
            }
            ans = Math.max(ans, cycleLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] edges = {3,3,4,2,3};
        System.out.println("test: " + sol.longestCycle(edges));
    }
}