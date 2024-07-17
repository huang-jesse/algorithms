import java.util.HashMap;
import java.util.Map;

class Solution {
    private int n;
    private int minProfit;
    private int[] group;
    private int[] profit;
    private Map<String, Integer> memoMap = new HashMap<>();
    private static final int MOD = (int)1e9 + 7;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        this.n = n;
        this.minProfit = minProfit;
        this.group = group;
        this.profit = profit;
        return dfs(0, 0, 0);
    }

    private int dfs(int index, int preSumOfGroup, int preSumOfProfit) {
        if (index == group.length) {
            if (preSumOfGroup <= n && preSumOfProfit >= minProfit) {
                return 1;
            } else {
                return 0;
            }
        }
        if (preSumOfGroup > n) {
            return 0;
        }
        String key = index + "_" + preSumOfGroup + "_" + preSumOfProfit;
        if (memoMap.containsKey(key)) {
            return memoMap.get(key);
        }

        long count = dfs(index + 1, preSumOfGroup + group[index], preSumOfProfit + profit[index]);
        count += dfs(index + 1, preSumOfGroup, preSumOfProfit);
        int ans = (int)(count % MOD); 
        memoMap.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int minProfit = 3;
        int[] group = {2,2};
        int[] profit = {2,3};

        System.out.println("test: " + sol.profitableSchemes(n, minProfit, group, profit));
    }
}