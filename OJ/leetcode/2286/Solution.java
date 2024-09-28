import java.util.Arrays;

class BookMyShow {
    private static final int[] EMPTY_ARRAY = new int[]{};

    private int n;
    private int m;
    private int[] maxs;
    private long[] sums;
    private int minRow;
    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        this.maxs = new int[4 * n];
        this.sums = new long[4 * n];
        // 建树
        build(0, n - 1, 1);
        this.minRow = 0;
    }

    /**
     * 递归构建线段树
     * @param s 当前节点左边界
     * @param t 当前节点右边界
     * @param p 当前节点编号
     */
    private void build(int s, int t, int p) {
        // 对 [s,t] 区间建立线段树,当前节点的编号为 p
        if (s == t) {
            maxs[p] = m;
            sums[p] = m;
            return;
        }
        // 移位运算符的优先级小于加减法，所以加上括号
        int mid = s + ((t - s) >> 1);
        // 递归对左右区间建树
        build(s, mid, p * 2);
        build(mid + 1, t, (p * 2) + 1);
        sums[p] = sums[p * 2] + sums[(p * 2) + 1];
        maxs[p] = m;
    }

    public void update(int i, int delta) {
        update(0, n - 1, 1, i, delta);
    }

    private void update(int s, int t, int p, int i, int delta) {
        if (s == t) {
            maxs[p] += delta;
            sums[p] += delta;
            return;
        }
        int mid = s + ((t - s) >> 1);
        if (i <= mid) {
            update(s, mid, p * 2, i, delta);
        } else {
            update(mid + 1, t, p * 2 + 1, i, delta);
        }
        maxs[p] = Math.max(maxs[p * 2], maxs[p * 2 + 1]);
        sums[p] = sums[p * 2] + sums[p * 2 + 1];
    }

    public int getMax(int l, int r) {
        return getMax(l, r, 0, n - 1, 1);
    }

    private int getMax(int l, int r, int s, int t, int p) {
        // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
        if (l <= s && t <= r) {
            // 当前区间为查询区间的子集时直接返回当前区间的和
            return maxs[p];
        }
        int mid = s + ((t - s) >> 1);
        int max = 0;
        // 如果左儿子代表的区间 [s, m] 与查询区间有交集, 则递归查询左儿子
        if (l <= mid) {
            max = Math.max(max, getMax(l, r, s, mid, p * 2));
        }
        // 如果右儿子代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右儿子
        if (r > mid) {
            max = Math.max(max, getMax(l, r, mid + 1, t, (p * 2) + 1));
        }
        return max;
    }

    public long getSum(int l, int r) {
        return getSum(l, r, 0, n - 1, 1);
    }

    private long getSum(int l, int r, int s, int t, int p) {
        // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
        if (l <= s && t <= r) {
            // 当前区间为查询区间的子集时直接返回当前区间的和
            return sums[p];
        }
        int mid = s + ((t - s) >> 1);
        long sum = 0;
        // 如果左儿子代表的区间 [s, m] 与查询区间有交集, 则递归查询左儿子
        if (l <= mid) {
            sum += getSum(l, r, s, mid, p * 2);
        }
        // 如果右儿子代表的区间 [m + 1, t] 与查询区间有交集, 则递归查询右儿子
        if (r > mid) {
            sum += getSum(l, r, mid + 1, t, (p * 2) + 1);
        }
        return sum;
    }

    public int[] gather(int k, int maxRow) {
        if (getMax(minRow, maxRow) < k) return EMPTY_ARRAY;
        int l = minRow;
        int r = maxRow;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (getMax(0, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int row = l;
        int numOfSeat = getMax(row, row);
        int col = m - numOfSeat;

        update(row, -k);

        return new int[]{row, col};
    }

    public boolean scatter(int k, int maxRow) {
        if (getSum(minRow, maxRow) < k) return false;

        while (k > 0) {
            int seatNum = (int)getSum(minRow, minRow);
            if (seatNum == 0) {
                minRow++;
                continue;
            }
            if (seatNum < k) {
                update(minRow, -seatNum);

                k -= seatNum;
                minRow++;
            } else {
                update(minRow, -k);

                k = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 2;
        int m = 5;
        BookMyShow obj = new BookMyShow(n, m);
        int k = 4;
        int maxRow = 1;
        int[] param_1 = obj.gather(k,maxRow);
        System.out.println("param_1: " + Arrays.toString(param_1)); // res: [0,0]
        k = 2;
        maxRow = 0;
        int[] param_2 = obj.gather(k,maxRow);
        System.out.println("param_2: " + Arrays.toString(param_2)); // res: []
        k = 5;
        maxRow = 1;
        boolean param_3 = obj.scatter(k,maxRow);
        System.out.println("param_3: " + param_3); // res: true
        k = 5;
        maxRow = 1;
        boolean param_4 = obj.scatter(k,maxRow);
        System.out.println("param_4: " + param_4); // res: false

        // int n = 5;
        // int m = 9;
        // BookMyShow obj = new BookMyShow(n, m);
        // int k = 10;
        // int maxRow = 1;
        // int[] param_1 = obj.gather(k,maxRow);
        // System.out.println("param_1: " + Arrays.toString(param_1)); // res: []
        // k = 3;
        // maxRow = 3;
        // boolean param_2 = obj.scatter(k,maxRow);
        // System.out.println("param_2: " + param_2); // res: true
        // k = 9;
        // maxRow = 1;
        // int[] param_3 = obj.gather(k,maxRow);
        // System.out.println("param_3: " + Arrays.toString(param_3)); // res: [1,0]
        // k = 2;
        // maxRow = 0;
        // int[] param_4 = obj.gather(k,maxRow);
        // System.out.println("param_4: " + Arrays.toString(param_4)); // res: [0,3]
    }
}