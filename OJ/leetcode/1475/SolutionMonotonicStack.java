import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class SolutionMonotonicStack {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        Deque<Integer> stackOfIndexes = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int cur = prices[i];
            while (!stackOfIndexes.isEmpty() && prices[stackOfIndexes.peek()] >= cur) {
                int index = stackOfIndexes.pop();
                prices[index] = prices[index] - cur;
            }
            stackOfIndexes.push(i);
        }
        return prices;
    }

    public static void main(String[] args) {
        SolutionMonotonicStack sol = new SolutionMonotonicStack();
        int[] prices = {8,4,6,2,3};
        // int[] prices = {1,1,1,1};
        // int[] prices = {1};
        System.out.println("test: " + Arrays.stream(sol.finalPrices(prices)).boxed().collect(Collectors.toList()));
    }
}