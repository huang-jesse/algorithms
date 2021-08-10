class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;
        int ans = 0;
        int start = 0;
        int step = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            int pre = nums[i-1];
            int cur = nums[i];
            int curStep = cur - pre;
            if (curStep != step) {
                ans += calculateNumberOfSlices(start, i-1);
                start = i-1;
                step = curStep;
                continue;
            } else {
                // curStep == step
                if (i == n-1) {
                    ans += calculateNumberOfSlices(start, i);
                    break;
                }
            }
        }
        return ans;
    }

    private static int calculateNumberOfSlices(int start, int end) {
        int distance = end - start + 1;
        if (distance > 2) {
            return (distance-2)*(distance-1)/2;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,8,9,10};
        System.out.println("test: " + sol.numberOfArithmeticSlices(nums));
    }
}