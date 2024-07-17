class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] trusted = new int[n+1];
        boolean[] trustFlag = new boolean[n+1];
        for (int[] trustInfo : trust) {
            trustFlag[trustInfo[0]] = true;
            trusted[trustInfo[1]]++;
        }
        int judge = -1;
        for (int i = 1; i <= n; i++) {
            if (!trustFlag[i] && trusted[i] == n-1) {
                judge = i;
                break;
            }
        }
        return judge;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2;
        int[][] trust = {{1,2}};
        System.out.println("test: " + sol.findJudge(n, trust));
    }
}