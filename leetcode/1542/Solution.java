import java.util.Arrays;

class Solution {
    private static final int INF = 0x3fffffff;
    public int longestAwesome(String s) {
        int n = s.length();
        // 00,0000,0000 : the bit represent it's digit count is odd or not for [0, 9]
        int[] masks = new int[1024];
        Arrays.fill(masks, INF);
        // masks[0] represent there is empty
        int currentMask = 0;
        masks[currentMask] = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int digitNum = s.charAt(i) - '0';
            currentMask ^= (1 << digitNum);
            // sub len is even
            if (masks[currentMask] != INF) {
                ans = Math.max(ans, i - masks[currentMask]);
            }
            // sub len is odd
            // try to find a longestAwesome in [0, i]
            for (int j = 0; j < 10; j++) {
                int targetMask = currentMask ^ (1 << j);
                if (masks[targetMask] != INF) {
                    ans = Math.max(ans, i - masks[targetMask]);
                }
            }

            // update currentMask
            if (masks[currentMask] == INF) {
                masks[currentMask] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "3242415";
        System.out.println("test: " + sol.longestAwesome(s));
    }
}