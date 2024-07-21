class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = nums[i] - target[i];
        }
        long ans = 0;
        for (int i = 0; i < n;) {
            if (diffs[i] == 0) {
                i++;
                continue;
            }
            if (diffs[i] < 0) {
                int j = i;
                while (j < n && diffs[j] < 0) {
                    diffs[j] = Math.abs(diffs[j]);
                    j++;
                }
                ans += diffs[i];
                for (int k = i + 1; k < j; k++) {
                    if (diffs[k] > diffs[k - 1]) {
                        ans += diffs[k] - diffs[k - 1];
                    }
                }
                i = j;
            } else {
                int j = i;
                while (j < n && diffs[j] > 0) {
                    j++;
                }
                ans += diffs[i];
                for (int k = i + 1; k < j; k++) {
                    if (diffs[k] > diffs[k - 1]) {
                        ans += diffs[k] - diffs[k - 1];
                    }
                }
                i = j;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,3,2};
        int[] target = {2,1,4};
        System.out.println("test: " + sol.minimumOperations(nums, target));
    }
}