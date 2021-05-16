class Solution {
    public int subsetXORSum(int[] nums) {
        int ans = subSum(0, 0, nums);
        return ans;
    }

    private int subSum(int preSubSum, int index, int[] nums) {
        if (index >= nums.length) return 0;
        int sum = 0;
        for (int i = index; i < nums.length; i++) {
            int tempSum = preSubSum ^ nums[i];
            sum += tempSum;
            int subSetsum = subSum(tempSum, i+1, nums);
            sum += subSetsum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{3,4,5,6,7,8};
        System.out.println("test: " + sol.subsetXORSum(nums));
    }
}