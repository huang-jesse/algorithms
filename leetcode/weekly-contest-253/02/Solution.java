import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int minStoneSum(int[] piles, int k) {
        int totalStoneSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : piles) {
            pq.offer(stone);
            totalStoneSum += stone;
        }
        while (k > 0) {
            k--;
            int cur = pq.poll();
            int minus = cur / 2;
            cur = cur - minus;
            pq.offer(cur);
            
            totalStoneSum -= minus;
        }
        return totalStoneSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] piles = {5,4,9};
        int k = 2;
        System.out.println("test: " + sol.minStoneSum(piles, k));
    }
}