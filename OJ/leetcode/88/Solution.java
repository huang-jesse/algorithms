class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        int index = m+n-1;
        m--;
        n--;
        while (index >= 0) {
            if (m < 0) {
                nums1[index--] = nums2[n--];
            } else if (n < 0) {
                // only nums1 left side on the correct index
                break;
            } else if (nums1[m] > nums2[n]) {
                nums1[index--] = nums1[m--];
            } else {
                nums1[index--] = nums2[n--];
            }
        }
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) {
                return false;
            }
        }
        return true;
    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{};
        int m = 0;
        int[] nums2 = new int[]{};
        int n = 0;
        Solution sol = new Solution();
        sol.merge(nums1, m, nums2, n);

        System.out.println("Is sorted: " + isSorted(nums1));
        show(nums1);
    }
}