class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLength = len1 + len2;
        if (totalLength % 2 != 0) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }

    /**
     * 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private static int getKthElement(int[] nums1, int[] nums2, int k) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        while (true) {
            int half = k/2;
            if (index1 >= len1) {
                return nums2[index2 + k - 1];
            } else if (index2 >= len2) {
                return nums1[index1 + k - 1];
            } else if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int midIndex1 = Math.min(index1 + half - 1, len1 - 1);
            int midIndex2 = Math.min(index2 + half - 1, len2 - 1);
            int pivot1 = nums1[midIndex1];
            int pivot2 = nums2[midIndex2];
            if (pivot1 <= pivot2) {
                k = k - (midIndex1 - index1 + 1);
                index1 = midIndex1 + 1;
            } else {
                k = k - (midIndex2 - index2 + 1);
                index2 = midIndex2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums1 = {1,3,7};
        // int[] nums2 = {2,5,6,8};
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println("test: " + sol.findMedianSortedArrays(nums1, nums2));
    }
}