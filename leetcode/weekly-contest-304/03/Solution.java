import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MAX_VAL = 100010;
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        Map<Integer, Integer> node1DsMap = new HashMap<>();
        boolean[] visited = new boolean[n];
        int currentNode1 = node1;
        int node1Ds = 0;
        while (currentNode1 > -1 && !visited[currentNode1]) {
            visited[currentNode1] = true;
            node1DsMap.put(currentNode1, node1Ds);

            node1Ds++;
            currentNode1 = edges[currentNode1];
        }

        visited = new boolean[n];
        int minDs = MAX_VAL;
        int ans = -1;
        int currentNode2 = node2;
        int node2Ds = 0;
        while (currentNode2 > -1 && !visited[currentNode2]) {
            visited[currentNode2] = true;
            if (node1DsMap.containsKey(currentNode2)) {
                node1Ds = node1DsMap.get(currentNode2);
                int maxNodeDs = Math.max(node2Ds, node1Ds);
                if (maxNodeDs < minDs) {
                    minDs = maxNodeDs;
                    ans = currentNode2;
                } else if (maxNodeDs == minDs) {
                    ans = Math.min(ans, currentNode2);
                }
            }

            node2Ds++;
            currentNode2 = edges[currentNode2];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] edges = {-1, 2, 6, 1, 3, 1, 4};
        int node1 = 5;
        int node2 = 6;
        System.out.println("test: " + sol.closestMeetingNode(edges, node1, node2));
    }
}