class Solution {
    public long minimumSteps(String s) {
        int n = s.length();
        int next = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == '0') {
                ans += (i - next);
                next++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "101100"; // 7
        System.out.println("test: " + sol.minimumSteps(s));
    }
}