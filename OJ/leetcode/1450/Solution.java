class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            int start = startTime[i];
            int end = endTime[i];
            if (start <= queryTime && queryTime <= end) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] startTime = {1,2,3};
        int[] endTime = {3,2,7};
        int queryTime = 4;
        System.out.println("test: " + sol.busyStudent(startTime, endTime, queryTime));
    }
}