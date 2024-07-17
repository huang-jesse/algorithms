class Solution {
    public int minimumRecolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();
        int n = chars.length;
        int[] preSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'B') {
                preSum[i+1] = preSum[i] + 1;
            } else {
                preSum[i+1] = preSum[i];
            }
        }
        int maxNumOfB = 0;
        for (int i = k; i <= n; i++) {
            int preIndex = i - k;
            int numOfB = preSum[i] - preSum[preIndex];
            if (numOfB > maxNumOfB) {
                maxNumOfB = numOfB;
            }
        }
        return k - maxNumOfB;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String blocks = "WBBWWBBWBW";
        int k = 7;
        System.out.println("test: " + sol.minimumRecolors(blocks, k));
    }
}