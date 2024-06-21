class Solution {
    private static final int MOD = (int)1e9 + 7;
    private static final int MAX = (int)1e5;
    // 1-indexed BinaryIndexedTree
    private int[] nums;
    private int n;

    public int createSortedArray(int[] instructions) {
        this.nums = new int[MAX + 1];
        this.n = nums.length;
        int ans = 0;
        for (int num : instructions) {
            int left = query(num - 1);
            int right = queryRange(num + 1, MAX);
            ans = (ans + Math.min(left, right)) % MOD;

            update(num, 1);
        }
        return ans;
    }

    private void update(int x, int val) {
        while (x < this.n) {
            nums[x] += val;
            x = x + lowbit(x);
        }
    }

    private int queryRange(int l, int r) {
        return query(r) - query(l - 1);
    }

    private int query(int x) {
        int sum = 0;
        while (x > 0) {
            sum += nums[x];
            x = x - lowbit(x);
        }
        return sum;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] instructions = {1,3,3,3,2,4,2,1,2};
        System.out.println("test: " + sol.createSortedArray(instructions));
    }
}