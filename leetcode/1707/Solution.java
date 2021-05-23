import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        Arrays.sort(nums);
        TreeMap<Integer, Integer> preXorSum = new TreeMap<>();
        int preSum = 0;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            preSum = preSum ^ cur;
            preXorSum.put(cur, preSum);
        }

        for (int i = 0; i < m; i++) {
            int curX = queries[i][0];
            int curM = queries[i][1];
            Integer pre = preXorSum.floorKey(curM);
            if (pre != null) {
                ans[i] = curX ^ pre;
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {};
        int[][] queries = {};
        System.out.println("test: " + sol.maximizeXor(nums, queries));
    }
}