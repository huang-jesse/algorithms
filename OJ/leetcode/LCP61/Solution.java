class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int continuous = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int trendA = temperatureA[i] - temperatureA[i - 1];
            int trendB = temperatureB[i] - temperatureB[i - 1];
            if ((trendA == 0 && trendB == 0) || (trendA < 0 && trendB < 0) || (trendA > 0 && trendB > 0)) {
                continuous++;
                ans = Math.max(ans, continuous);
            } else {
                continuous = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] temperatureA = {21,18,18,18,31};
        int[] temperatureB = {34,32,16,16,17};
        System.out.println("test: " + sol.temperatureTrend(temperatureA, temperatureB));
    }
}