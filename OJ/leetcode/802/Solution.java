import java.util.ArrayList;
import java.util.List;

class Solution {
    boolean[] safes;
    boolean[] unsafes;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        safes = new boolean[n];
        unsafes = new boolean[n];

        List<Integer> ans = new ArrayList<>();
        boolean[] visiteds = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isSafeNode(i, graph, visiteds)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean isSafeNode(int node, int[][] graph, boolean[] visiteds) {
        if (visiteds[node] || unsafes[node]) {
            return false;
        }
        if (safes[node]) {
            return true;
        }
        int[] nextNodes = graph[node];
        visiteds[node] = true;
        boolean isSafe = true;
        for (int nextNode : nextNodes) {
            if (!isSafeNode(nextNode, graph, visiteds)) {
                isSafe = false;
                break;
            }
        }
        visiteds[node] = false;
        
        if (isSafe) {
            safes[node] = true;
            return true;
        } else {
            unsafes[node] = true;
            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println("test: " + sol.eventualSafeNodes(graph));
    }
}