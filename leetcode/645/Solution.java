import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] visits = new boolean[n+1];
        int errorNum = 0;
        for (int num : nums) {
            if (!visits[num]) {
                visits[num] = true;
            } else {
                errorNum = num;
            }
        }
        int lostNum = 0;
        for (int i = 1; i <= n; i++) {
            if (!visits[i]) {
                lostNum = i;
                break;
            }
        }
        return new int[]{errorNum, lostNum};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,2,4};
        int[] ans = sol.findErrorNums(nums);
        System.out.println("test: " + Arrays.stream(ans).boxed().collect(Collectors.toList()));
        System.out.println(6&(-6));
    }
}