class Solution {
	public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] time = new int[n];
        int ans = -1;
        int clock = 1;
        for (int i = 0; i < n; i++) {
            if (time[i] > 0) {
                continue;
            }
            int startTime = clock;
            for (int node = i; node >= 0; node = edges[node]) {
                if (time[node] > 0) {
                    // 节点已访问
                    if (time[node] >= startTime) {
                        // 找到新的环
                        int cycleLen = clock - time[node];
                        ans = Math.max(ans, cycleLen);
                    }
                    break;
                }
                time[node] = clock;
                clock++;
            }
        }
		return ans;
	}

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] edges = {2,-1,3,1};
        int[] edges = {-1, 2, 6, 1, 3, 1, 4};
        System.out.println("test: " + sol.longestCycle(edges));
    }
}