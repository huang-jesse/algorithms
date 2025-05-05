class Solution {
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
            return x & -x;
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

        public void add(int x, int delta) {
            while (x <= n) {
                c[x] += delta;
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
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 将 nums1[i] 映射到 p[nums[i]]
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[nums1[i]] = i;
        }

        long ans = 0;
        // 1-index
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        for (int i = 0; i < n - 1; i++) {
            int y = p[nums2[i]];
            // 获取 "y - 1" 的前缀和
            int less = bit.getSum(y);
            ans += (long) less * (n - 1 - y - (i - less));
            // 1-index 所以 y + 1
            bit.add(y + 1, 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {4,0,1,3,2};
        int[] nums2 = {4,1,0,2,3}; //ans = 4
        System.out.println("test: " + sol.goodTriplets(nums1, nums2));
    }
}