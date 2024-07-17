class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        int[] prefixDp = new int[n + 1];
        prefixDp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                // opteration 1 or operation 3
                prefixDp[i + 1] = Math.min(i + 1, prefixDp[i] + 2);
            } else {
                // 0
                prefixDp[i + 1] = prefixDp[i];
            }
        }
        int[] suffixDp = new int[n + 1];
        suffixDp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                // opteration 2 or operation 3
                suffixDp[i] = Math.min(n - i, suffixDp[i + 1] + 2);
            } else {
                // 0
                suffixDp[i] = suffixDp[i + 1];
            }
        }
        int ans = n;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, prefixDp[i] + suffixDp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "1100101"; // 5
        System.out.println("test: " + sol.minimumTime(s));
    }
}