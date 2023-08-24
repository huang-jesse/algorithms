class Solution {
    private int n;
    private int[] arr;
    private Integer[] memo;
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        n = nums.length-1;
        arr = nums;

        // 0 ~ n-1
        memo = new Integer[n];
        int beginZero = Math.max(this.recursiveSum(0, n-1), this.recursiveSum(1, n-1));
        // 1 ~ n
        memo = new Integer[n];
        int beginOne = Math.max(this.recursiveSum(1, n), this.recursiveSum(2, n));

        return Math.max(beginZero, beginOne);
    }

    private int recursiveSum(int start, int end) {
        if (start > end) return 0;
        if (start == end) return arr[start];

        if (memo[start] != null) {
            return memo[start];
        }

        int step2 = arr[start] + this.recursiveSum(start+2, end);
        int step3 = arr[start] + this.recursiveSum(start+3, end);

        memo[start] = Math.max(step2, step3);
        return memo[start];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,2,3,3};
        SolutionDP sol = new SolutionDP();
        System.out.println("rob: "+ sol.rob(nums));
    }
}