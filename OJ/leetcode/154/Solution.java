class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 5000;
        return binarySearch(nums, 0, nums.length-1);
    }

    private int binarySearch(int[] nums, int start, int end) {
        if (start > end) return 5000;
        if (start == end) return nums[start];
        int mid = (start + end) / 2;
        int min = 5000;
        if (nums[start] < nums[mid]) {
            // left side is sorted
            min = nums[start];
            return Math.min(min, binarySearch(nums, mid+1, end));
        } else if (nums[start] > nums[mid]) {
            // right side is sorted
            min = nums[mid];
            return Math.min(min, binarySearch(nums, start, mid-1));
        } else {
            // when nums[start] == nums[mid]
            if (nums[mid] != nums[end]) {
                // left side is the same value as nums[mid], eg. [2,2,2,0,1,1]
                min = nums[start];
                return Math.min(min, binarySearch(nums, mid+1, end));
            } else {
                // unkow what side is sorted
                // search both side too
                return Math.min(binarySearch(nums, start, mid), binarySearch(nums, mid+1, end));
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,5,10,12,20,1,2};
        Solution sol = new Solution();
        int min = sol.findMin(nums1);
        System.out.println("min: " + min);
    }
}