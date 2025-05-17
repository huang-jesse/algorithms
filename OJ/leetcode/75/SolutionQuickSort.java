import java.util.Arrays;
class SolutionQuickSort {
    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int pivot = start;
        // partition
        int l = start + 1;
        int r = end;
        while (true) {
            while (l < end && nums[l] <= nums[pivot]) {
                l++;
            }
            while (nums[r] > nums[pivot]) {
                r--;
            }
            if (l >= r) break;
            swap(nums, l, r);
        }
        swap(nums, pivot, r);
        // pivot index - > r
        pivot = r;

        // sort (pivot is sorted)
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SolutionQuickSort sol = new SolutionQuickSort();
        int[] nums = {2,0,2,1,1,0}; // ans = [0,0,1,1,2,2]
        // int[] nums = {0,1}; // ans = [0,1]
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        sol.sortColors(nums);
        boolean isSorted = true;
        for (int i = 0; i < sortedNums.length; i++) {
            if (sortedNums[i] != nums[i]) {
                isSorted = false;
                break;
            }
        }
        System.out.println(String.format("Is nums sorted: %s", isSorted));
    }
}