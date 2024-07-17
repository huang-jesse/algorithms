import java.util.TreeMap;

class Solution {
    public boolean isArraySpecial(int[] nums) {
        return isSpecial(nums, new int[][]{{0, nums.length - 1}})[0];
    }

    public boolean[] isSpecial(int[] nums, int[][] queries) {
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
        int[] nums = {};
        System.out.println("test: " + sol.isArraySpecial(nums));
    }
}