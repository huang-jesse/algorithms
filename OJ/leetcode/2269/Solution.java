class Solution {
    public int divisorSubstrings(int num, int k) {
        String numStr = Integer.toString(num);
        int n = numStr.length();
        int ans = 0;
        for (int i = 0; i <= n - k; i++) {
            int sub = Integer.parseInt(numStr.substring(i, i + k));
            ans += sub > 0 && num % sub == 0 ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int num = 430043;
        int k = 2;
        System.out.println("test: " + sol.divisorSubstrings(num, k));
    }
}