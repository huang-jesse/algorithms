class SolutionTwoPointer {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int ans = 0;
        int l = 0;
        while (l < n && seats[l] == 0) {
            l++;
        }
        ans = Math.max(ans, l);
        while (l < n) {
            int r = l + 1;
            while (r < n && seats[r] == 0) {
                r++;
            }
            if (r == n) {
                ans = Math.max(ans, r - l - 1);
            } else {
                // seats[r] == 1
                ans = Math.max(ans, (r - l) / 2);
            }
            l = r;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionTwoPointer sol = new SolutionTwoPointer();
        int[] seats = {1,0,0,0,1,0,1};
        // int[] seats = {1,0,0,0};
        System.out.println("test: " + sol.maxDistToClosest(seats));
    }
}