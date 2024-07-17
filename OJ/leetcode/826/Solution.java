import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;
        Arrays.sort(worker);
        Integer[] indexes = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(indexes, (o1, o2) -> difficulty[o1] - difficulty[o2]);
        int ans = 0;
        int maxProfit = 0;
        for (int i = 0, j = 0; i < m; i++) {
            while (j < n && worker[i] >= difficulty[indexes[j]]) {
                maxProfit = Math.max(maxProfit, profit[indexes[j]]);
                j++;
            }
            ans += maxProfit;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] difficulty = {2,4,6,8,10};
        // int[] profit = {10,20,30,40,50};
        // int[] worker = {4,5,6,7};
        int[] difficulty = {13,37,58};
        int[] profit = {4,90,96};
        int[] worker = {34,73,45};
        System.out.println("test: " + sol.maxProfitAssignment(difficulty, profit, worker));
    }
}