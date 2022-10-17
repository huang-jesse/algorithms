class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int swapState = 0;
        int notSwapState = 1;
        for (int i = 1; i < n; i++) {
            int curSwapState = 0;
            int curNotSwapState = 0;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]
            && nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                curSwapState = Math.min(swapState, notSwapState);
                curNotSwapState = Math.min(swapState, notSwapState) + 1;
            } else if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                curSwapState = swapState;
                curNotSwapState = notSwapState + 1;
            } else if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                curSwapState = notSwapState;
                curNotSwapState = swapState + 1;
            }
            swapState = curSwapState;
            notSwapState = curNotSwapState;
        }
        return Math.min(swapState, notSwapState);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1,3,5,4};
        int[] nums2 = {1,2,3,7};
        // int[] nums1 = {1,3,5,8,11};
        // int[] nums2 = {1,5,6,6,7};
        // int[] nums1 = {1,3,5,8,11,12,13,13};
        // int[] nums2 = {1,5,6,6,7,8,9,14};
        System.out.println("test: " + sol.minSwap(nums1, nums2));
    }
}