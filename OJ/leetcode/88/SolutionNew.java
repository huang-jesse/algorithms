import java.util.Arrays;

class SolutionNew {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (index >= 0) {
            if (i >= 0 && j >= 0) {
                nums1[index] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
            } else {
                nums1[index] = i >= 0 ? nums1[i--] : nums2[j--];
            }
            index--;
        }
    }

    public static void main(String[] args) {
        SolutionNew sol = new SolutionNew();
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        sol.merge(nums1, m, nums2, n);
        System.out.println("Merged num1: " + Arrays.toString(nums1));
    }
}