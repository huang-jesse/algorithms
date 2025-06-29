class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n= nums.length;
        int[] diffs = new int[n + 1];
        for (int[] query : queries) {
            diffs[query[0]]++;
            diffs[query[1] + 1]--;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diffs[i];
            if (sum < nums[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,0,1};
        int[][] queries = {{0,2}};
        System.out.println("test: " + sol.isZeroArray(nums, queries));
    }
}