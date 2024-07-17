class SolutionDivideConquer {
    public class Status {
        public int lSum, rSum, maxSum, rangeSum;
        public Status(int lSum, int rSum, int maxSum, int rangeSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.maxSum = maxSum;
            this.rangeSum = rangeSum;
        }
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        return getInfo(nums, 0, n - 1).maxSum;
    }

    private Status getInfo(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = l + ((r - l) >> 1);
        Status lStatus = getInfo(nums, l, m);
        Status rStatus = getInfo(nums, m + 1, r);
        return pushUp(lStatus, rStatus);
    }

    private Status pushUp(Status lStatus, Status rStatus) {
        int lSum = Math.max(lStatus.lSum, lStatus.rangeSum + rStatus.lSum);
        int rSum = Math.max(rStatus.rSum, rStatus.rangeSum + lStatus.rSum);
        int maxSum = Math.max(Math.max(lStatus.maxSum, rStatus.maxSum), lStatus.rSum + rStatus.lSum);
        int rangeSum = lStatus.rangeSum + rStatus.rangeSum;
        return new Status(lSum, rSum, maxSum, rangeSum);
    }

    public static void main(String[] args) {
        SolutionDivideConquer sol = new SolutionDivideConquer();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("test: " + sol.maxSubArray(nums));
    }
}