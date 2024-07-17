import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    /**
     * 区间总和线段树，懒标记版本
     */
    class SegmentTreeLazyMark {
        class Node {
            private int s;
            private int t;
            private int val;
            public Node(int s, int t) {
                this.s = s;
                this.t = t;
            }
        }
        private Node[] d;
        private int[] a;
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
                // 这里是二进制取反，所以取反后的和=总数-原和
                d[p].val = (t - s + 1) - d[p].val;
                m[p] += c;
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
        public int getSum(int l, int r, int p) {
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

            int sum = 0;
            // 如果左子节点代表的区间 [s, m] 与查询区间有交集, 则递归查询左子节点
            if (l <= mid) {
                sum += getSum(l, r, p * 2);
            }
            // 如果右子节点代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右子节点
            if (r > mid) {
                sum += getSum(l, r, (p * 2) + 1);
            }
            return sum;
        }

        /**
         * 更新当前节点的值为左右子节点的值之和
         * @param p
         */
        private void pushup(int p) {
            d[p].val = d[p * 2].val + d[p * 2 + 1].val;
        }

        /**
         * 下放懒惰标记
         * @param p
         */
        private void pushdown(int p) {
            int s = d[p].s;
            int t = d[p].t;
            int mid = s + ((t - s) >> 1);
            int mod = m[p] % 2;
            // 清空当前节点的标记
            m[p] = 0;
            if (mod == 0) {
                // 说明偶数次取反，则不需要处理
                return;
            }
            // 左子节点
            // 二进制取反
            d[p * 2].val = (mid - s + 1) - d[p * 2].val;
            // 将标记下传给子节点
            m[p * 2] += mod;
            // 右子节点
            // 二进制取反
            d[p * 2 + 1].val = (t - mid) - d[p * 2 + 1].val;
            // 将标记下传给子节点
            m[p * 2 + 1] += mod;
        }
    }

    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        SegmentTreeLazyMark num1ST = new SegmentTreeLazyMark(nums1);
        long sum = Arrays.stream(nums2).mapToLong(num -> (long)num).sum();
        int n = nums1.length;
        List<Long> resList = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                num1ST.update(query[1], query[2], 1, 1);
            } else if (query[0] == 2) {
                int count = num1ST.getSum(0, n - 1, 1);
                int p = query[1];
                sum += (long)count * p;
            } else {
                // query[0] == 3
                resList.add(sum);
            }
        }
        return resList.stream().mapToLong(l -> l).toArray();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums1 = {1,0,1};
        // int[] nums2 = {0,0,0};
        // int[][] queries = {{1,1,1},{2,1,0},{3,0,0}};
        int[] nums1 = {1,0,1};
        int[] nums2 = {44,28,35};
        int[][] queries = {{1,0,1},{2,10,0},{2,2,0},{2,7,0},{3,0,0},{3,0,0},{1,2,2},{1,1,2},{2,1,0},{1,0,2},{1,2,2},{1,0,2},{3,0,0},{1,1,2},{3,0,0},{1,0,1},{2,21,0},{1,0,1},{2,26,0},{1,1,1}};
        System.out.println("test: " + Arrays.stream(sol.handleQuery(nums1, nums2, queries)).boxed().collect(Collectors.toList()));
    }
}