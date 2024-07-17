import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public void rotate(int[] nums, int k) {
        int[] aux = nums.clone();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int rotatedIndex = (i + k) % n;
            nums[rotatedIndex] = aux[i];
        }
        aux = null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsList: " + numsList);
        sol.rotate(nums, k);
        List<Integer> numsRotatedList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsRotatedList: " + numsRotatedList);
    }
}