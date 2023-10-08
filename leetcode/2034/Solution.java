import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class StockPrice {
    private final Map<Integer, Integer> timestampMap;
    private final TreeMap<Integer, Integer> priceCounterTs;
    private int latestTimestamp;

    public StockPrice() {
        this.timestampMap = new HashMap<>();
        this.priceCounterTs = new TreeMap<>();
        this.latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        if (timestampMap.containsKey(timestamp)) {
            // 更新 priceCounterTs
            int originalPrice = timestampMap.get(timestamp);
            int count = priceCounterTs.get(originalPrice) - 1;
            if (count == 0) {
                priceCounterTs.remove(originalPrice);
            } else {
                priceCounterTs.put(originalPrice, count);
            }
        }
        timestampMap.put(timestamp, price);
        priceCounterTs.put(price, priceCounterTs.getOrDefault(price, 0) + 1);
        this.latestTimestamp = Math.max(timestamp, this.latestTimestamp);
    }

    public int current() {
        return this.timestampMap.get(this.latestTimestamp);
    }

    public int maximum() {
        return this.priceCounterTs.lastKey();
    }

    public int minimum() {
        return this.priceCounterTs.firstKey();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
        stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
        System.out.println(stockPrice.current());     // return 5, the latest timestamp is 2 with the price being 5.
        System.out.println(stockPrice.maximum());     // return 10, the maximum price is 10 at timestamp 1.
        stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                                  // Timestamps are [1,2] with corresponding prices [3,5].
        System.out.println(stockPrice.maximum());     // return 5, the maximum price is 5 after the correction.
        stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
        System.out.println(stockPrice.minimum());     // return 2, the minimum price is 2 at timestamp 4.
    }
}