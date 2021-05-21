import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    Map<Integer, TreeSet<Integer>> indexMapOfNums2;
    int[] nums1;
    int n;
    int m;
    Integer[][] memo;
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        this.indexMapOfNums2 = new HashMap<>();
        this.nums1 = nums1;
        this.n = nums1.length;
        this.m = nums2.length;
        for (int i = 0; i < m; i++) {
            int cur = nums2[i];
            TreeSet<Integer> ts = indexMapOfNums2.getOrDefault(cur, new TreeSet<>());
            ts.add(i);
            indexMapOfNums2.put(cur, ts);
        }
        this.memo = new Integer[n][m];
        return dfs(0, 0);
    }

    private int dfs(int indexOfNum1, int indexOfNum2) {
        if (indexOfNum1 >= n || indexOfNum2 >= m) {
            return 0;
        }
        if (this.memo[indexOfNum1][indexOfNum2] != null) {
            return this.memo[indexOfNum1][indexOfNum2];
        }
        int cur = nums1[indexOfNum1];
        if (!indexMapOfNums2.containsKey(cur)) {
            return dfs(indexOfNum1 + 1, indexOfNum2);
        }
        TreeSet<Integer> indexsOfNum2 = indexMapOfNums2.get(cur);
        Integer leastCanConnectIndex = indexsOfNum2.ceiling(indexOfNum2);
        if (leastCanConnectIndex == null) {
            return dfs(indexOfNum1 + 1, indexOfNum2);
        }
        // there are two choice here
        // 1. connect line with leastCanConnectIndex
        int connectLine = 1 + dfs(indexOfNum1 + 1, leastCanConnectIndex + 1);
        // 2. not connect line
        int notConnectLine = dfs(indexOfNum1 + 1, indexOfNum2);
        int ans = Math.max(connectLine, notConnectLine);
        this.memo[indexOfNum1][indexOfNum2] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums1 = {2,5,1,2,5};
        // int[] nums2 = {10,5,2,1,5,2};
        int[] nums1 = {1,3,7,1,7,5};
        int[] nums2 = {1,9,2,5,1};
        System.out.println("test: " + sol.maxUncrossedLines(nums1, nums2));
    }
}