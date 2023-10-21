class Solution {
    private int[] fa;
    private int[] sizes;
    public long countPairs(int n, int[][] edges) {
        this.fa = new int[n];
        this.sizes = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            sizes[i] = 1;
        }
        for (int[] edge : edges) {
            merge(edge[0], edge[1]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int count = sizes[find(i)];
            ans += n - count;
        }
        return ans / 2;
    }

    private void merge(int i, int j) {
        int rooti = find(i);
        int rootj = find(j);
        if (rooti != rootj) {
            if (sizes[rooti] >= sizes[rootj]) {
                fa[rootj] = rooti;
                sizes[rooti] += sizes[rootj];
            } else {
                fa[rooti] = rootj;
                sizes[rootj] += sizes[rooti];
            }
        }
    }

    private int find(int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa[i]);
            return fa[i];
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 7;
        int[][] edges = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        // int n = 11;
        // int[][] edges = {{5,0},{1,0},{10,7},{9,8},{7,2},{1,3},{0,2},{8,5},{4,6},{4,2}};
        System.out.println("test: " + sol.countPairs(n, edges));
    }
}