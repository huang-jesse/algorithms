class Solution {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (check(i)) {
                ans += i * i;
            }
        }
        return ans;
    }

    private boolean check(int num) {
        int power = num * num;
        String powString = String.valueOf(power);
        char[] powChars = powString.toCharArray();
        int n = powChars.length;
        int[] preNumArr = new int[n + 1];
        int preNum = 0;
        for (int i = 1; i <= n; i++) {
            preNum = preNum * 10 + powChars[i - 1] - '0';
            preNumArr[i] = preNum;
        }
        int[] pows = new int[n + 1];
        int temp = 1;
        for (int i = 0; i <= n; i++) {
            pows[i] = temp;
            temp *= 10;
        }

        boolean[][] dp = new boolean[n + 1][num + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
            for (int j = 1; j <= num; j++) {
                for (int k = i; k >= 1; k--) {
                    // s[k, i]
                    int pow = pows[i - k + 1];
                    int s = preNumArr[i] - preNumArr[k - 1] * pow;
                    if (s > j) {
                        // 不符合，接下来 s 更大所以一定也不符合
                        break;
                    }
                    dp[i][j] = dp[i][j] | dp[k - 1][j - s];
                }
            }
        }
        return dp[n][num];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 37; // 1478
        // int n = 10; // 182
        System.out.println("test: " + sol.punishmentNumber(n));
    }
}