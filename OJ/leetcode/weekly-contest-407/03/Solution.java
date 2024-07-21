class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int ans = 0;
        int oneCount = 0;
        int preOne = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (i - preOne > 1) {
                    ans += oneCount;
                }
                oneCount++;
                preOne = i;
            }
        }
        if (s.charAt(n - 1) == '0') {
            ans += oneCount;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "1001101";
        System.out.println("test: " + sol.maxOperations(s));
    }
}