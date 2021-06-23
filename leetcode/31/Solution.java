import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length-1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3};
        List<Integer> initList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        sol.nextPermutation(nums);
        List<Integer> curList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("test: " + curList);
        while (!initList.equals(curList)) {
            sol.nextPermutation(nums);
            curList = Arrays.stream(nums).boxed().collect(Collectors.toList());
            System.out.println("test: " + curList);
        }
    }
}