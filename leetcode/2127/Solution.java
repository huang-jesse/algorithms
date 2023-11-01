import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegrees = new int[n];
        for (int i = 0; i < n; i++) {
            indegrees[favorite[i]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 最长父节点路径统计
        int[] rootPathCounter = new int[n];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int nextNode = favorite[node];
            rootPathCounter[nextNode] = Math.max(1 + rootPathCounter[node], rootPathCounter[nextNode]);
            indegrees[nextNode]--;
            if (indegrees[nextNode] == 0) {
                queue.offer(nextNode);
            }
        }
        // visited[i] 为 false 得均为环中的节点（可能有多个环）
        // 二节点环累加
        int twoNodeAns = 0;
        // 超过两个节点的环（不可累加）
        int loopAns = 0;
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                continue;
            }
            indegrees[i] = 0;
            // 环，有两种可能：
            // 1. 两个节点互环，这样所有指向这两个节点的节点都是合法节点，同时所有二节点互环同时合法（即可以累加）
            // 2. 超过两个节点的环，这样仅当前环内的节点合法（仅有一个环合法）
            int j = favorite[i];
            int count = 1;
            while (j != i) {
                indegrees[j] = 0;
                j = favorite[j];
                count++;
            }

            if (count == 2) {
                // 两个节点互环
                // 累计两个节点的最长父节点路径统计，计算总合法节点数量
                int res = count + rootPathCounter[i] + rootPathCounter[favorite[i]];
                twoNodeAns += res;
            } else {
                // 超过两个节点的环
                // dfs 统计环内节点数
                loopAns = Math.max(loopAns, count);
            }
        }
        return Math.max(twoNodeAns, loopAns);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] favorite = {2,2,1,2};
        // int[] favorite = {3,0,1,4,1};
        System.out.println("test: " + sol.maximumInvitations(favorite));
    }
}