import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        // non-increasing order
        Arrays.sort(items, (o1, o2) -> o2[0] - o1[0]);
        // the profit of duplicate categories
        Deque<Integer> duplicateStack = new ArrayDeque<>();
        Set<Integer> categories = new HashSet<>();
        long totalProfit = 0;
        for (int i = 0; i < k; i++) {
            totalProfit += items[i][0];
            if (!categories.add(items[i][1])) {
                duplicateStack.push(items[i][0]);
            }
        }
        long res = totalProfit + (long)categories.size() * categories.size();
        // totalProfit will decreasing but distinctCategories will increasing
        for (int i = k; i < n; i++) {
            if (!duplicateStack.isEmpty() && !categories.contains(items[i][1])) {
                categories.add(items[i][1]);
                totalProfit = totalProfit - duplicateStack.pop() + items[i][0];
            }
            res = Math.max(res, totalProfit + (long)categories.size() * categories.size());
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] items = {{8,3},{7,1},{7,1},{7,1},{7,1},{1,2},{1,4},{1,5}};
        // int k = 4; // 33
        int k = 5; // 43
        System.out.println("test: " + sol.findMaximumElegance(items, k));
    }
}