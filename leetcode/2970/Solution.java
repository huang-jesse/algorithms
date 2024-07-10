class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        // 1-indexed
        boolean[] isLeftStrictlyIncreasing = new boolean[n + 1];
        // 0-indexed
        boolean[] isRightStrictlyIncreasing = new boolean[n + 1];
        isLeftStrictlyIncreasing[0] = true;
        isLeftStrictlyIncreasing[1] = true;
        isRightStrictlyIncreasing[n] = true;
        isRightStrictlyIncreasing[n - 1] = true;
        for (int i = 1; i < n; i++) {
            isLeftStrictlyIncreasing[i + 1] = isLeftStrictlyIncreasing[i] & (nums[i] > nums[i - 1]);
            isRightStrictlyIncreasing[n - 1 - i] = isRightStrictlyIncreasing[n - i] & (nums[n - 1 - i] < nums[n - i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!isLeftStrictlyIncreasing[i]) continue;
            int l = i;
            int r = n - 1;
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                if (isRightStrictlyIncreasing[mid + 1] && (i == 0 || mid == n - 1 || nums[i - 1] < nums[mid + 1])) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            int count = n - 1 - l + 1;
            ans += count;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {6,5,7,8}; // 7
        int[] nums = {8,7,6,6}; // 3
        System.out.println("test: " + sol.incremovableSubarrayCount(nums));
    }
}