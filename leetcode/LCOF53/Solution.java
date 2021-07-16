class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int index = binarySearch(nums, target);
        if (index == -1)
            return 0;
        int ans = 0;
        for (int i = index; i < n; i++) {
            if (nums[i] != target)
                break;
            ans++;
        }
        return ans;
    }

    private static int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right] == target ? right : -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {5,7,7,8};
        int target = 5;
        System.out.println("test: " + sol.search(nums, target));
    }
}