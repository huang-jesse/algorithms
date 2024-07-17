import java.util.PriorityQueue;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // sort by capital
        PriorityQueue<int[]> allProjects = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        // sort by profit desc
        PriorityQueue<Integer> canDoProfits = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int n = profits.length;
        for (int i = 0; i < n; i++) {
            int cap = capital[i];
            int profit = profits[i];
            if (cap <= w) {
                canDoProfits.offer(profit);
            } else {
                allProjects.offer(new int[]{cap, profit});
            }
        }

        int count = 0;
        while (count < k && !canDoProfits.isEmpty()) {
            int maxProfit = canDoProfits.poll();
            w += maxProfit;
            count++;
            
            while (!allProjects.isEmpty() && allProjects.peek()[0] <= w) {
                int profit = allProjects.poll()[1];
                canDoProfits.offer(profit);
            }
        }
        return w;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int k = 2;
        int w = 0;
        int[] profits = {1,2,3};
        int[] capital = {0,1,1};
        System.out.println("test: " + sol.findMaximizedCapital(k, w, profits, capital));
    }
}