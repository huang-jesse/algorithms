class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int lastMin = Math.min(nums1[n - 1], nums2[n - 1]);
        int lastMax = Math.max(nums1[n - 1], nums2[n - 1]);
        for (int i = 0; i < n; i++) {
            if ((nums1[i] > lastMin && nums2[i] > lastMin)
                || nums1[i] > lastMax || nums2[i] > lastMax) {
                return -1;
            }
        }
        int nums1GreaterCount = 0;
        int nums2GreaterCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums1[i] > lastMin) {
                nums1GreaterCount++;
            }
            if (nums2[i] > lastMin) {
                nums2GreaterCount++;
            }
        }
        if (lastMin == nums1[n - 1]) {
            return Math.min(nums1GreaterCount, nums2GreaterCount + 1);
        } else {
            return Math.min(nums1GreaterCount + 1, nums2GreaterCount);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1,2,7};
        int[] nums2 = {4,5,3};
        System.out.println("test: " + sol.minOperations(nums1, nums2));
    }
}