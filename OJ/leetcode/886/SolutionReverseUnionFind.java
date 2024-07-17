class SolutionReverseUnionFind {
    private int[] fa;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // UnionFind
        fa = new int[2 * n + 1];
        for (int i = 0; i <= 2 * n; i++) {
            fa[i] = i;
        }

        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];
            merge(a, b + n);
            merge(b, a + n);
            if (find(a) == find(b)) {
                return false;
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
        SolutionReverseUnionFind sol = new SolutionReverseUnionFind();
        // int n = 4;
        // int[][] dislikes = {{1,2},{1,3},{2,4}};
        int n = 3;
        int[][] dislikes = {{1,2},{1,3},{2,3}};
        System.out.println("test: " + sol.possibleBipartition(n, dislikes));
    }
}