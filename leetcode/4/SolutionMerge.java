class SolutionMerge {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] aux = new int[n+m];
        int j = 0;
        int k = 0;
        int i = 0;
        int midMax = ((n+m)/2)+1;
        while (i < midMax) {
            if (k >= m) {
                aux[i] = nums1[j];
                j++;
            } else if (j >= n) {
                aux[i] = nums2[k];
                k++;
            } else if (nums1[j] <= nums2[k]) {
                aux[i] = nums1[j];
                j++;
            } else {
                aux[i] = nums2[k];
                k++;
            }
            i++;
        }
        if ((n+m) % 2 == 0) {
            return (double)(aux[midMax-1] + aux[midMax-2]) / 2;
        } else {
            return aux[midMax-1];
        }
    }

    public static void main(String[] args) {
        SolutionMerge sol = new SolutionMerge();
        // int[] nums1 = {1,3,7};
        // int[] nums2 = {2,5,6,8};
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println("test: " + sol.findMedianSortedArrays(nums1, nums2));
    }
}