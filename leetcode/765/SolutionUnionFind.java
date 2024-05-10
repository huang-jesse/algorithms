class SolutionUnionFind {
    private int[] fa;
    private int count;
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        if (n <= 2) {
            return 0;
        }
        int coupleGroupNum = n / 2;
        fa = new int[coupleGroupNum];
        for (int i = 0; i < coupleGroupNum; i++) {
            fa[i] = i;
        }
        count = coupleGroupNum;
        for (int i = 0; i < n - 1; i += 2) {
            int coupleGroupId1 = row[i] / 2;
            int coupleGroupId2 = row[i + 1] / 2;
            union(coupleGroupId1, coupleGroupId2);
        }
        return coupleGroupNum - count;
    }

    private void union(int i, int j) {
        int rooti = find(i);
        int rootj = find(j);
        if (rooti == rootj) {
            return;
        }
        fa[rooti] = rootj;
        count--;
    }

    private int find(int i) {
        if (fa[i] == i) {
            return i;
        }
        fa[i] = find(fa[i]);
        return fa[i];
    }

    public static void main(String[] args) {
        SolutionUnionFind sol = new SolutionUnionFind();
        int[] row = {0,4,5,2,3,1};
        System.out.println("test: " + sol.minSwapsCouples(row));
    }
}