import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] fa;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }
        // UnionFind
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            List<Integer> dislikeList = graph[i];
            for (int disliekId : dislikeList) {
                if (find(disliekId) == find(i)) {
                    return false;
                }
                merge(disliekId, dislikeList.get(0));
            }
        }
        return true;
    }

    private int find(int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa[i]);
            return fa[i];
        }
    }

    /**
     * Merge i to j
     * @param i
     * @param j
     */
    private void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 4;
        // int[][] dislikes = {{1,2},{1,3},{2,4}};
        int n = 3;
        int[][] dislikes = {{1,2},{1,3},{2,3}};
        System.out.println("test: " + sol.possibleBipartition(n, dislikes));
    }
}