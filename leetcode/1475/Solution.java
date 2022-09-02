import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        for (int i = 0; i < n - 1; i++) {
            int cur = prices[i];
            for (int j = i + 1; j < n; j++) {
                if (cur >= prices[j]) {
                    prices[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] prices = {8,4,6,2,3};
        int[] prices = {1,1,1,1};
        System.out.println("test: " + Arrays.stream(sol.finalPrices(prices)).boxed().collect(Collectors.toList()));
    }
}