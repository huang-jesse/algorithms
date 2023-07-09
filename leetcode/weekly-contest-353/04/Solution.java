class Solution {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] decreaseArr = new int[n];
        long[] preSumDecreaseArr = new long[n + 1];
        preSumDecreaseArr[0] = 0;
        for (int i = 0; i < n; i++) {
            int currentNum = nums[i];
            // The sum of decreaseArr[i-k+1] + ... + decreaseArr[i-1]
            long preDecreaseSum = getPreSum(preSumDecreaseArr, i - k + 1, i - 1);
            // Decrease the sum of decreaseArr[i-k+1 ~ i-1]
            currentNum -= preDecreaseSum;
            if (currentNum < 0) {
                return false;
            }
            // The range of nums[n-1-k+2] ~ nums[n-1], cannot decrease once again.
            if (i + k - 1 < n) {
                decreaseArr[i] = currentNum;
                currentNum = 0;
            }
            preSumDecreaseArr[i + 1] = preSumDecreaseArr[i] + decreaseArr[i];
            if (currentNum != 0) {
                return false;
            }
        }
        return true;
    }

    private long getPreSum(long[] preSumDecreaseArr, int l, int r) {
        l = Math.max(0, l);
        if (r < 0) {
            return 0L;
        }
        return preSumDecreaseArr[r + 1] - preSumDecreaseArr[l + 1 - 1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,3,3,2,1,1,0};
        int k = 3;
        // int[] nums = {2,2,3,1,1,0};
        // int k = 3;
        System.out.println("test: " + sol.checkArray(nums, k));
    }
}