import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> ans = new ArrayList<>(n);
        int size = 0;
        int[] arr = new int[2 * n + 1];
        for(int i = 0; i < n; i++){
            arr[i * 2] = positions[i][0];
            arr[i * 2 + 1] = positions[i][0] + positions[i][1];
            size = Math.max(size, arr[i * 2 + 1]);
        }
        Map<Integer, Integer> map = discrete(arr);
        int[] a = new int[map.get(size) + 1];
        SegmentTreeLazyMark st = new SegmentTreeLazyMark(a);
        int preHeight = 0;
        for (int[] position : positions) {
            int left = position[0];
            int sideLength = position[1];
            int l = map.get(left);
            int r = map.get(left + sideLength) - 1;
            int curHeight = st.getMax(l, r, 1) + sideLength;
            st.update(l, r, curHeight, 1);

            preHeight = Math.max(curHeight, preHeight);
            ans.add(preHeight);
        }
        return ans;
    }

    private Map<Integer, Integer> discrete(int[] nums){ // 紧离散
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int idx = 0;
        for(int num : list) map.put(num, idx++);
        return map;
    }

    /**
     * 区间总和线段树，懒标记版本
     */
    class SegmentTreeLazyMark {
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
                d[p].val = Math.max(d[p].val, c);
                m[p] = Math.max(m[p], c);
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
         * 获取区间 [l,r] 的最大值
         * @param l 查询区间左边界
         * @param r 查询区间右边界
         * @param p 当前节点编号
         * @return
         */
        public int getMax(int l, int r, int p) {
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

            int max = 0;
            // 如果左子节点代表的区间 [s, m] 与查询区间有交集, 则递归查询左子节点
            if (l <= mid) {
                max = Math.max(max, getMax(l, r, p * 2));
            }
            // 如果右子节点代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右子节点
            if (r > mid) {
                max = Math.max(max, getMax(l, r, (p * 2) + 1));
            }
            return max;
        }

        /**
         * 更新当前节点的值为左右子节点的值之和
         * @param p
         */
        private void pushup(int p) {
            d[p].val = Math.max(d[p * 2].val, d[p * 2 + 1].val);
        }

        /**
         * 下放懒惰标记
         * @param p
         */
        private void pushdown(int p) {
            // int s = d[p].s;
            // int t = d[p].t;
            // 左子节点
            d[p * 2].val = Math.max(d[p * 2].val, m[p]);
            // 将标记下传给子节点
            m[p * 2] = Math.max(m[p * 2], m[p]);
            // 右子节点
            d[p * 2 + 1].val = Math.max(d[p * 2 + 1].val, m[p]);
            // 将标记下传给子节点
            m[p * 2 + 1] = Math.max(m[p * 2 + 1], m[p]);
            // 清空当前节点的标记
            m[p] = 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[][] positions = {{1,2},{2,3},{6,1}}; // {2,5,5}
        int[][] positions = {{2,1},{2,9},{1,8}}; // {1,10,18}
        System.out.println("test: " + sol.fallingSquares(positions));
    }
}