public class SparseTable {
    private int n;
    private int[][] f;
    private int[] log2;
    public SparseTable(int[] nums) {
        this.n = nums.length;
        this.log2 = preProcessLog2(n);
        this.f = new int[n][log2[n] + 1];
        for (int i = 0; i < n; i++) {
            f[i][0] = nums[i];
        }
        for (int j = 1; j <= log2[n]; j++) {
            for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    private int[] preProcessLog2(int n) {
        int[] log2 = new int[n + 1];
        log2[1] = 0;
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }
        return log2;
    }

    public int query(int l, int r) {
        int s = log2[r - l + 1];
        return Math.max(f[l][s], f[r - (1 << s) + 1][s]);
    }

    public static void main(String[] args) {
        int[] nums = {0,13,14,4,13,1,5,7};
        SparseTable st = new SparseTable(nums);
        System.out.println(st.query(2, 7));// 14
        System.out.println(st.query(0, 1));// 13
        System.out.println(st.query(0, 0));// 0
        System.out.println(st.query(3, 3));// 4
        System.out.println(st.query(3, 4));// 13
    }
}