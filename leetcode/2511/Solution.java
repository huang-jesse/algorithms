class Solution {
    public int captureForts(int[] forts) {
        int n = forts.length;
        int pre = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (forts[i] != 0) {
                if (pre != -1 && forts[i] != forts[pre]) {
                    ans = Math.max(ans, i - pre - 1);
                }
                pre = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] forts = {1,0,0,-1,0,0,0,0,1};
        System.out.println("test: " + sol.captureForts(forts));
    }
}