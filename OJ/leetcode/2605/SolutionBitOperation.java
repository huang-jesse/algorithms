class SolutionBitOperation {
    public int minNumber(int[] nums1, int[] nums2) {
        int mask1 = 0;
        for (int num1 : nums1) {
            mask1 |= 1 << num1;
        }
        int mask2 = 0;
        for (int num2 : nums2) {
            mask2 |= 1 << num2;
        }
        int m = mask1 & mask2;
        if (m > 0) {
            return Integer.numberOfTrailingZeros(m);
        }
        int m1 = Integer.numberOfTrailingZeros(mask1);
        int m2 = Integer.numberOfTrailingZeros(mask2);
        if (m1 < m2) {
            return m1 * 10 + m2;
        } else {
            return m2 * 10 + m1;
        }
    }

    public static void main(String[] args) {
        SolutionBitOperation sol = new SolutionBitOperation();
        int[] nums1 = {4,1,3};
        int[] nums2 = {5,7};
        System.out.println("test: " + sol.minNumber(nums1, nums2));
    }
}