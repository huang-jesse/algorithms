class Solution {
    // 1e9 < 2^31-1 (max int)
    // 1e18 < 2^63-1 (max long)
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int)(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int)Math.pow(nVal, 1.0 / m);// max of k is 1e9
            long pow = 1;
            long sum = 1;
            for (int i = 0; i < m; i++) {
                pow *= k;
                sum += pow;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String n = "4681";
        System.out.println("test: " + sol.smallestGoodBase(n));
    }
}