class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int min = 0;
        int total = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            if (total < min) {
                min = total;
                ans = i + 1;
            }
        }
        return total < 0 ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2}; // res: 3
        System.out.println("test: " + sol.canCompleteCircuit(gas, cost));
    }
}