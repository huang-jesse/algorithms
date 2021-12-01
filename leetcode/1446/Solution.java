class Solution {
    public int maxPower(String s) {
        int n = s.length();
        int ans = 1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                count++;
            } else {
                ans = Math.max(ans, count);
                count = 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "triplepillooooow";
        System.out.println("test: " + sol.maxPower(s));
    }
}