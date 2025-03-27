class Solution {
    public long minimumCost(String s) {
        int n = s.length();
        long[] prefix = new long[n];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            if (s.charAt(i) != s.charAt(i - 1)) {
                prefix[i] += i;
            }
        }

        long[] suffix = new long[n];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1];
            if (s.charAt(i) != s.charAt(i + 1)) {
                suffix[i] += n - i - 1;
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, prefix[i] + suffix[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "010101";
        System.out.println("test: " + sol.minimumCost(s));
    }
}