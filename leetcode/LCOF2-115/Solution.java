import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        int[] indegrees = new int[n + 1];
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<Integer>();
        }
        for (int[] sequence : sequences) {
            int size = sequence.length;
            for (int i = 1; i < size; i++) {
                int prev = sequence[i - 1], next = sequence[i];
                if (graph[prev].add(next)) {
                    indegrees[next]++;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int num = queue.poll();
            Set<Integer> set = graph[num];
            for (int next : set) {
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3};
        int[][] sequences = {{1,2},{1,3},{2,3}};
        System.out.println("test: " + sol.sequenceReconstruction(nums, sequences));
    }
}