import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        long sumNeg = 0;
        long val = 1;
        int count = 0;
        for (int i : nums) {
            val += i;
            if (i < 0) {
                minQueue.add(i);
            }
            while(val < 1) {
                if (minQueue.isEmpty()) return -1;
                Integer minNeg = minQueue.poll();
                val -= minNeg;
                sumNeg += minNeg;
                count++;
            }
        }
        if (sumNeg + val < 1) {
            return -1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,100,100,-250,-60,-140,-50,-50,100,150};
        Solution sol = new Solution();
        System.out.println("val: " + sol.magicTower(nums));
    }
}