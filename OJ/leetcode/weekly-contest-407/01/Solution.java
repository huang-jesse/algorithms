class Solution {
    public int minChanges(int n, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bitN = (n >> i) & 1;
            int bitK = (k >> i) & 1;
            if (bitN < bitK) {
                return -1;
            } else if (bitN > bitK) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 13;
        int k = 4;

        System.out.println("test: " + sol.minChanges(n, k));
    }
}