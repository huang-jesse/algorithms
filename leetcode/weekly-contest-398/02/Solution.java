import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        int l = 0;
        while (l < n) {
            int r = l;
            while (r + 1 < n && nums[r] % 2 != nums[r + 1] % 2) {
                r++;
            }
            if (r > l) {
                ts.put(l, r);
            }
            l = r + 1;
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (queries[i][0] == queries[i][1]) {
                ans[i] = true;
                continue;
            }
            Integer start = ts.floorKey(queries[i][0]);
            if (start != null && ts.get(start) >= queries[i][1]) {
                ans[i] = true;
            } else {
                ans[i] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,3,1,6};
        int[][] queries = {{0,2}, {2,3}};// false, true
        // int[] nums = {3,7,8};
        // int[][] queries = {{0,2}};// false
        System.out.println("test: " + Arrays.toString(sol.isArraySpecial(nums, queries)));
    }
}