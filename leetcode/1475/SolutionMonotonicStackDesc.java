import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class SolutionMonotonicStackDesc {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int cur = prices[i];
            while (!stack.isEmpty() && stack.peek() > cur) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                prices[i] = prices[i] - stack.peek();
            }
            stack.push(cur);
        }
        return prices;
    }

    public static void main(String[] args) {
        SolutionMonotonicStackDesc sol = new SolutionMonotonicStackDesc();
        // int[] prices = {8,4,6,2,3};
        // int[] prices = {1,1,1,1};
        int[] prices = {1};
        System.out.println("test: " + Arrays.stream(sol.finalPrices(prices)).boxed().collect(Collectors.toList()));
    }
}