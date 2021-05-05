import java.util.Arrays;

class Solution {
    int[][][] memo;
    int[] houses;
    int[][] cost;
    int target;
    int sizeOfHouses;
    static final int INF = (int)Math.pow(10, 6);
    static final int INITIAL_VALUE = -2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.memo = new int[m][n+1][target+1];
        this.houses = houses;
        this.cost = cost;
        this.target = target;
        this.sizeOfHouses = houses.length;
        // initialize memo
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(memo[i][j], INITIAL_VALUE);
            }
        }
        int ans = dfs(0, 0, 0);
        // if the ans is INF, that means it is without a solution, then return -1
        if (ans == INF) {
            ans = -1;
        }
        return ans;
    }

    private int dfs(int index, int preColor, int preNumOfGroup) {
        if (index >= houses.length) {
            if (preNumOfGroup == target)
                return 0;
            else
                return INF;
        }
        if (preNumOfGroup > target)
            return INF;
        if (memo[index][preColor][preNumOfGroup] != INITIAL_VALUE)
            return memo[index][preColor][preNumOfGroup];
        // all cost of current and after painted
        int allCost = INF;
        int sizeOfColors = cost[index].length;
        for (int i = 0; i < sizeOfColors; i++) {
            int colorOfCurHouse = houses[index];
            int curColor = i + 1;
            int curCost = cost[index][i];
            // if current house existed color and curColor != colorOfCurHouse, continue
            if (colorOfCurHouse != 0 && colorOfCurHouse != curColor)
                continue;
            int curNumOfGroup = preNumOfGroup;
            // if first index of houses or curColor differ to preColor
            if (curColor != preColor)
                curNumOfGroup += 1;
            // calculate all of after cost
            int tempCost = dfs(index+1, curColor, curNumOfGroup);
            // if the current house has no color, plus the curCost
            if (colorOfCurHouse == 0)
                tempCost = tempCost + curCost;
            allCost = Math.min(allCost, tempCost);
        }
        memo[index][preColor][preNumOfGroup] = allCost;
        return allCost;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] houses = new int[]{0,2,1,2,0};
        // int[] houses = new int[]{0,0,0,0,0};
        int[][] cost = new int[][] {{1,10},{10,1},{10,1},{1,10},{5,1}};
        int m = 5;
        int n = 2;
        int target = 3;
        System.out.println("test: " + sol.minCost(houses, cost, m, n, target));
    }
}