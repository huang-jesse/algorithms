class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] stones = {5,1,2,4,3};
        // int[] stones = {2,1};
        // int[] stones = {2};
        System.out.println("test: " + sol.stoneGameIX(stones));
    }
}