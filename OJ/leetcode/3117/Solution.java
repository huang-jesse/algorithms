import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        SparseTable st = new SparseTable(nums);
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        int[] aux = new int[n + 1];
        Arrays.fill(aux, INF);
        SegmentTreeLazyMark pre = new SegmentTreeLazyMark(aux);
        pre.update(0, 0, 0, 1);
        for (int j = 1; j <= m; j++) {
            SegmentTreeLazyMark seg = new SegmentTreeLazyMark(aux);
            for (int i = 1; i <= n; i++) {
                int[] leftToRight = binarySearch(st, i - 1, andValues[j - 1]);
                if (leftToRight != null) {
                    int l = leftToRight[0];
                    int r = leftToRight[1];
                    int min = pre.getMin(l, r, 1);
                    dp[i][j] = Math.min(dp[i][j], min + nums[i - 1]);
                    // update
                    seg.update(i, i, dp[i][j], 1);
                }
            }
            pre = seg;
        }
        return dp[n][m] < INF ? dp[n][m] : -1;
    }

    private int[] binarySearch(SparseTable st, int i, int target) {
        if (st.query(i, i) < target || st.query(0, i) > target) return null;
        // left boundary search
        int l = 0;
        int r = i;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (st.query(mid, i) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int left = l;
        if (st.query(left, i) != target) return null;

        // right boundary search
        l = 0;
        r = i;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (st.query(mid, i) <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int right = l;
        return new int[]{left, right};
    }

    static class SparseTable {
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
                    f[i][j] = f[i][j - 1] & f[i + (1 << (j - 1))][j - 1];
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
            return f[l][s] & f[r - (1 << s) + 1][s];
        }
    }

    /**
     * 区间总和线段树，懒标记版本
     */
    static class SegmentTreeLazyMark {
        /**
         * 节点对象
         */
        class Node {
            /**
             * 当前节点左边界
             */
            private int s;
            /**
             * 当前节点右边界
             */
            private int t;
            private int val;
            public Node(int s, int t) {
                this.s = s;
                this.t = t;
            }
        }

        /**
         * 线段树节点数组
         */
        private Node[] d;
        /**
         * 原数组
         */
        private int[] a;
        /**
         * 懒标记数组
         */
        private int[] m;

        public SegmentTreeLazyMark(int[] a) {
            int n = a.length;
            this.a = a;
            this.d = new Node[4 * n];
            this.m = new int[4 * n];
            build(0, n-1, 1);
        }

        /**
         * 递归构建线段树
         * @param s 当前节点左边界
         * @param t 当前节点右边界
         * @param p 当前节点编号
         */
        public void build(int s, int t, int p) {
            // 对 [s,t] 区间建立线段树,当前节点的编号为 p
            d[p] = new Node(s, t);
            if (s == t) {
                d[p].val = a[s];
                return;
            }
            // 移位运算符的优先级小于加减法，所以加上括号
            int mid = s + ((t - s) >> 1);
            // 递归对左右区间建树
            build(s, mid, p * 2);
            build(mid + 1, t, (p * 2) + 1);
            // 合计
            pushup(p);
        }

        /**
         * 针对区间 [l,r] 的每一项加上某值 c （懒标记）
         * @param l 修改区间左边界
         * @param r 修改区间右边界
         * @param c 被修改的元素的变化量
         * @param p 当前节点编号
         * @return
         */
        public void update(int l, int r, int c, int p) {
            int s = d[p].s;
            int t = d[p].t;
            // [l, r] 为修改区间, c 为被修改的元素的变化量, [s, t] 为当前节点包含的区间,
            // p为当前节点的编号
            if (l <= s && t <= r) {
                // 当前区间为修改区间的子集时直接修改当前节点的值，然后打标记，结束修改
                d[p].val = Math.min(d[p].val, c);
                m[p] = Math.min(m[p], c);
                return;
            }
            int mid = s + ((t - s) >> 1);
            // 如果当前节点的懒标记非空，则更新当前节点两个子节点的值和懒标记值
            if (m[p] != 0) {
                pushdown(p);
            }

            // 如果左子节点代表的区间 [s, m] 与修改区间有交集，更新左子节点
            if (l <= mid) {
                update(l, r, c, p * 2);
            }
            // 如果右子节点代表的区间 [s, m] 与修改区间有交集，更新右子节点
            if (r > mid) {
                update(l, r, c, p * 2 + 1);
            }
            // 合计
            pushup(p);
        }

        /**
         * 获取区间 [l,r] 的总和
         * @param l 查询区间左边界
         * @param r 查询区间右边界
         * @param p 当前节点编号
         * @return
         */
        public int getMin(int l, int r, int p) {
            int s = d[p].s;
            int t = d[p].t;
            // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
            if (l <= s && t <= r) {
                // 当前区间为查询区间的子集时直接返回当前区间的和
                return d[p].val;
            }

            int mid = s + ((t - s) >> 1);
            // 如果当前节点的懒标记非空，则更新当前节点两个子节点的值和懒标记值
            if (m[p] != 0) {
                pushdown(p);
            }

            int min = Integer.MAX_VALUE;
            // 如果左子节点代表的区间 [s, m] 与查询区间有交集, 则递归查询左子节点
            if (l <= mid) {
                min = Math.min(getMin(l, r, p * 2), min);
            }
            // 如果右子节点代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右子节点
            if (r > mid) {
                min = Math.min(getMin(l, r, (p * 2) + 1), min);
            }
            return min;
        }

        /**
         * 更新当前节点的值为左右子节点的值之和
         * @param p
         */
        private void pushup(int p) {
            d[p].val = Math.min(d[p * 2].val, d[p * 2 + 1].val);
        }

        /**
         * 下放懒惰标记
         * @param p
         */
        private void pushdown(int p) {
            int s = d[p].s;
            int t = d[p].t;
            // int mid = s + ((t - s) >> 1);
            // 左子节点
            d[p * 2].val = Math.min(d[p * 2].val, m[p]);
            // 将标记下传给子节点
            m[p * 2] = Math.min(m[p * 2], m[p]);
            // 右子节点
            d[p * 2 + 1].val = Math.min(d[p * 2 + 1].val, m[p]);
            // 将标记下传给子节点
            m[p * 2 + 1] = Math.min(m[p * 2 + 1], m[p]);
            // 清空当前节点的标记
            m[p] = 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {2,3,5,7,7,7,5};
        // int[] andValues = {0,7,5};
        int[] nums = {1,4,3,3,2};
        int[] andValues = {0,3,3,2};
        System.out.println("test: " + sol.minimumValueSum(nums, andValues));
    }
}