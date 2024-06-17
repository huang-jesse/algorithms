import java.util.ArrayList;
import java.util.List;


class MajorityChecker {
    private static final int MAX = (int)2e4;
    private final SegmentTree st;
    private final List<Integer>[] indexesArr;

    public MajorityChecker(int[] arr) {
        this.st = new SegmentTree(arr);
        this.indexesArr = (ArrayList<Integer>[])new ArrayList[MAX + 1];
        for (int i = 0; i <= MAX; i++) indexesArr[i] = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indexesArr[arr[i]].add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        SegmentTree.Node node = this.st.query(left, right, 1);
        int major = node.major;
        List<Integer> indexes = this.indexesArr[major];
        int low = binarySearchLowerBound(indexes, left);
        int high = binarySearchUpperBound(indexes, right);
        if (low <= high && high - low + 1 >= threshold) {
            return major;
        } else {
            return -1;
        }
    }

    private int binarySearchLowerBound(List<Integer> indexes, int target) {
        if (indexes.get(indexes.size() - 1) < target) {
            return -1;
        }
        int low = 0;
        int high = indexes.size() - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (indexes.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int binarySearchUpperBound(List<Integer> indexes, int target) {
        if (indexes.get(0) > target) {
            return -1;
        }
        int low = 0;
        int high = indexes.size() - 1;
        while (low < high) {
            int mid = low + ((high - low + 1) >> 1);
            if (indexes.get(mid) <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    static class SegmentTree {
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
            private int major;
            private int cnt;
            public Node(int s, int t, int major, int cnt) {
                this.s = s;
                this.t = t;
                this.major = major;
                this.cnt = cnt;
            }
            public Node merge(Node o) {
                if (this.major == o.major) {
                    this.cnt += o.cnt;
                } else if (this.cnt > o.cnt) {
                    this.cnt -= o.cnt;
                } else {
                    this.major = o.major;
                    this.cnt = o.cnt - this.cnt;
                }
                return this;
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
        public SegmentTree(int[] a) {
            int n = a.length;
            this.a = a;
            this.d = new Node[4 * n];
            build(0, n-1, 1);
        }
        /**
         * 递归构建线段树
         * @param s 当前节点左边界
         * @param t 当前节点右边界
         * @param p 当前节点编号
         */
        private void build(int s, int t, int p) {
            // 对 [s,t] 区间建立线段树,当前节点的编号为 p
            d[p] = new Node(s, t, -1, 0);
            if (s == t) {
                d[p].major = a[s];
                d[p].cnt = 1;
                return;
            }
            // 移位运算符的优先级小于加减法，所以加上括号
            int mid = s + ((t - s) >> 1);
            // 递归对左右区间建树
            build(s, mid, p * 2);
            build(mid + 1, t, (p * 2) + 1);
            d[p].merge(d[p * 2]).merge(d[(p * 2) + 1]);
        }
        /**
         * 获取区间 [l,r] 的总和
         * @param l 查询区间左边界
         * @param r 查询区间右边界
         * @param s 当前节点左边界
         * @param t 当前节点右边界
         * @param p 当前节点编号
         * @return
         */
        public Node query(int l, int r, int p) {
            int s = d[p].s;
            int t = d[p].t;
            // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
            if (l <= s && t <= r) {
                // 当前区间为查询区间的子集时直接返回当前区间的和
                return d[p];
            }
            int mid = s + ((t - s) >> 1);
            Node sum = new Node(s, t, -1, 0);
            // 如果左儿子代表的区间 [s, m] 与查询区间有交集, 则递归查询左儿子
            if (l <= mid) {
                sum.merge(query(l, r, p * 2));
            }
            // 如果右儿子代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右儿子
            if (r > mid) {
                sum.merge(query(l, r, (p * 2) + 1));
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,2,1,2,1,2,2,1,1,2};
        MajorityChecker obj = new MajorityChecker(arr);
        int left = 2;
        int right = 5;
        int threshold = 4;
        int param_1 = obj.query(left,right,threshold); // 1
        System.out.println("test: " + param_1);
    }
}