class LUPrefix {
    private int n;
    private int[] fa;

    public LUPrefix(int n) {
        this.n = n;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
    }

    public void upload(int video) {
        merge(video, video - 1);
    }

    public int longest() {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            int midRoot = find(mid);
            if (midRoot == 0) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
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
     * merge i to j
     * @param args
     */
    private void merge(int i, int j) {
        fa[find(i)] = find(fa[j]);
    }

    public static void main(String[] args) {
        LUPrefix sol = new LUPrefix(4);
        sol.upload(3);
        System.out.println("test: " + sol.longest());//0
        sol.upload(1);
        System.out.println("test: " + sol.longest());//1
        sol.upload(2);
        System.out.println("test: " + sol.longest());//3
    }
}