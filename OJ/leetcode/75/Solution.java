import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        // select sort
        for (int i = 0; i < n; i++) {
            int select = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[select]) {
                    select = j;
                }
            }
            // swap
            int temp = nums[i];
            nums[i] = nums[select];
            nums[select] = temp;
        }
        return;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,0,2,1,1,0}; // ans = [0,0,1,1,2,2]
        sol.sortColors(nums);
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
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