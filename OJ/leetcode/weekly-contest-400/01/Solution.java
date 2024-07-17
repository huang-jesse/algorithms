class Solution {
    public int minimumChairs(String s) {
        int n = s.length();
        int count = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'E') {
                count++;
            } else {
                count--;
            }
            ans = Math.max(ans, count);
        }
            return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "ELELEEL";
        System.out.println("test: " + sol.minimumChairs(s));
    }
}