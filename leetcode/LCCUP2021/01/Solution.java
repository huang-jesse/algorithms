class Solution {
    public int purchasePlans(int[] nums, int target) {
        long count = 0;
        long base = 1000000007;
        int[] pre = new int[100001];
        for (int i : nums) {
            pre[i]++;
        }
        // Accumulate that current index is num of less and equals current value
        for (int i = 1; i < 100001; i++) {
            pre[i] += pre[i-1];
        }

        // Accumulate count
        for (int i : nums) {
            if (i <= target) {
                count += pre[target-i];
            }
        }
        // exclude self count
        for (int i : nums) {
            if (2*i <= target) {
                count--;
            }
        }
        // exclude verbosity count for both each others
        count = count / 2;

        return (int)(count % base);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,2,1,9};
        // int[] nums = new Random(2).ints(0, 1000).limit(10000).toArray();
        int target = 10;
        Solution sol = new Solution();
        int num = sol.purchasePlans(nums, target);
        System.out.println("plans: "+num);
    }
}