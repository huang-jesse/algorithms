class LUPrefix {
    private int n;
    private int[] fa;
    public LUPrefix(int n) {
        this.n = n;
        this.fa = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
            fa[i] = i;
        }
    }

    public void upload(int video) {
        merge(video, video + 1);
    }

    public int longest() {
        return find(1) - 1;
    }

    /**
    * find the root of i, by compression path
    */
    private int find(int i) {
        if (fa[i] == i) {
            return i;
        } else {
            fa[i] = find(fa[i]);
            return fa[i];
        }
    }

    /**
     * merge i to j
     */
    private void merge(int i, int j) {
        fa[find(i)] = find(j);
    }

    public static void main(String[] args) {
        int n = 4;
        LUPrefix sol = new LUPrefix(n);
        sol.upload(3);
        System.out.printf("logest: %d\n", sol.longest());// 0
        sol.upload(1);
        System.out.printf("logest: %d\n", sol.longest());// 1
        sol.upload(2);
        System.out.printf("logest: %d\n", sol.longest());// 3
    }
}