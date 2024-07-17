import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        List<Integer> sumList = new ArrayList<>();
        Integer sumAsc = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] <= nums[i-1]) {
                sumList.add(sumAsc);
                sumAsc = nums[i];
            } else {
                sumAsc += nums[i];
            }
        }
        sumList.add(sumAsc);
        return sumList.stream().sorted(Comparator.reverseOrder()).findFirst().get();
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        int[] testArr = new int[] {3,6,10,1,8,9,9,8,9};
        // Do test
        int test = sol.maxAscendingSum(testArr);
        System.out.println(test);
    }
}