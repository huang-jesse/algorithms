import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int[] ans = new int[n - k + 1];
        for (int i = 0, j = 0; i < n - k + 1; i++) {
            // create sliding window
            while (j < i + k) {
                tm.put(nums[j], tm.getOrDefault(nums[j], 0) + 1);
                j++;
            }
            // get max of sliding window
            ans[i] = tm.lastKey();
            // remove first
            int removeNum = nums[i];
            tm.put(removeNum, tm.get(removeNum) - 1);
            if (tm.get(removeNum) == 0) {
                tm.remove(removeNum);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = {1,3,-1,-3,5,3,6,7};
        // int k = 3;
        // int[] nums = {1};
        // int k = 1;
        int[] nums = {0,0};
        int k = 2;
        // int[] nums = {0,0};
        // int k = 1;
        System.out.println("test: " + Arrays.stream(sol.maxSlidingWindow(nums, k)).boxed().collect(Collectors.toList()));
    }
}