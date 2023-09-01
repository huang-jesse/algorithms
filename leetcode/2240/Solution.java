class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        int mostTimes = total / cost1;
        for (int i = 0; i <= mostTimes; i++) {
            int remainAmount = total - cost1 * i;
            int mostCanBuyTimes = remainAmount / cost2;
            ans += 1 + mostCanBuyTimes;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int total = 20;
        int cost1 = 10;
        int cost2 = 5;
        System.out.println("test: " + sol.waysToBuyPensPencils(total, cost1, cost2));
    }
}