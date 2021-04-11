class Solution {
    private Integer[][] memo;
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length - 1;
        memo = new Integer[4][n+1];

        return searchSideJumps(obstacles, 0, 2);
    }

    private int searchSideJumps(int[] obstacles, int cur, int curSide) {
        if (memo[curSide][cur] != null) {
            return memo[curSide][cur];
        }

        int n = obstacles.length-1;
        int index = cur;
        while(index < n && obstacles[index+1] != curSide) {
            index++;
        }
        if (index == n) {
            memo[curSide][cur] = 0;
            return 0;
        }

        int t1 = Integer.MAX_VALUE;
        int t2 = Integer.MAX_VALUE;
        int t3 = Integer.MAX_VALUE;

        if (obstacles[index] != 1 && curSide != 1) {
            t1 = this.searchSideJumps(obstacles, index, 1);
        }
        if (obstacles[index] != 2 && curSide != 2) {
            t2 = this.searchSideJumps(obstacles, index, 2);
        }
        if (obstacles[index] != 3 && curSide != 3) {
            t3 = this.searchSideJumps(obstacles, index, 3);
        }
        int t = Math.min(Math.min(t1, t2), t3) + 1;
        memo[curSide][cur] = t;

        return t;
    }

    public static void main(String[] args) {
        // int[] obstacles = new int[]{0,0,0,0,2,1,2,0,2,2,3,3,3,0,0,1,1,3,0,0,0,1,2,2,1,2,1,3,2,2,3,1,3,0,1,1,1,3,0,0,0,2,0,2,0,3,1,2,3,3,2,2,2,0,0,0,1,0,0,0,0,3,0,0,0,3,0,2,1,2,3,1,0,3,3,2,0,1,1,0,1,0,2,2,2,1,3,0,3,0,2,1,1,3,1,0,1,2,2,0,0};
        int[] obstacles = new int[]{0,1,2,3,0};
        Solution sol = new Solution();
        System.out.println("jumps: "+ sol.minSideJumps(obstacles));
    }
}