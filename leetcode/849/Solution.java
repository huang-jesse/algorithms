class Solution {
    private final static int INF = 0x3fffffff;
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] leftArr = new int[n];
        int[] rightArr = new int[n];
        int lastLeft = -INF;
        int lastRight = INF;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 0) {
                leftArr[i] = i - lastLeft;
            } else {
                // seats[i] == 1
                lastLeft = i;
            }
            if (seats[n - 1 - i] == 0) {
                rightArr[n - 1 - i] = lastRight - (n - 1 - i);
            } else {
                lastRight = n - 1 - i;
            }
        }
        int ans = 0;
        for (int i = 0 ; i < n; i++) {
            ans = Math.max(ans, Math.min(leftArr[i], rightArr[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] seats = {1,0,0,0,1,0,1};
        // int[] seats = {1,0,0,0};
        System.out.println("test: " + sol.maxDistToClosest(seats));
    }
}