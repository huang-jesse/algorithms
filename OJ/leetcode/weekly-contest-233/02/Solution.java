import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    private static int BUY = 0;

    private class Order implements Comparable {
        private int price;
        public int amount;

        public Order(int price, int amount) {
            this.price = price;
            this.amount = amount;
        }

        public int getPrice() {
            return price;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public int compareTo(Object o) {
            Order otherOrder = (Order)o;
            return Integer.compare(this.price, otherOrder.getPrice());
        }
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<Order> sellHeap = new PriorityQueue();// min
        PriorityQueue<Order> buyHeap = new PriorityQueue(Collections.reverseOrder()); // max

        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int orderType = order[2];
            if (orderType == BUY) {
                // buy order type
                // backlog buy orders
                if (sellHeap.isEmpty() || sellHeap.peek().getPrice() > price) {
                    buyHeap.add(new Order(price, amount));
                    continue;
                }
                while (!sellHeap.isEmpty() && sellHeap.peek().getPrice() <= price && amount > 0) {
                    int sellAmount = sellHeap.peek().amount;
                    if (sellAmount > amount) {
                        sellHeap.peek().amount = sellAmount - amount;
                        // all buy orders was sold
                        amount = 0;
                    } else {
                        amount = amount - sellAmount;
                        // all sell order was bought
                        sellHeap.poll();
                    }
                }
                // backlog buy orders
                if (amount > 0) {
                    buyHeap.add(new Order(price, amount));
                }
            } else {
                // sell order type
                // backlog sell orders
                if (buyHeap.isEmpty() || buyHeap.peek().getPrice() < price) {
                    sellHeap.add(new Order(price, amount));
                    continue;
                }
                while (!buyHeap.isEmpty() && buyHeap.peek().getPrice() >= price && amount > 0) {
                    int buyAmount = buyHeap.peek().amount;
                    if (buyAmount > amount) {
                        buyHeap.peek().amount = buyAmount - amount;
                        // all sell orders was sold
                        amount = 0;
                    } else {
                        amount = amount - buyAmount;
                        // all buy order was bought
                        buyHeap.poll();
                    }
                }
                // backlog sell orders
                if (amount > 0) {
                    sellHeap.add(new Order(price, amount));
                }
            }
        }

        Long sellSum = sellHeap.stream().map(order -> Long.valueOf(order.getAmount())).reduce(Long.valueOf(0), Long::sum);
        Long buySum = buyHeap.stream().map(order -> Long.valueOf(order.getAmount())).reduce(Long.valueOf(0), Long::sum);
        Long baseNum = (long)Math.pow(10, 9) + 7;

        return (int)((sellSum + buySum) % baseNum);
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        // Set parameters
        // int[][] testArr = new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};
        int[][] testArr = new int[][]{{944925198,885003661,0},{852263791,981056352,0},{16300530,415829909,0},{940927944,713835606,0},{606389372,407474168,1},{139563740,85382287,1},{700244880,901922025,1},{972900669,15506445,0},{576578542,65339074,0},{45972021,293765308,0},{464403992,97750995,0},{29659852,536508041,0},{799523481,299864737,0},{711908211,480514887,1},{354125407,677598767,1},{279004011,688916331,0},{263524013,64622178,0},{375395974,460070320,0},{971786816,379275200,1},{577774472,214070125,1},{987757349,711231195,0}};
        // int[][] testArr = new int[][]{{26,7,0},{16,1,1},{14,20,0},{23,15,1},{24,26,0},{19,4,1},{1,1,0}};
        // int[][] testArr = new int[][]{{10,5,0},{15,2,1}, {25,1,1}, {30,4,0}};
        // Do test
        int test = sol.getNumberOfBacklogOrders(testArr);
        System.out.println(test);
    }
}