import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SolutionReverseArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // [n - k, n - 1] -> [0, k - 1] in reverse order
        // [0, n - k - 1] -> [k, n - 1] in reverse order
        reverse(nums, 0, n - 1);
        // reverse [0, k - 1] to origin order
        reverse(nums, 0, k - 1);
        // reverse [k, n - 1] to origin order
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        SolutionReverseArray sol = new SolutionReverseArray();
        // int[] nums = {-1,-100,3,99};
        // int k = 2;
        // int[] nums = {1,2,3,4,5,6,7};
        // int k = 3;
        int[] nums = {1,2,3,4,5,6};
        int k = 3;
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsList: " + numsList);
        sol.rotate(nums, k);
        List<Integer> numsRotatedList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("numsRotatedList: " + numsRotatedList);
    }
}