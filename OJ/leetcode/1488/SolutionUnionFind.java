import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SolutionUnionFind {
    private static final int INF = 1000000;
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] fa = new int[n];
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        Map<Integer, Integer> fullLakes = new HashMap<>();
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 0; i < n; i++) {
            if (rains[i] != 0) {
                int lake = rains[i];
                ans[i] = -1;
                if (i > 0 && rains[i - 1] != 0) {
                    fa[i] = fa[i - 1];
                } else {
                    fa[i] = i;
                    idx[i] = INF;
                }
                if (fullLakes.containsKey(lake)) {
                    // need dry this lake before
                    int rainDay = fullLakes.get(lake);
                    int root = find(fa, rainDay);
                    if (idx[root] > i) {
                        // can not avoid flood
                        return new int[]{};
                    }
                    ans[idx[root]++] = lake;
                    if (idx[root] < n && rains[idx[root]] != 0) {
                        fa[root] = find(fa, idx[root]);
                    }
                }
                fullLakes.put(lake, i);
            } else if (i > 0 && rains[i - 1] != 0) {
                // dry day
                idx[find(fa, i - 1)] = i;
            }
        }
        return ans;
    }

    private int find(int[] fa, int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa, fa[i]);
            return fa[i];
        }
    }

    public static void main(String[] args) {
        SolutionUnionFind sol = new SolutionUnionFind();
        // int[] rains = {1,2,0,0,2,1};
        int[] rains = {1,0,2,0,3,0,2,0,0,0,1,2,3};
        // int[] rains = {0,1,1};
        System.out.println("test: " + Arrays.toString(sol.avoidFlood(rains)));
    }
}