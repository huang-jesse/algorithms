import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // {endIndex, {startIndex, gold}}
        Map<Integer, List<int[]>> endMaps = new HashMap<>();
        for (List<Integer> offer : offers) {
            int starIndex = offer.get(0);
            int endIndex = offer.get(1);
            int price = offer.get(2);
            List<int[]> startInfos = endMaps.getOrDefault(endIndex, new ArrayList<>());
            startInfos.add(new int[]{starIndex, price});
            endMaps.put(endIndex, startInfos);
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            List<int[]> starList = endMaps.getOrDefault(i, new ArrayList<>());
            // 不选当前结尾项
            dp[i + 1] = dp[i];
            for (int[] starInfo : starList) {
                int j = starInfo[0];
                int price = starInfo[1];
                dp[i + 1] = Math.max(dp[i + 1], dp[j + 1 - 1] + price);
            }
        }
        int ans = 0;
        for (int res : dp) {
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int n = 5;
        // List<List<Integer>> offers = Arrays.asList(Arrays.asList(0,0,1), Arrays.asList(0,2,2), Arrays.asList(1,3,2));
        int n = 10;
        List<List<Integer>> offers = Arrays.asList(Arrays.asList(0,6,5), Arrays.asList(2,9,4), Arrays.asList(0,9,2)
            , Arrays.asList(3,9,3), Arrays.asList(1,6,10), Arrays.asList(0,1,3), Arrays.asList(3,8,9)
            , Arrays.asList(4,8,3), Arrays.asList(2,6,5), Arrays.asList(0,4,6));
        System.out.println("test: " + sol.maximizeTheProfit(n, offers));
    }
}