import java.util.ArrayList;
import java.util.List;

class Solution {
    public int splitNum(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }
        digits.sort((o1, o2) -> o1.compareTo(o2));
        int n = digits.size();
        int[] nums = new int[2];
        for (int i = 0; i < n; i++) {
            nums[i % 2] = nums[i % 2] * 10 + digits.get(i);
        }
        return nums[0] + nums[1];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 687;
        System.out.println("test: " + sol.splitNum(num));
    }
}