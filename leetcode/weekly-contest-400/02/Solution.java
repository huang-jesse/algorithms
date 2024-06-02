import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (o1, o2) -> o1[0] - o2[0]);
        int ans = 0;
        int start = 0;
        for (int[] meeting : meetings) {
            int range = meeting[0] - start - 1;
            if (range > 0) {
                ans += range;
            }
            start = Math.max(start, meeting[1]);
        }
        if (days - start > 0) {
            ans += days - start;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int days = 10;
        int[][] meettings = {{5,7},{1,3},{9,10}};
        System.out.println("test: " + sol.countDays(days, meettings));
    }
}