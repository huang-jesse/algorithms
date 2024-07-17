import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int counter = 0;
        Map<Integer, Integer> counterMap = new HashMap<>();
        // initial preIndex
        counterMap.put(0, -1);
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (cur == 1) {
                counter++;
            } else {
                counter--;
            }
            if (counterMap.containsKey(counter)) {
                // is 0 1 blance subArr
                int preIndex = counterMap.get(counter);
                int lenOfSubArr = i - preIndex;
                ans = Math.max(ans, lenOfSubArr);
            } else {
                counterMap.put(counter, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {0,0,1,1};
        System.out.println("test: " + sol.findMaxLength(nums));
    }
}