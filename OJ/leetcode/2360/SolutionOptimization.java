class SolutionOptimization {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] visitTime = new int[n];
        int ans = -1;
        int clock = 1;
        for (int i = 0; i < n; i++) {
            if (visitTime[i] > 0) continue;
            int startTime = clock;
            for (int node = i; node >= 0; node = edges[node]) {
                if (visitTime[node] > 0) {
                    if (visitTime[node] >= startTime) {
                        // find a cycle
                        int cycleLen = clock - visitTime[node];
                        ans = Math.max(ans, cycleLen);
                    }
                    // already visited
                    break;
                }
                visitTime[node] = clock;
                clock++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int[] edges = {3,3,4,2,3};
        System.out.println("test: " + sol.longestCycle(edges));
    }
}