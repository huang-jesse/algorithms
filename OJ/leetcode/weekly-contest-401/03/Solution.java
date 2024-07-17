import java.util.Arrays;

class Solution {
    private int[] memo;
    public int maxTotalReward(int[] rewardValues) {
        int n = rewardValues.length;
        this.memo = new int[50001];
        Arrays.fill(this.memo, -1);
        Arrays.sort(rewardValues);
        return rewardValues[n - 1] + backtrack(rewardValues, rewardValues[n - 1] - 1);
    }

    private int backtrack(int[] rewardValues, int maxTarget) {
        if (maxTarget <= 0 || maxTarget < rewardValues[0]) return 0;
        if (this.memo[maxTarget] != -1) {
            return this.memo[maxTarget];
        }
        int n = rewardValues.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (rewardValues[mid] <= maxTarget) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int res = 0;
        for (int i = l; i >= 0; i--) {
            if (res >= (2 * rewardValues[i] - 1)) break;
            res = Math.max(res, rewardValues[i] + backtrack(rewardValues, Math.min(maxTarget - rewardValues[i], rewardValues[i] - 1)));
        }
        this.memo[maxTarget] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] rewardValues = {1,6,4,3,2};
        int[] rewardValues = {7};
        System.out.println("test: " + sol.maxTotalReward(rewardValues));
    }
}