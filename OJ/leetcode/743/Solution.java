import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> nodeMap = new HashMap<>();
        for (int[] path : times) {
            int source = path[0];
            int destination = path[1];
            int time = path[2];

            List<int[]> nextNodes = nodeMap.getOrDefault(source, new ArrayList<>());
            int[] nextNode = {destination, time};
            nextNodes.add(nextNode);
            nodeMap.put(source, nextNodes);
        }

        Comparator<int[]> compare = (o1, o2) -> o1[1] - o2[1];
        PriorityQueue<int[]> nodePq = new PriorityQueue<>(compare);
        nodePq.offer(new int[]{k, 0});
        boolean[] visited = new boolean[n+1];
        int count = 0;
        int delayTime = 0;
        while (count != n && !nodePq.isEmpty()) {
            int[] node = nodePq.poll();
            int currentNodeId = node[0];
            delayTime = node[1];
            if (visited[currentNodeId]) {
                continue;
            }
            visited[currentNodeId] = true;
            count++;
            List<int[]> nextNodes = nodeMap.getOrDefault(currentNodeId, new ArrayList<>());
            for (int[] nextNode : nextNodes) {
                int nextNodeId = nextNode[0];
                int nextTime = nextNode[1];
                nodePq.offer(new int[]{nextNodeId, delayTime + nextTime});
            }
        }
        return count == n ? delayTime : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] times = {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
        int n = 4;
        int k = 1;
        System.out.println("test: " + sol.networkDelayTime(times, n, k));
    }
}