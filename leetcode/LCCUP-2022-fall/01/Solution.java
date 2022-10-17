class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int[] trendA = new int[n];
        int[] trendB = new int[n];
        for (int i = 1; i < n; i++) {
            if (temperatureA[i] == temperatureA[i-1]) {
                trendA[i] = 0;
            } else if (temperatureA[i] > temperatureA[i-1]) {
                trendA[i] = 1;
            } else {
                trendA[i] = -1;
            }
            if (temperatureB[i] == temperatureB[i-1]) {
                trendB[i] = 0;
            } else if (temperatureB[i] > temperatureB[i-1]) {
                trendB[i] = 1;
            } else {
                trendB[i] = -1;
            }
        }
        int ans = 0;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (trendA[i] == trendB[i]) {
                count++;
            } else {
                count = 0;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] temperatureA = {};
        int[] temperatureB = {};
        System.out.println("test: " + sol.temperatureTrend(temperatureA, temperatureB));
    }
}