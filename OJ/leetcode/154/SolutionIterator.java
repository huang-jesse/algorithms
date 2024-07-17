class SolutionIterator {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 5000;
        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int pivot = (start + end) / 2;
            if (nums[pivot] < nums[end]) {
                // pivot is on the right side of min value.(Or pivot is the min value)
                end = pivot;
            } else if (nums[pivot] > nums[end]) {
                // pivot is on the left side of min value.
                start = pivot+1;
            } else {
                // nums[pivot] == nums[end]
                // exclude index of end
                end -= 1;
            }
        }

        return nums[start];
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,5,10,12,20,1,2};
        SolutionIterator sol = new SolutionIterator();
        int min = sol.findMin(nums1);
        System.out.println("min: " + min);
    }
}