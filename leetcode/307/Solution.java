class NumArray {
    private int[] nums;
    private BinaryIndexedTree BIT;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.BIT = new BinaryIndexedTree(nums);
    }

    public void update(int index, int val) {
        this.BIT.add(index + 1, val - this.nums[index]);
        this.nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return this.BIT.getRangeSum(left + 1, right + 1);
    }

    public class BinaryIndexedTree {
        private int n;
        private int[] c;

        public BinaryIndexedTree(int n) {
            this.n = n;
            this.c = new int[n + 1];// 1-indexed
        }

        public BinaryIndexedTree(int[] a) {
            this(a.length);
            this.init(a);
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        public void init(int[] a) {
            for (int i = 1; i <= n; i++) {
                c[i] += a[i - 1];
                int j = i + lowbit(i);
                if (j <= n) {
                    c[j] += c[i];
                }
            }
        }

        public void add(int x, int p) {
            while (x <= n) {
                c[x] += p;
                x = x + lowbit(x);
            }
        }

        public int getSum(int x) {
            int res = 0;
            while (x > 0) {
                res += c[x];
                x = x - lowbit(x);
            }
            return res;
        }

        public int getRangeSum(int l, int r) {
            return getSum(r) - getSum(l - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {7,2,7,2,0};
        NumArray sol = new NumArray(nums);
        sol.update(4,6);
        sol.update(0,2);
        sol.update(0,9);
        System.out.println(sol.sumRange(4, 4)); // 6
        sol.update(3,8);
        System.out.println(sol.sumRange(0, 4)); // 32
    }
}