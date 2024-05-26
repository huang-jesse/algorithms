class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] % (nums2[j] * k) == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums1 = {1,3,4};
        // int[] nums2 = {1,3,4};
        // int k = 1;
        int[] nums1 = {1,2,4,12};
        int[] nums2 = {2,4};
        int k = 3;
        System.out.println("test: " + sol.numberOfPairs(nums1, nums2, k));
    }
}