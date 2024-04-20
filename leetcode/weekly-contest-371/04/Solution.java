import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int ans = 0;
        int mask = 0;
        Arrays.sort(nums);
        for (int i = 20; i >= 0; i--) {
            // [20, i] -> 1111 , [i, 0] -> 0000
            mask |= (1 << i);
            ans |= (1 << i);
            Map<Integer, Integer> numMap = new HashMap<>();
            boolean isValid = false;
            for (int num : nums) {
                int maskNum = num & mask;
                if (numMap.containsKey(maskNum ^ ans) && numMap.get(maskNum ^ ans) * 2 >= num) {
                    // find valid: num1 ^ num2 = ans
                    isValid = true;
                    break;
                }
                numMap.put(maskNum, num);
            }
            if (!isValid) {
                // invalid
                ans ^= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4,5};
        System.out.println("test: " + sol.maximumStrongPairXor(nums));
    }
}