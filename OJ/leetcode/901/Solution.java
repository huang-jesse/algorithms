import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    // {price, span}
    private final Deque<int[]> monotonicStack;

    public StockSpanner() {
        this.monotonicStack = new ArrayDeque<>();
        this.monotonicStack.push(new int[]{Integer.MAX_VALUE, -1});
    }

    public int next(int price) {
        int span = 1;
        while (monotonicStack.peek()[0] <= price) {
            int[] stockInfo = monotonicStack.pop();
            span += stockInfo[1];
        }
        monotonicStack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));; // return 1
        System.out.println(stockSpanner.next(80));;  // return 1
        System.out.println(stockSpanner.next(60));;  // return 1
        System.out.println(stockSpanner.next(70));;  // return 2
        System.out.println(stockSpanner.next(60));;  // return 1
        System.out.println(stockSpanner.next(75));;  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));;  // return 6
    }
}