import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
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
        List<Integer>[] connectedArray = (ArrayList<Integer>[])new ArrayList[n + 1];
        Arrays.setAll(connectedArray, o -> new ArrayList<>());
        for (int i = threshold + 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    if (j > threshold) {
                        // divisors 1
                        connectedArray[j].add(i);
                    }
                    // divisors 2
                    int divisors = i / j;
                    if (divisors != j && divisors > threshold) {
                        connectedArray[divisors].add(i);
                    }
                }
            }
            connectedArray[i].add(i);
        }
        // [1,n]
        this.fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
        for (int i = threshold + 1; i <= n; i++) {
            List<Integer> connectedList = connectedArray[i];
            int size = connectedList.size();
            for (int j = 1; j < size; j++) {
                merge(connectedList.get(j), connectedList.get(j - 1));
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
        Solution sol = new Solution();
        int n = 6;
        int threshold = 2;
        int[][] queries = {{1,4},{2,5},{3,6}};
        System.out.println("test: " + sol.areConnected(n, threshold, queries));
    }
}