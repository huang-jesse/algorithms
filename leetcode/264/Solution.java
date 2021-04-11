import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public int nthUglyNumber(int n) {
        int count = 1;
        long cur = 1;
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> minPriorityQueue = new PriorityQueue<>();
        while(count < n) {
            if (seen.add(cur*2)) {
                minPriorityQueue.add(cur*2);
            }
            if (seen.add(cur*3)) {
                minPriorityQueue.add(cur*3);
            }
            if (seen.add(cur*5)) {
                minPriorityQueue.add(cur*5);
            }
            cur = minPriorityQueue.poll();
            count++;
        }
        return (int)cur;
    }

    public static void main(String[] args) {
        int n = 1407;
        Solution sol = new Solution();
        System.out.println("nthUglyNumber: "+ sol.nthUglyNumber(n));
    }
}