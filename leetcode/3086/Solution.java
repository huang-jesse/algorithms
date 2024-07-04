class Solution {
    private static final long INF = Long.MAX_VALUE;
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] prefixCounter = new int[n + 1];
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixCounter[i + 1] = prefixCounter[i] + nums[i];
            prefixSum[i + 1] = prefixSum[i] + nums[i] * i;
        }
        long[] suffixSum = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i] * (n - 1 - i);
        }

        long ans = INF;
        for (int i = 0; i < n; i++) {
            long res = 0;
            int remaink = k - nums[i];
            int prioritySelect = 0;
            if (remaink > 0 && i > 0) {
                remaink -= nums[i - 1];
                res += nums[i - 1];
                prioritySelect += nums[i - 1];
            }
            if (remaink > 0 && i < n - 1) {
                remaink -= nums[i + 1];
                res += nums[i + 1];
                prioritySelect += nums[i + 1];
            }
            // perform maxChanges
            if (remaink > 0) {
                int performTimes = Math.min(remaink, maxChanges);
                res += performTimes * 2;
                remaink -= performTimes;
            }
            // perform adjacent swap
            if (remaink > 0) {
                // binary search for left and right index
                int target = remaink + prioritySelect;
                int[] leftAndRight = searchLeftAndRightBoundary(prefixCounter, i, target);
                int left = leftAndRight[0];
                long numOfOnes = prefixCounter[i] - prefixCounter[left];
                res += suffixSum[left] - suffixSum[i] - numOfOnes * (n - 1 - i);

                int right = leftAndRight[1];
                numOfOnes = prefixCounter[right + 1] - prefixCounter[i + 1];
                res += prefixSum[right + 1] - prefixSum[i + 1] - numOfOnes * i;

                res -= prioritySelect;
            }
            ans = Math.min(res, ans);
        }
        return ans;
    }

    private int[] searchLeftAndRightBoundary(int[] prefixCounter, int index, int target) {
        int n = prefixCounter.length - 1;
        int leftLen = index;
        int rightLen = n - 1 - index;
        int maxLen = Math.max(leftLen, rightLen);
        int low = 1;
        int high = maxLen;
        // find least leftAndRight that equal or greater than target
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            int leftIndex = Math.max(index - mid, 0);
            int rightIndex = Math.min(index + mid, n - 1);
            int leftVal = prefixCounter[index] - prefixCounter[leftIndex];
            int rightVal = prefixCounter[rightIndex + 1] - prefixCounter[index + 1];
            if (leftVal + rightVal >= target) {
                // equal or greater than target
                high = mid;
            } else {
                // less than target
                low = mid + 1;
            }
        }
        int targetStep = low;
        int leftIndex = Math.max(index - targetStep, 0);
        int rightIndex = Math.min(index + targetStep, n - 1);
        int leftVal = prefixCounter[index] - prefixCounter[leftIndex];
        int rightVal = prefixCounter[rightIndex + 1] - prefixCounter[index + 1];
        if (leftVal + rightVal == target) {
            return new int[]{leftIndex, rightIndex};
        }
        // leftVal + rightVal > target
        // there is a case that nums[leftIndex] == nums[rightIndex] == 1
        // binary search leftIndex and rightIndex separately
        int leftTarget = leftVal - 1;
        low = 0;
        high = targetStep - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            int midIndex = index - mid;
            int midVal = prefixCounter[index] - prefixCounter[midIndex];
            if (midVal >= leftTarget) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        int lastLeftDistance = low;
        int lastLeftIndex = index - lastLeftDistance;

        int rightTarget = rightVal - 1;
        low = 0;
        high = targetStep - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            int midIndex = index + mid;
            int midVal = prefixCounter[midIndex + 1] - prefixCounter[index + 1];
            if (midVal >= rightTarget) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        int lastRightDistance = low;
        int lastRightIndex = index + lastRightDistance;
        if (lastLeftDistance < lastRightDistance) {
            return new int[]{lastLeftIndex, rightIndex};
        } else {
            return new int[]{leftIndex, lastRightIndex};
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,1,0,0,0,1,1,0,0,1};
        // int k = 3;
        // int maxChanges = 1; // 3
        // int[] nums = {1,1,0,0,0,1,1,0,0,1};
        // int k = 3;
        // int maxChanges = 0; // 4
        int[] nums = {1,1,1,1,1};
        int k = 5;
        int maxChanges = 1; // 6
        System.out.println("test: " + sol.minimumMoves(nums, k, maxChanges));
    }
}