class Solution {

    public int maximumPopulation(int[][] logs) {
        int[] aliveYears = new int[2050+1];
        for (int[] log : logs) {
            int birth = log[0];
            int death = log[1];
            for (int i = birth; i < death; i++) {
                aliveYears[i] += 1;
            }
        }
        int ans = 1950;
        int max = aliveYears[1950];
        for (int i = 1951; i <= 2050; i++) {
            int cur = aliveYears[i];
            if (cur > max) {
                max = cur;
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] logs = new int[][] {};
        System.out.println("test: " + sol.maximumPopulation(logs));
    }
}