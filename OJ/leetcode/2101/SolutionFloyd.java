import java.util.Arrays;

/**
 * 使用 Floyd 算法（修改最短路算法）获取所有的顶点与顶点之间的是否可达（用 Bitset 进行压缩优化）
 */
class SolutionFloyd {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        Bitset[] reachableSets = new Bitset[n];
        Arrays.setAll(reachableSets, o -> new Bitset(n));
        for (int i = 0; i < n; i++) {
            int x1 = bombs[i][0];
            int y1 = bombs[i][1];
            long rQuadratic = (long)bombs[i][2] * bombs[i][2];
            for (int j = 0; j < n; j++) {
                int x2 = bombs[j][0];
                int y2 = bombs[j][1];
                long xDiff = x1 - x2;
                long yDiff = y1 - y2;
                long dsQuadratic = xDiff * xDiff + yDiff * yDiff;
                if (rQuadratic >= dsQuadratic) {
                    // reachable
                    reachableSets[i].set(j);
                }
            }
        }
        // Floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // represent f[i][j] and f[i][k]
                Bitset ikSet = reachableSets[i];
                // iteration for j
                if (ikSet.has(k)) {
                    // is k reachable ?
                    Bitset kjSet = reachableSets[k];
                    // represent f[i][j] = f[i][k] or f[k][j]
                    ikSet.or(kjSet);
                }
            }
        }
        int ans = 0;
        for (Bitset iSet : reachableSets) {
            ans = Math.max(ans, iSet.count());
        }
        return ans;
    }
    // 相比 java.util.Bitset，去掉了一些边界检查、assert 等
    static class Bitset {
        private static final int W = Long.SIZE;

        private final long[] bits;

        public Bitset(int n) {
            bits = new long[(n + W - 1) / W]; // 需要 ceil(n/W) 个 W 位整数
        }

        public void set(int p) {
            bits[p / W] |= 1L << (p % W);
        }

        public boolean has(int p) {
            return (bits[p / W] & (1L << (p % W))) != 0;
        }

        public void or(Bitset other) {
            for (int i = 0; i < bits.length; i++) {
                bits[i] |= other.bits[i];
            }
        }

        public int count() {
            int c = 0;
            for (long x : bits) {
                c += Long.bitCount(x);
            }
            return c;
        }
    }

    public static void main(String[] args) {
        SolutionFloyd sol = new SolutionFloyd();
        int[][] bombs = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        System.out.println("test: " + sol.maximumDetonation(bombs));
    }
}