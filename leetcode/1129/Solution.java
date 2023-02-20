import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Integer>> redGraph = new HashMap<>();
        Map<Integer, List<Integer>> blueGraph = new HashMap<>();
        for (int[] edge : redEdges) {
            List<Integer> nextNodes = redGraph.getOrDefault(edge[0], new ArrayList<>());
            nextNodes.add(edge[1]);
            redGraph.put(edge[0], nextNodes);
        }
        for (int[] edge : blueEdges) {
            List<Integer> nextNodes = blueGraph.getOrDefault(edge[0], new ArrayList<>());
            nextNodes.add(edge[1]);
            blueGraph.put(edge[0], nextNodes);
        }
        int[] redRes = shortestPath(redGraph, blueGraph, n);
        int[] blueRes = shortestPath(blueGraph, redGraph, n);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (redRes[i] == -1) {
                ans[i] = blueRes[i];
            } else if (blueRes[i] == -1) {
                ans[i] = redRes[i];
            } else {
                ans[i] = Math.min(redRes[i], blueRes[i]);
            }
        }
        return ans;
    }

    private int[] shortestPath(Map<Integer, List<Integer>> firstGraph, Map<Integer, List<Integer>> secondGraph, int n) {
        int[] res = new int[n];
        Arrays.fill(res, -1);
        int step = 0;
        boolean[][] visited = new boolean[2][n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, List<Integer>> currentGraph = step % 2 == 0 ? firstGraph : secondGraph;
            for (int i = 0; i < size; i++) {
                int curNode = queue.poll();
                if (visited[step % 2][curNode]) {
                    continue;
                }
                visited[step % 2][curNode] = true;
                if (res[curNode] == -1) {
                    res[curNode] = step;
                }

                List<Integer> nextNodes = currentGraph.getOrDefault(curNode, Collections.emptyList());
                for (Integer nextNode : nextNodes) {
                    queue.offer(nextNode);
                }
            }
            step++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] redEdges = {{0,1},{0,2}};
        int[][] blueEdges = {{1,0}};
        int[] ans = sol.shortestAlternatingPaths(n, redEdges, blueEdges);
        System.out.println("test: " + Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}