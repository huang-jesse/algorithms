class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            int num = i;
            while (num % 5 == 0) {
                num /= 5;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        System.out.println("test: " + sol.trailingZeroes(n));
    }
}