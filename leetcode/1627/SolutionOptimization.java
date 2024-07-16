import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SolutionOptimization {
    private int[] fa;
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        int m = queries.length;
        if (threshold == 0) {
            // all connected
            return Collections.nCopies(m, Boolean.TRUE);
        } else if (threshold == n) {
            // not connected
            return Collections.nCopies(m, Boolean.FALSE);
        }
        List<Boolean> ans = new ArrayList<>();
        // [1,n]
        this.fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
        for (int z = threshold + 1; z <= n; z++) {
            for (int p = 2 * z; p <= n; p += z) {
                merge(z, p);
            }
        }
        for (int i = 0; i < queries.length; i++) {
            ans.add(find(queries[i][0]) == find(queries[i][1]));
        }
        return ans;
    }

    public void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    public int find(int x) {
        if (fa[x] == x) {
            return x;
        } else {
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }

    public static void main(String[] args) {
        SolutionOptimization sol = new SolutionOptimization();
        int n = 6;
        int threshold = 2;
        int[][] queries = {{1,4},{2,5},{3,6}};
        System.out.println("test: " + sol.areConnected(n, threshold, queries));
    }
}