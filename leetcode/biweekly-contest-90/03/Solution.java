import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int destroyTargets(int[] nums, int space) {
        Arrays.sort(nums);
        Map<Integer, Integer> modCounter = new HashMap<>();
        int n = nums.length;
        // int[] destroyCounter = new int[n];
        int max = 0;
        int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            int cur = nums[i];
            int mod = cur % space;
            modCounter.put(mod, modCounter.getOrDefault(mod, 0) + 1);
            int res = modCounter.get(mod);
            if (res >= max) {
                max = res;
                ans = cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,7,8,1,1,5};
        int space = 2;
        System.out.println("test: " + sol.destroyTargets(nums, space));
    }
}